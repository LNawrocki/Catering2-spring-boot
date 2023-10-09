package pl.coderslab.catering2springboot.controllers;

import org.aspectj.asm.IModelFilter;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.DepartmentRepository;
import pl.coderslab.catering2springboot.repository.NewMenuRepository;
import pl.coderslab.catering2springboot.repository.NewOrderRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@SessionAttributes({"userId", "name", "lastName", "superAdmin", "findUserId"})
public class UserController {

    public final DepartmentRepository departmentRepository;
    public final NewOrderRepository newOrderRepository;
    private final UserRepository userRepository;
    private final NewMenuRepository newMenuRepository;

    public UserController(UserRepository userRepository, DepartmentRepository departmentRepository, NewMenuRepository newMenuRepository, NewOrderRepository newOrderRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.newMenuRepository = newMenuRepository;
        this.newOrderRepository = newOrderRepository;
    }

    @GetMapping
    public String mealsView(Model model) {
        model.addAttribute("mealsMonday", newMenuRepository.findByDayId(1));
        model.addAttribute("mealsTuesday", newMenuRepository.findByDayId(2));
        model.addAttribute("mealsWednesday", newMenuRepository.findByDayId(3));
        model.addAttribute("mealsThursday", newMenuRepository.findByDayId(4));
        model.addAttribute("mealsFriday", newMenuRepository.findByDayId(5));
        model.addAttribute("date", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
        return "home";
    }

    @GetMapping("/admin/list")
    public String userList(Model model, HttpSession session) {
        if (session.getAttribute("findUserId") == null) {
            model.addAttribute("usersList", userRepository.findAll());
        }
        if (session.getAttribute("findUserId") != null) {
            model.addAttribute("usersList", userRepository.getByUserId((Long) session.getAttribute("findUserId")));
            return "/user/user-list";
        }
        return "/user/user-list";
    }

    @PostMapping("/admin/list/search")
    public String userList(@RequestParam String findUserId,
                           @RequestParam String search,
                           Model model) {
        model.addAttribute("findUserId", findUserId);
        System.out.println(findUserId);
//        model.addAttribute("search", sea)
        return "redirect:/admin/list";
    }


    @GetMapping("/admin/add")
    public String addView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            model.addAttribute("user", new User());
            model.addAttribute("departments", departmentRepository.findAll());
            return "/user/user-add";
        }
        return "redirect:/";
    }

    @PostMapping("/admin/add")
    public String add(@Valid User user, BindingResult bindingResult, HttpSession session, Model model) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getFieldErrors().stream()
                        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
                return "/user/user-add";
            }
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
            return "redirect:/admin/list";
        }
        return "redirect:/";
    }

    @GetMapping("/admin/update")
    public String updateView(@RequestParam Long userId, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            User user = userRepository.getByUserId(userId);
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("departments", departmentRepository.findAll());
            return "/user/user-update";
        }
        return "redirect:/";
    }

    @PostMapping("/admin/update")
    public String update(User user, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
            return "redirect:/admin/list";
        }
        return "redirect:/";
    }

    @GetMapping("/admin/delete")
    public String update(@RequestParam Long userId, Model model, User user, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            userRepository.delete(user);
            model.addAttribute("usersList", userRepository.findAll());
            return "redirect:/admin/list";
        }
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/user/auth")
    public String authenticate() {
        return "/user/user-auth";
    }

    @PostMapping("/user/auth")
    public String authenticate(@RequestParam String login, @RequestParam String password, Model model) {

        User user = userRepository.getByLogin(login);

        if (Objects.nonNull(user)) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                model.addAttribute("name", user.getName());
                model.addAttribute("lastname", user.getLastName());
                model.addAttribute("userId", user.getUserId());
                model.addAttribute("superAdmin", user.getSuperAdmin());
                model.addAttribute("login", user.getLogin());
//                session.setMaxInactiveInterval(60);
                if (user.getSuperAdmin()) {
                    return "redirect:/admin/home";
                }
                return "redirect:/user/home";
            }
        }
        model.addAttribute("msg", "Niepoprawny login lub błędne hasło");
        return "/user/user-auth";
    }

    @GetMapping("/admin/home")
    public String adminHomeView(Model model, HttpSession session) {

        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            User user = userRepository.getByUserId((Long) session.getAttribute("userId"));

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
            model.addAttribute("date", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            return "/admin/admin-home";
        }
        return "redirect:/";
    }

    @GetMapping("/user/home")
    public String userHomeView(Model model, HttpSession session) {

        if (session.getAttribute("userId") != null) {
            User user = userRepository.getByUserId((Long) session.getAttribute("userId"));
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

            model.addAttribute("date", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            return "/user/user-home";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}






