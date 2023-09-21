package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.catering2springboot.entity.NewMenu;
import pl.coderslab.catering2springboot.entity.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBy(); // List of all users
    User getByUserId(Long userId); // get user by userId

    @Modifying
    @Transactional
    @Query("delete User u where u.userId = ?1")
    int deleteUserByUserId(Long userId);
    }


