package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.entity.Department;
import pl.coderslab.catering2springboot.repository.ConfigRepository;
import pl.coderslab.catering2springboot.repository.DepartmentRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private  final DepartmentRepository departmentRepository;
    private final ConfigRepository configRepository;

    public AdminController(DepartmentRepository departmentRepository, ConfigRepository configRepository) {
        this.departmentRepository = departmentRepository;
        this.configRepository = configRepository;
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
    public String adminConfigView(HttpSession session){
//        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            return "/admin/admin-config";
//        }
//        return "redirect:/";
    }

    @PostMapping ("/config")
    public String adminConfigView(@RequestParam Boolean editMode, HttpSession session){
//        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
//        configRepository.findAllById();


        return "/admin/admin-config";
//        }
//        return "redirect:/";
    }
}
