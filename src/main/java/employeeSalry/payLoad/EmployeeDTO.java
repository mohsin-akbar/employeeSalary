package employeeSalry.payLoad;

import lombok.Data;

public class EmployeeDTO {
    private int empId;
    private String name;
    private String email;
    private String dept;

    // getters, setters, constructors
    public EmployeeDTO(int empId, String name, String email, String dept) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.dept = dept;
    }
    public EmployeeDTO() {}
    // getters/setters omitted for brevity
}
