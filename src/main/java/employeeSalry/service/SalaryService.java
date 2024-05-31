package employeeSalry.service;


import employeeSalry.entity.Employee;
import employeeSalry.entity.Salary;
import employeeSalry.payLoad.SalaryDto;
import employeeSalry.repository.EmployeeRepo;
import employeeSalry.repository.SalaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaryService {
    @Autowired
    private SalaryRepo salaryRepo;

    @Autowired
    private EmployeeRepo employeeRepo;


    public SalaryDto createSalary(long eid, SalaryDto salaryDto){
        Employee employee = employeeRepo.findById(eid).get();
        Salary salary =new Salary();
        salary.setSalary(salaryDto.getSalary());
        salary.setEmployee(employee);

        Salary savedSalary = salaryRepo.save(salary);

        return  mapToDto(savedSalary);
    }

    private SalaryDto mapToDto(Salary salary) {
        SalaryDto salaryDto=new SalaryDto();
        salaryDto.setSalary(salary.getSalary());
        salaryDto.setSid(salary.getSid());
        salaryDto.setEmployee(salary.getEmployee());
        salaryDto.setEid(salary.getEmployee().getEid());
        return salaryDto;
    }
}
