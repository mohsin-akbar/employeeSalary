package employeeSalry.service;

import employeeSalry.entity.Employee;
import employeeSalry.entity.Salary;
import employeeSalry.payLoad.EmployeeDto;
import employeeSalry.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeRepo employeeRepo;

   public EmployeeDto  createEmployee(EmployeeDto employeeDto) {
       Employee employee=new Employee();
       employee.setPassword(passwordEncoder.encode("employeeDto.getPassword"));
       employee.setCity(employeeDto.getCity());
       employee.setName(employeeDto.getName());
       employee.setEmail(employeeDto.getEmail());
     //  employee.setSalary(employeeDto.getSalary());
       
       Employee savedEmployee = employeeRepo.save(employee);

       return mapToDto(savedEmployee);

    }

    private EmployeeDto mapToDto(Employee employee) {
       EmployeeDto employeeDto=new EmployeeDto();
       employeeDto.setCity(employee.getCity());
       employeeDto.setPassword(employee.getPassword());
       employeeDto.setName(employee.getName());
       employeeDto.setEmail(employee.getEmail());
       employeeDto.setEid(employee.getEid());
      //employeeDto.setSalary(employee.getSalary());
       return employeeDto;
    }

    public void deleteEmployee(long eid) {
       employeeRepo.deleteById(eid);
    }


    public EmployeeDto findEmployeeById(long eid) {
        Optional<Employee> byId = employeeRepo.findById(eid);
        Employee employee = byId.get();

        return mapToDto(employee);
    }
}
