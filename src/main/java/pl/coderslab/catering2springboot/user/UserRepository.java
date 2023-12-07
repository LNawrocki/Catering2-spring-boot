package pl.coderslab.catering2springboot.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.department.Department;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUserId(Long userId);
    User getByLogin(String login);
    List<User> getByDepartmentId(Integer id);
    List<User> findAllByDepartment(Department department);
}



