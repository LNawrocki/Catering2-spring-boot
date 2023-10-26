package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.catering2springboot.entity.Config;
import pl.coderslab.catering2springboot.entity.Department;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.financial.FinancialDepartmentSummary;
import pl.coderslab.catering2springboot.repository.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private  final DepartmentRepository departmentRepository;
    private final ConfigRepository configRepository;
    private final NewOrderRepository newOrderRepository;
    private final UserRepository userRepository;
    private final NewMenuRepository newMenuRepository;

    public AdminController(DepartmentRepository departmentRepository, ConfigRepository configRepository, NewOrderRepository newOrderRepository, UserRepository userRepository, NewMenuRepository newMenuRepository) {
        this.departmentRepository = departmentRepository;
        this.configRepository = configRepository;
        this.newOrderRepository = newOrderRepository;
        this.userRepository = userRepository;
        this.newMenuRepository = newMenuRepository;
    }

    //TODO - move to financial controller
    @GetMapping("/financial")
    public String financialSummary (Model model){
        List<FinancialDepartmentSummary> financialDepartmentSummaryList = new ArrayList<>();
        List<Department> allDepartments = departmentRepository.findAll();
        BigDecimal sumOfDepartmentFullPrice = new BigDecimal(0);
        BigDecimal sumOfDepartmentDiscountPrice = new BigDecimal(0);
        for (Department department : allDepartments) {
            FinancialDepartmentSummary financialDepartmentSummary = new FinancialDepartmentSummary();
            financialDepartmentSummary.setDepartmentName(department.getName());
            List<Long> usersId = new ArrayList<>();
            List<User> usersByDepartment = userRepository.findAllByDepartment(department);
            for (User userBelongToDepartment : usersByDepartment) {
                usersId.add(userBelongToDepartment.getUserId());
            }
            for (Long id : usersId) {
                NewOrder newOrder = newOrderRepository.getNewOrderByUserId(id);
                if (newOrder != null) {
                    financialDepartmentSummary.setDepartmentSummaryDiscountPrice(financialDepartmentSummary.getDepartmentSummaryDiscountPrice().add(newOrder.getToPay()));;
                    financialDepartmentSummary.setDepartmentSummaryFullPrice(financialDepartmentSummary.getDepartmentSummaryFullPrice().add(newMenuRepository.findByMealNo(newOrder.getMealMon()).getMealPrice()));
                    financialDepartmentSummary.setDepartmentSummaryFullPrice(financialDepartmentSummary.getDepartmentSummaryFullPrice().add(newMenuRepository.findByMealNo(newOrder.getMealTue()).getMealPrice()));
                    financialDepartmentSummary.setDepartmentSummaryFullPrice(financialDepartmentSummary.getDepartmentSummaryFullPrice().add(newMenuRepository.findByMealNo(newOrder.getMealWed()).getMealPrice()));
                    financialDepartmentSummary.setDepartmentSummaryFullPrice(financialDepartmentSummary.getDepartmentSummaryFullPrice().add(newMenuRepository.findByMealNo(newOrder.getMealThu()).getMealPrice()));
                    financialDepartmentSummary.setDepartmentSummaryFullPrice(financialDepartmentSummary.getDepartmentSummaryFullPrice().add(newMenuRepository.findByMealNo(newOrder.getMealFri()).getMealPrice()));

                }
            }
            sumOfDepartmentDiscountPrice = sumOfDepartmentDiscountPrice.add(financialDepartmentSummary.getDepartmentSummaryDiscountPrice());
            sumOfDepartmentFullPrice = sumOfDepartmentFullPrice.add(financialDepartmentSummary.getDepartmentSummaryFullPrice());
            financialDepartmentSummaryList.add(financialDepartmentSummary);
        }

        model.addAttribute("financialDepartmentSummaryList", financialDepartmentSummaryList);
        model.addAttribute("sumOfDepartmentDiscountPrice", sumOfDepartmentDiscountPrice);
        model.addAttribute("sumOfDepartmentFullPrice", sumOfDepartmentFullPrice);

        return "/admin/admin-financial";
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

    @PostMapping ("/config/newMenuAvaliable")
    public String adminNewMenuAvaliable(Config config, HttpSession session){
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            Config configValues = configRepository.findById(1).get();
            configValues.setNewMenuAvaliable(config.getNewMenuAvaliable());
            configRepository.save(configValues);
            return "redirect:/admin/config";
        }
        return "redirect:/";
    }
}
