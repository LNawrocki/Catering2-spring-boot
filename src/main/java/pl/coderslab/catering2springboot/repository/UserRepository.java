package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.entity.Department;
import pl.coderslab.catering2springboot.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUserId(Long userId);
    User getByLogin(String login);
    List<User> getByDepartmentId(Integer id);
    List<User> findAllByDepartment(Department department);
}



