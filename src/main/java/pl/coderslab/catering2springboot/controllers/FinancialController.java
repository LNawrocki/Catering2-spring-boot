package pl.coderslab.catering2springboot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.catering2springboot.config.Config;
import pl.coderslab.catering2springboot.config.ConfigService;
import pl.coderslab.catering2springboot.entity.*;
import pl.coderslab.catering2springboot.financial.FinancialDepartmentSummary;
import pl.coderslab.catering2springboot.newMenu.NewMenuRepository;
import pl.coderslab.catering2springboot.newOrder.NewOrder;
import pl.coderslab.catering2springboot.newOrder.NewOrderRepository;
import pl.coderslab.catering2springboot.repository.*;
import pl.coderslab.catering2springboot.user.User;
import pl.coderslab.catering2springboot.user.UserRepository;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class FinancialController {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final ActualOrderRepository actualOrderRepository;
    private final NewOrderRepository newOrderRepository;
    private final ActualMenuRepository actualMenuRepository;
    private final NewMenuRepository newMenuRepository;
    private final ConfigService configService;


    @GetMapping("/financial")
    public String financialSummary(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
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
                        if (!newOrder.getIsPaid()) {
                            financialDepartmentSummary.setNotPaidOrders(financialDepartmentSummary.getNotPaidOrders() + 1);
                        }
                        financialDepartmentSummary.setDepartmentSummaryDiscountPrice(financialDepartmentSummary.getDepartmentSummaryDiscountPrice().add(newOrder.getToPay()));
                        if (!newMenuRepository.findAll().isEmpty()) {
                            financialDepartmentSummary.setDepartmentSummaryFullPrice(financialDepartmentSummary.getDepartmentSummaryFullPrice().add(newMenuRepository.findByMealNo(newOrder.getMealMon()).getMealPrice()));
                            financialDepartmentSummary.setDepartmentSummaryFullPrice(financialDepartmentSummary.getDepartmentSummaryFullPrice().add(newMenuRepository.findByMealNo(newOrder.getMealTue()).getMealPrice()));
                            financialDepartmentSummary.setDepartmentSummaryFullPrice(financialDepartmentSummary.getDepartmentSummaryFullPrice().add(newMenuRepository.findByMealNo(newOrder.getMealWed()).getMealPrice()));
                            financialDepartmentSummary.setDepartmentSummaryFullPrice(financialDepartmentSummary.getDepartmentSummaryFullPrice().add(newMenuRepository.findByMealNo(newOrder.getMealThu()).getMealPrice()));
                            financialDepartmentSummary.setDepartmentSummaryFullPrice(financialDepartmentSummary.getDepartmentSummaryFullPrice().add(newMenuRepository.findByMealNo(newOrder.getMealFri()).getMealPrice()));
                        } else {
                            financialDepartmentSummary.setDepartmentSummaryFullPrice(BigDecimal.valueOf(0));
                        }
                    }
                }
                sumOfDepartmentDiscountPrice = sumOfDepartmentDiscountPrice.add(financialDepartmentSummary.getDepartmentSummaryDiscountPrice());
                sumOfDepartmentFullPrice = sumOfDepartmentFullPrice.add(financialDepartmentSummary.getDepartmentSummaryFullPrice());
                financialDepartmentSummaryList.add(financialDepartmentSummary);
            }

            model.addAttribute("financialDepartmentSummaryList", financialDepartmentSummaryList);
            model.addAttribute("sumOfDepartmentDiscountPrice", sumOfDepartmentDiscountPrice);
            model.addAttribute("sumOfDepartmentFullPrice", sumOfDepartmentFullPrice);
            model.addAttribute("refundation", sumOfDepartmentFullPrice.subtract(sumOfDepartmentDiscountPrice));
            model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
            model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));

            Config configValues = configService.getConfig();
            model.addAttribute(configValues);
            return "/admin/admin-financial";
        }
        return "redirect:/";
    }
}
