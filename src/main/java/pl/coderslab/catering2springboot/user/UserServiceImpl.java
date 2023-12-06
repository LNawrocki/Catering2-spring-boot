package pl.coderslab.catering2springboot.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.catering2springboot.entity.Department;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    public final UserRepository userRepository;


    @Override
    public User getUserById(Long id) {
        return userRepository.getByUserId(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    @Override
    public List<User> findUsersByDepartmentId(Integer id) {
        return userRepository.getByDepartmentId(id);
    }

    @Override
    public List<User> findUsersByDepartment(Department department) {
        return userRepository.findAllByDepartment(department);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
