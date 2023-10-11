package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.entity.Department;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.ConfigRepository;
import pl.coderslab.catering2springboot.repository.DepartmentRepository;
import pl.coderslab.catering2springboot.repository.NewOrderRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private  final DepartmentRepository departmentRepository;
    private final ConfigRepository configRepository;
    private final NewOrderRepository newOrderRepository;
    private final UserRepository userRepository;

    public AdminController(DepartmentRepository departmentRepository, ConfigRepository configRepository, NewOrderRepository newOrderRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.configRepository = configRepository;
        this.newOrderRepository = newOrderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/financial")
    public String financialSummary (){

        List<Department> allDepartments = departmentRepository.findAll();
        for (Department department : allDepartments) {
            List<Long> usersId = new ArrayList<>();
            List<User> usersByDepartment = userRepository.findAllByDepartment(department);
            for (User user : usersByDepartment) {
                usersId.add(user.getUserId());
            }
            System.out.println(usersId);
            for (Long id : usersId) {
                NewOrder newOrder = newOrderRepository.getNewOrderByUserId(id);
                if (newOrder != null) {
                    BigDecimal idPrice = newOrder.getPriceMon().add(newOrder.getPriceTue().add(newOrder.getPriceWed().add(newOrder.getPriceThu().add(newOrder.getPriceFri()))));
                    System.out.println(idPrice);
                }
            }
        }


        return "/admin-financial";
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
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            return "/admin/admin-config";
        }
        return "redirect:/";
    }

    @PostMapping ("/config/editMenu")
    public String adminEditMenu(@RequestParam Boolean editMode, HttpSession session){
//        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
//        configRepository.findAllById();


        return "/admin/admin-config";
//        }
//        return "redirect:/";
    }

    @PostMapping ("/config/newMenuAvaliable")
    public String adminnewMenuAvaliable(@RequestParam Boolean editMode, HttpSession session){
//        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
//        configRepository.findAllById();


        return "/admin/admin-config";
//        }
//        return "redirect:/";
    }


}
