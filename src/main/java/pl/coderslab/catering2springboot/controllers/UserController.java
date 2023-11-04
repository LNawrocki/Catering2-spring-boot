package pl.coderslab.catering2springboot.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.catering2springboot.entity.ActualOrder;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Controller
@SessionAttributes({"userId", "name", "lastName", "superAdmin", "searchId", "searchLogin", "searchDepartmentId"})
public class UserController {

    public final DepartmentRepository departmentRepository;
    public final NewOrderRepository newOrderRepository;
    private final UserRepository userRepository;
    private final NewMenuRepository newMenuRepository;
    private final ActualOrderRepository actualOrderRepository;
    private final ConfigRepository configRepository;

    public UserController(UserRepository userRepository, DepartmentRepository departmentRepository, NewMenuRepository newMenuRepository, NewOrderRepository newOrderRepository, ActualOrderRepository actualOrderRepository, ConfigRepository configRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.newMenuRepository = newMenuRepository;
        this.newOrderRepository = newOrderRepository;
        this.actualOrderRepository = actualOrderRepository;
        this.configRepository = configRepository;
    }

    @GetMapping
    public String mealsView(Model model) {
        if (configRepository.findAll().get(0).getEditMode()) {
            model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
            model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
            return "home-editmode";
        }
        model.addAttribute("mealsMonday", newMenuRepository.findByDayId(1));
        model.addAttribute("mealsTuesday", newMenuRepository.findByDayId(2));
        model.addAttribute("mealsWednesday", newMenuRepository.findByDayId(3));
        model.addAttribute("mealsThursday", newMenuRepository.findByDayId(4));
        model.addAttribute("mealsFriday", newMenuRepository.findByDayId(5));
        model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
        model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
        model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
        return "home";
    }

    @GetMapping("/admin/list")
    public String userList(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            model.addAttribute("departments", departmentRepository.findAll());

            if (session.getAttribute("searchId") != null && session.getAttribute("searchId") != "") {
                Long id = Long.parseLong((String) session.getAttribute("searchId"));
                User user = userRepository.getByUserId(id);
                List<User> users = new ArrayList<>();
                users.add(user);
                model.addAttribute("usersList", users);
                model.addAttribute("searchId", null);
                return "/user/user-list";
            }

            if (session.getAttribute("searchLogin") != null && session.getAttribute("searchLogin") != "") {
                String login = (String) session.getAttribute("searchLogin");
                User user = userRepository.getByLogin(login);
                System.out.println(user);
                List<User> users = new ArrayList<>();
                users.add(user);
                model.addAttribute("usersList", users);
                model.addAttribute("searchLogin", null);
                return "/user/user-list";
            }

            if (session.getAttribute("searchDepartmentId") != null && session.getAttribute("searchDepartmentId") != "") {
                List<User> users = userRepository.findAllByDepartment(departmentRepository.findById((Integer) session.getAttribute("searchDepartmentId")).get());
                model.addAttribute("usersList", users);
                return "/user/user-list";
            }

            model.addAttribute("usersList", userRepository.findAll());
            return "/user/user-list";
        }
        return "redirect:/";
    }

    @PostMapping("/admin/list/searchId")
    public String userListId(@RequestParam String searchId,
                             Model model) {
        model.addAttribute("searchId", searchId);
        return "redirect:/admin/list";
    }

    @PostMapping("/admin/list/searchLogin")
    public String userListLogin(@RequestParam String searchLogin,
                                Model model) {
        model.addAttribute("searchLogin", searchLogin);
        return "redirect:/admin/list";
    }

    @PostMapping("/admin/list/searchDepartment")
    public String userListDepartment(@RequestParam Integer searchDepartmentId,
                                     Model model) {
        model.addAttribute("searchDepartmentId", searchDepartmentId);
        return "redirect:/admin/list";
    }

    @PostMapping("/admin/list/searchClean")
    public String userListClean(Model model, HttpSession session) {
        model.addAttribute("searchId", "");
        model.addAttribute("searchLogin", "");
        model.addAttribute("searchDepartmentId", "");
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

    //TODO - zabezpieczenie przed nadpisaniem istniejącego użytkownika
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

    @PostMapping("/admin/delete/confirm")
    public String deleteConfirm(@RequestParam Long deleteUserId, @RequestParam(required = false) Boolean confirm, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            User user = userRepository.getByUserId(deleteUserId);

            if (user.getUserId() == session.getAttribute("userId")) {
                return "/admin/admin-delete-info";
            }

            if (user.getSuperAdmin()) {
                model.addAttribute("departments", departmentRepository.findAll());
                return "/admin/admin-delete-info";
            }

            ActualOrder actualOrderByUserId = actualOrderRepository.getActualOrderByUser_UserId(deleteUserId);
            NewOrder newOrder = newOrderRepository.getNewOrderByUserId(deleteUserId);
            if ((actualOrderByUserId != null || newOrder != null) && Objects.isNull(confirm)) {
                model.addAttribute("deleteUserId", deleteUserId);
                return "/user/user-list-delete-info";
            }

            if (actualOrderByUserId != null) {
                actualOrderRepository.delete(actualOrderByUserId);
            }

            if (newOrder != null) {
                newOrderRepository.delete(newOrder);
            }

            userRepository.delete(user);
            model.addAttribute("usersList", userRepository.findAll());
            model.addAttribute("departments", departmentRepository.findAll());
            return "redirect:/admin/list";
        }
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
            if (user.getActive() == false && BCrypt.checkpw(password, user.getPassword())) {
                model.addAttribute("login", user.getLogin());
                return "user-not-active";
            }
            if (BCrypt.checkpw(password, user.getPassword())) {
                model.addAttribute("name", user.getName());
                model.addAttribute("lastname", user.getLastName());
                model.addAttribute("userId", user.getUserId());
                model.addAttribute("superAdmin", user.getSuperAdmin());
                model.addAttribute("login", user.getLogin());
                //TODO: ustawienie zakończenia sesji
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


    @GetMapping("/user/home")
    public String userHomeView(Model model, HttpSession session) {

        if (session.getAttribute("userId") != null) {
            User user = userRepository.getByUserId((Long) session.getAttribute("userId"));
            model.addAttribute("name", user.getName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("editUserId", user.getUserId());
            if (configRepository.findAll().get(0).getEditMode()) {
                return "/user/user-home-editmode";
            }


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
            return "/user/user-home";
        }
        return "redirect:/";
    }

    @GetMapping("/user/update")
    public String userUpdateView(@RequestParam Long editUserId, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            User user = userRepository.getByUserId(editUserId);
            System.out.println(user.getPassword());
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("departments", departmentRepository.findAll());
            return "/user/user-update";
        }
        return "redirect:/";
    }

    @PostMapping("/user/update")
    public String userUpdate(User user, HttpSession session) {
        System.out.println(user.toString());
        if (session.getAttribute("userId") != null) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
            return "redirect:/logout";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}






