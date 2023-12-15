package pl.coderslab.catering2springboot.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.catering2springboot.department.Department;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUserId(Long userId);
    User getByLogin(String login);
    List<User> getByDepartmentId(Integer id);
    List<User> findAllByDepartment(Department department);

    @Query(value ="SELECT MIN(t1.user_id + 1) AS first_missing_id\n " +
            "FROM users AS t1\n LEFT JOIN users AS u2 ON t1.user_id + 1 = u2.user_id\n" +
            "WHERE u2.user_id IS NULL", nativeQuery = true)
    Integer firsEmptyIndex();

    @Query(value ="SELECT max(u.userId) FROM User u")
    Integer lastEmptyIndex();

}



