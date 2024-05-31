package employeeSalry.Controller;

import employeeSalry.entity.Employee;
import employeeSalry.entity.Salary;
import employeeSalry.payLoad.EmployeeDto;
import employeeSalry.payLoad.SalaryDto;
import employeeSalry.service.EmployeeService;
import employeeSalry.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SalaryService salaryService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employee = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);

    }
    @PostMapping("/{eid}/salary")
    public ResponseEntity<SalaryDto> createSalary(@PathVariable("eid") long eid, @RequestBody SalaryDto salaryDto){
        SalaryDto salary = salaryService.createSalary(eid, salaryDto);
        return new ResponseEntity<>(salary,HttpStatus.CREATED);
    }

    @DeleteMapping("/{eid}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("eid") long eid){
        employeeService.deleteEmployee(eid);

        return new ResponseEntity<>("the employee record deleted",HttpStatus.OK);
    }

    @GetMapping("/{eid}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("eid") long eid){
        EmployeeDto employeeDto = employeeService.findEmployeeById(eid);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }
}
