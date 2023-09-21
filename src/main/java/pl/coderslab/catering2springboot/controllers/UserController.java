package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("usersList",userRepository.findBy());
        return "/user/user-list";
    }

    @GetMapping("/update")
    public String update(@RequestParam Long userId, Model model){
        System.out.println(userId);
        model.addAttribute("user", userRepository.getByUserId(userId));
        return "/user/user-update";
    }



    @PostMapping("/delete")
    public String updateUser(@RequestParam Long userId, Model model) {
        userRepository.deleteUserByUserId(userId);
        model.addAttribute("usersList",userRepository.findBy());
        return "redirect:/user/user-list";
    }


}
