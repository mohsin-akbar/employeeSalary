package employeeSalry.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sid;
    private double salary;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="eid",unique = true)
    private Employee employee;
}
