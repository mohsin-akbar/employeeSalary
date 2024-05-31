package employeeSalry.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eid;
    private String name;
    private String city;

    private String password;

    @Column(unique = true)
    private String email;
    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
    private Salary salary;
}
