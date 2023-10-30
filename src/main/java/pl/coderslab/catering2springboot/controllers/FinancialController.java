package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.catering2springboot.entity.*;
import pl.coderslab.catering2springboot.financial.FinancialDepartmentSummary;
import pl.coderslab.catering2springboot.repository.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class FinancialController {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final ActualOrderRepository actualOrderRepository;
    private final NewOrderRepository newOrderRepository;
    private final ActualMenuRepository actualMenuRepository;
    private final NewMenuRepository newMenuRepository;


    public FinancialController(DepartmentRepository departmentRepository, UserRepository userRepository, ActualOrderRepository actualOrderRepository, NewOrderRepository newOrderRepository, ActualMenuRepository actualMenuRepository, NewMenuRepository newMenuRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.actualOrderRepository = actualOrderRepository;
        this.newOrderRepository = newOrderRepository;
        this.actualMenuRepository = actualMenuRepository;
        this.newMenuRepository = newMenuRepository;
    }

    //TODO - move to financial controller
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
            model.addAttribute("refundation", sumOfDepartmentFullPrice.subtract(sumOfDepartmentDiscountPrice));
            model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
            model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
            return "/admin/admin-financial";
        }
        return "redirect:/";
    }

//    @GetMapping("/financial/dinners")
//    public String financialDinnerSummary(Model model, HttpSession session) {
////        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
//
//            List<ActualMenu> allDinners = actualMenuRepository.findAll();
//            List<ActualOrder> allNewOrders = actualOrderRepository.findAll();
//            LinkedHashMap<String, String> dinnerUsersMap = new LinkedHashMap<>();
//
//            allDinners.forEach(dinner -> dinner.setMealName(String.valueOf(dinner.getMealNo()).concat(" ").concat(dinner.getMealName())));
//
//            for (ActualMenu dinner : allDinners) {
//                String ids = "";
//                for (ActualOrder newOrder : allNewOrders) {
//                    if (dinner.getMealNo().equals(newOrder.getMealMon()) ||
//                            dinner.getMealNo().equals(newOrder.getMealTue()) ||
//                            dinner.getMealNo().equals(newOrder.getMealWed()) ||
//                            dinner.getMealNo().equals(newOrder.getMealThu()) ||
//                            dinner.getMealNo().equals(newOrder.getMealFri())) {
//                        ids = ids + newOrder.getUser().getUserId() + ", ";
//                    }
//                }
//                dinnerUsersMap.put(dinner.getMealName(), ids);
//            }
//            model.addAttribute("dinnerUsersMap", dinnerUsersMap);
//            return "/admin/admin-dinner-ids";
//        }
//        return "redirect:/";
//    }
}
