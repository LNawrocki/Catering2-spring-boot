package pl.coderslab.catering2springboot.user;

import pl.coderslab.catering2springboot.department.Department;

import java.util.List;

public interface UserService {

    User getUserById(Long id);
    User getUserByLogin(String login);
    List<User> findUsersByDepartmentId(Integer id);
    List<User> findUsersByDepartment(Department department);

    List<User> findAll();
    void delete(User user);
    User save(User user);
    Integer firsEmptyIndex();
    Integer lastEmptyIndex();
}
