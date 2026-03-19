package employeeSalry.Controller;

import employeeSalry.entity.Employee;
import employeeSalry.payLoad.JwtRequest;
import employeeSalry.payLoad.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private JwtUtil jwtUtil;
    private static final List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee(101, "Mohsin", "mohsin@gmail.com", "IT"));
        employees.add(new Employee(102, "Deepika", "deepika@gmail.com", "HR"));
	employees.add(new Employee(102, "Deepak", "deepak@gmail.com", "HC"));
    }

    //for validating the user/admin
    @GetMapping("/generateToken")
    public String generateToken(@RequestBody JwtRequest jwtRequest){
        return jwtUtil.generateToken(jwtRequest.getSub(),jwtRequest.getRole());
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employees.stream()
                .filter(e -> e.getEmpId() == id)
                .findFirst()
                .orElse(null);
    }
}

