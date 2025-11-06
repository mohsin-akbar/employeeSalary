package employeeSalry.Controller;

import employeeSalry.payLoad.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aggregate")
public class HrAggregatorController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;
//

    @GetMapping("/details")
    public ResponseEntity<?> getCombinedDetails(@RequestHeader("Authorization") String authHeader) {
        try {
            // Extract and validate JWT
            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);
            String role = jwtUtil.extractRole(token);

            System.out.println("Authenticated User: " + username + " | Role: " + role);

            if (!jwtUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
            }

            //  Prepare headers for downstream calls
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // --- Call Employee Service ---
            ResponseEntity<List> empResponse = restTemplate.exchange(
                    "http://localhost:8081/employees/all",
                    HttpMethod.GET,
                    entity,
                    List.class
            );

            // --- Call Salary Service ---
            ResponseEntity<List> salaryResponse = restTemplate.exchange(
                    "http://localhost:8082/salaries/all",
                    HttpMethod.GET,
                    entity,
                    List.class
            );

            List<Map<String, Object>> employees = empResponse.getBody();
            List<Map<String, Object>> salaries = salaryResponse.getBody();

            // --- Merge employee + salary by empId ---
            List<Map<String, Object>> combinedList = employees.stream()
                    .map(emp -> {
                        Integer empId = (Integer) emp.get("empId");
                        Map<String, Object> salary = salaries.stream()
                                .filter(s -> s.get("empId").equals(empId))
                                .findFirst()
                                .orElse(null);

                        Map<String, Object> combined = new LinkedHashMap<>();
                        combined.put("employee", emp);
                        combined.put("salary", salary);
                        return combined;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(combinedList);

        } catch (HttpClientErrorException.Forbidden e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied from downstream service (403). Check JWT secret or roles.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching details: " + e.getMessage());
        }
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getEmployeeDetailsById(@PathVariable int id,
                                                    @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7); // Remove "Bearer "
        String username = jwtUtil.extractUsername(token);

        // Forward token to downstream services
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Call Employee Service
            ResponseEntity<String> empResponse = restTemplate.exchange(
                    "http://localhost:8081/employees/" + id,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            // Call Salary Service
            ResponseEntity<String> salaryResponse = restTemplate.exchange(
                    "http://localhost:8082/salaries/" + id,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            String combined = "{\n" +
                    "\"employee\": " + empResponse.getBody() + ",\n" +
                    "\"salary\": " + salaryResponse.getBody() + "\n" +
                    "}";

            return ResponseEntity.ok(combined);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching employee or salary details: " + ex.getMessage());
        }
    }
}
