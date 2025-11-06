package employeeSalry.payLoad;

public class EmployeeFullDetails {

    private int empId;
    private String name;
    private String email;
    private String dept;
    private double basic;
    private double bonus;


    public EmployeeFullDetails() {
    }

    public EmployeeFullDetails(int empId, String name, String email, String dept, double basic, double bonus) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.dept = dept;
        this.basic = basic;
        this.bonus = bonus;
    }

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

    public double getBasic() {
        return basic;
    }

    public void setBasic(double basic) {
        this.basic = basic;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }


}
