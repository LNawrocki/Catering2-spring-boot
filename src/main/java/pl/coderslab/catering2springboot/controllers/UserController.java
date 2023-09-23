package pl.coderslab.catering2springboot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.DepartmentRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    public final DepartmentRepository departmentRepository;

//    public UserController(UserRepository userRepository, DepartmentRepository departmentRepository) {
//        this.userRepository = userRepository;
//        this.departmentRepository = departmentRepository;
//    }

    @GetMapping("/list")
    public String userList(Model model) {
        List<User> list = userRepository.findBy();
        model.addAttribute("usersList", userRepository.findBy());
        return "/user/user-list";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("departments", departmentRepository.findAll());
        System.out.println(departmentRepository.findAll());
        return "/user/user-add";
    }

    @PostMapping("/add")
    public String add(User user) {
        System.out.println(user);
        userRepository.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/update")
    public String updateView(@RequestParam Long userId, Model model) {
        User user = userRepository.getByUserId(userId);
        user.setPassword("");
        model.addAttribute("user", user);
        model.addAttribute("departments", departmentRepository.findAll());
        return "/user/user-update";
    }

    @PostMapping("/update")
    public String update(@RequestParam String name,
                             @RequestParam String lastName,
                             @RequestParam String login,
                             @RequestParam String password,
                             @RequestParam Boolean superAdmin,
                             @RequestParam Long userId) {
        userRepository.updateUserFieldsByUserId(name, lastName, login, password, superAdmin, userId);
        return "redirect:/user/list";
    }

    @GetMapping("/delete")
    public String update(@RequestParam Long userId, Model model) {
        userRepository.deleteUserByUserId(userId);
        model.addAttribute("usersList", userRepository.findBy());
        return "redirect:/user/list";
    }
}
