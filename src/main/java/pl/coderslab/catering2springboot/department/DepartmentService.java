package pl.coderslab.catering2springboot.department;

import org.springframework.stereotype.Service;

import java.util.List;


public interface DepartmentService {

    List<Department> findAll();
    Department save(Department department);

    void delete(Department department);

    Department getById(Integer departmentId);
    Boolean isUserByDepartmentId(Integer id);

    Department getDepartmentById(Integer id);
}
