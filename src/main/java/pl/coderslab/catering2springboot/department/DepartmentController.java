package pl.coderslab.catering2springboot.department;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@SessionAttributes({"msg"})
@RequestMapping("/admin")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/department")
    public String addDepartmentView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            model.addAttribute("department", new Department());
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("msg", session.getAttribute("msg"));
            return "/admin/department-edit";
        }
        return "redirect:/";
    }

    @PostMapping("/department")
    public String addDepartment(@Valid Department department, BindingResult bindingResult, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            if (bindingResult.hasErrors()) {
                model.addAttribute("departments", departmentService.findAll());
                return "admin/department-edit";
            }
            departmentService.save(department);
            model.addAttribute("msg", "");
            return "redirect:/admin/department";
        }
        return "redirect:/";
    }

    @PostMapping("/department/delete")
    public String deleteDepartment(@RequestParam Integer deleteDepartmentId, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            System.out.println(departmentService.isUserByDepartmentId(deleteDepartmentId));
            if (departmentService.isUserByDepartmentId(deleteDepartmentId)){
                departmentService.delete(departmentService.getById(deleteDepartmentId));
                model.addAttribute("msg", "");

            } else {
                model.addAttribute("msg", "Odmowa - nie możesz usunąć działu, do którego należą użytkownicy");
            }

            return "redirect:/admin/department";
        }
        return "redirect:/";
    }
}
