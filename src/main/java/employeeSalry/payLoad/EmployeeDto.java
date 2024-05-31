package employeeSalry.payLoad;

import employeeSalry.entity.Salary;
import lombok.Data;

@Data
public class EmployeeDto {
    private long eid;
    private String name;
    private String city;
    private String password;
    private String email;
    private Salary salary;
}
