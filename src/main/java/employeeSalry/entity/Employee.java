package employeeSalry.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
    private int empId;
    private String name;
    private String email;
    private String dept;

    // getters, setters, constructors
    public Employee(int empId, String name, String email, String dept) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.dept = dept;
    }
    public Employee() {}

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
    // getters/setters omitted for brevity

    //new comment added in bottom
    

}
