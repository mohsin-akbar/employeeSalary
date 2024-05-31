package employeeSalry.payLoad;

import employeeSalry.entity.Employee;
import lombok.Data;

@Data
public class SalaryDto {
    private long sid;
    private double salary;
    private Employee employee;

    private long eid;

}
