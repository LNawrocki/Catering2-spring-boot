package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.catering2springboot.entity.Department;
import pl.coderslab.catering2springboot.repository.DepartmentRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private  final DepartmentRepository departmentRepository;

    public AdminController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/department")
    public String addDepartmentView(Model model){
        model.addAttribute("department", new Department());
        model.addAttribute("departments", departmentRepository.findAll());
        return "/admin/department-edit";
    }

    @PostMapping("/department")
    public String addDepartment(Department department){
        departmentRepository.save(department);
        return "redirect:/admin/department";
    }
}
