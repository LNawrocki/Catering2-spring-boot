package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.entity.Department;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
