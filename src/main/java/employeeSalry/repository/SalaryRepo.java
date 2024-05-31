package employeeSalry.repository;

import employeeSalry.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepo extends JpaRepository<Salary,Long> {
}
