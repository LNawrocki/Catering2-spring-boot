package pl.coderslab.catering2springboot.controllers;

import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.catering2springboot.config.Config;
import pl.coderslab.catering2springboot.config.ConfigService;
import pl.coderslab.catering2springboot.entity.*;

import pl.coderslab.catering2springboot.newMenu.NewMenuRepository;
import pl.coderslab.catering2springboot.repository.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;


@Controller
@RequestMapping("/admin")
@SessionAttributes({"msg"})
@AllArgsConstructor
public class AdminController {

    public final ActualMenuRepository actualMenuRepository;
    private final DepartmentRepository departmentRepository;
    private final ConfigService configService;
    private final UserRepository userRepository;
    private final NewOrderRepository newOrderRepository;
    private final NewMenuRepository newMenuRepository;
    private final DishRepository dishRepository;
    private final PriceRepository priceRepository;


    @GetMapping("/home")
    public String adminHomeView(Model model, HttpSession session) {

        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            User user = userRepository.getByUserId((Long) session.getAttribute("userId"));
            model.addAttribute("name", user.getName());
            model.addAttribute("lastName", user.getLastName());
            if (configService.editModeStatus()) {
                return "/admin/admin-home-editmode";
            }

            model.addAttribute("name", user.getName());
            model.addAttribute("lastName", user.getLastName());

            NewOrder order = newOrderRepository.getNewOrderByUserId(user.getUserId());
            if (order != null && !order.getIsPaid()) {
                model.addAttribute("receivables", order.getToPay());
            } else {
                model.addAttribute("receivables", 0);
            }
            model.addAttribute("mealsMonday", newMenuRepository.findByDayId(1));
            model.addAttribute("mealsTuesday", newMenuRepository.findByDayId(2));
            model.addAttribute("mealsWednesday", newMenuRepository.findByDayId(3));
            model.addAttribute("mealsThursday", newMenuRepository.findByDayId(4));
            model.addAttribute("mealsFriday", newMenuRepository.findByDayId(5));
            model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
            model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
            return "/admin/admin-home";
        }
        return "redirect:/";
    }

    @GetMapping("/department")
    public String addDepartmentView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            model.addAttribute("department", new Department());
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("msg", session.getAttribute("msg"));
            return "/admin/department-edit";
        }
        return "redirect:/";
    }

    @PostMapping("/department")
    public String addDepartment(@Valid Department department, BindingResult bindingResult, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            if (bindingResult.hasErrors()) {
                model.addAttribute("departments", departmentRepository.findAll());
                return "admin/department-edit";
            }
            departmentRepository.save(department);
            model.addAttribute("msg", "");
            return "redirect:/admin/department";
        }
        return "redirect:/";
    }

    @PostMapping("/department/delete")
    public String deleteDepartment(@RequestParam Integer deleteDepartmentId, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            if (userRepository.getByDepartmentId(deleteDepartmentId).isEmpty()){
                departmentRepository.delete(departmentRepository.findById(deleteDepartmentId).get());
                model.addAttribute("msg", "");

            } else {
                model.addAttribute("msg", "Odmowa -nie możesz usunąć działu, do którego należą użytkownicy");
            }

            return "redirect:/admin/department";
        }
        return "redirect:/";
    }

    @GetMapping("/config")
    public String configView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            Config config = configService.getConfig();
            model.addAttribute(config);
            return "/admin/admin-config";
        }
        return "redirect:/";
    }

    @PostMapping("/config/editMode")
    public String configEditMenu(@RequestParam Boolean editMode, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            Config configValues = configService.getConfig();
            configValues.setEditMode(editMode);
            configService.save(configValues);
            return "redirect:/admin/config";
        }
        return "redirect:/";
    }

    @PostMapping("/config/clearActualMenu")
    public String configClearActualMenu(HttpSession session) {
//        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
        actualMenuRepository.deleteAll();
        return "redirect:/admin/config";
    }
//        return "redirect:/";
//    }

    @PostMapping("/config/clearNewOrders")
    public String configClearNewOrders(HttpSession session) {
//        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
        newOrderRepository.deleteAll();
        return "redirect:/admin/config";
    }
//        return "redirect:/";
//    }

    @GetMapping("/update")
    public String adminUpdateView(@RequestParam Long editUserId, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            User user = userRepository.getByUserId(editUserId);
            model.addAttribute("user", user);
            model.addAttribute("departments", departmentRepository.findAll());
            return "/admin/admin-update";
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String adminUpdate(User user, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            if (userRepository.getByUserId(user.getUserId()).getPassword().equals(user.getPassword())) {
                userRepository.save(user);
                return "redirect:/admin/list";
            }
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
            return "redirect:/admin/list";
        }
        return "redirect:/";
    }

    @GetMapping("/dish")
    public String dishesView(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("dishes", dishRepository.findAll());
        return "/admin/dish-list";
    }

    @GetMapping("/price")
    public String pricesView(Model model) {
        model.addAttribute("price", new Price());
        model.addAttribute("prices", priceRepository.findAll());
        return "/admin/price-list";
    }


}
