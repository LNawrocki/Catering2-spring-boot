package pl.coderslab.catering2springboot.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.entity.Config;
import pl.coderslab.catering2springboot.entity.Department;

import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DepartmentRepository departmentRepository;
    private final ConfigRepository configRepository;
    private final UserRepository userRepository;


    public AdminController(DepartmentRepository departmentRepository, ConfigRepository configRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.configRepository = configRepository;

        this.userRepository = userRepository;
    }



    @GetMapping("/department")
    public String addDepartmentView(Model model){
        model.addAttribute("department", new Department());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("nextId", departmentRepository.count() + 1);
        return "/admin/department-edit";
    }

    @PostMapping("/department")
    public String addDepartment(@Valid Department department, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("nextId", departmentRepository.count() + 1);
            return "admin/department-edit";
        }
        departmentRepository.save(department);
        return "redirect:/admin/department";
    }

    @GetMapping ("/config")
    public String adminConfigView(Model model, HttpSession session){
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            Config configValues = configRepository.findById(1).get();
            model.addAttribute(configValues);
            return "/admin/admin-config";
        }
        return "redirect:/";
    }

    @PostMapping ("/config/editMenu")
    public String adminEditMenu(Config config, HttpSession session){
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            Config configValues = configRepository.findById(1).get();
            configValues.setEditMode(config.getEditMode());
            configRepository.save(configValues);
            return "redirect:/admin/config";
        }
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateView(@RequestParam Long editUserId, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            User user = userRepository.getByUserId(editUserId);
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("departments", departmentRepository.findAll());
            return "/user/user-update";
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(User user, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
            return "redirect:/admin/list";
        }
        return "redirect:/";
    }
}
