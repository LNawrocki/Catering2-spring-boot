package pl.coderslab.catering2springboot.department;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department getDepartmentById(Integer id);
}
