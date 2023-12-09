package pl.coderslab.catering2springboot.user;

import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.catering2springboot.config.ConfigService;
import pl.coderslab.catering2springboot.department.DepartmentRepository;
import pl.coderslab.catering2springboot.newOrder.NewOrder;
import pl.coderslab.catering2springboot.newMenu.NewMenuService;
import pl.coderslab.catering2springboot.newOrder.NewOrderService;
import pl.coderslab.catering2springboot.repository.*;

import javax.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Objects;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
@RequestMapping("/user")
@SessionAttributes({"userId", "name", "lastName", "superAdmin", "searchId", "searchLogin", "searchDepartmentId"})
public class UserController {

    public final DepartmentRepository departmentRepository;
    public final NewOrderService newOrderService;
    private final UserService userService;
    private final NewMenuService newMenuService;
    private final ConfigService configService;

    @GetMapping("/home")
    public String userHomeView(Model model, HttpSession session) {

        if (session.getAttribute("userId") != null) {
            User user = userService.getUserById((Long) session.getAttribute("userId"));
            model.addAttribute("name", user.getName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("editUserId", user.getUserId());
            if (configService.getConfig().getEditMode()) {
                return "/user/user-home-editmode";
            }

            NewOrder order = newOrderService.getNewOrderByUserId(user.getUserId());
            if (order != null && !order.getIsPaid()) {
                model.addAttribute("receivables", order.getToPay());
            } else {
                model.addAttribute("receivables", 0);
            }

            model.addAttribute("mealsMonday", newMenuService.newMenuFindByDayId(1));
            model.addAttribute("mealsTuesday", newMenuService.newMenuFindByDayId(2));
            model.addAttribute("mealsWednesday", newMenuService.newMenuFindByDayId(3));
            model.addAttribute("mealsThursday", newMenuService.newMenuFindByDayId(4));
            model.addAttribute("mealsFriday", newMenuService.newMenuFindByDayId(5));
            model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
            model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
            return "/user/user-home";
        }
        return "redirect:/";
    }










    @GetMapping("/auth")
    public String authenticate() {
        return "/user/user-auth";
    }

    @PostMapping("/auth")
    public String authenticate(@RequestParam String login, @RequestParam String password, Model model) {

        User user = userService.getUserByLogin(login);
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




    @GetMapping("/update")
    public String userUpdateView(@RequestParam Long editUserId, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            User user = userService.getUserById(editUserId);
            System.out.println(user.getPassword());
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("departments", departmentRepository.findAll());
            return "/user/user-update";
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String userUpdate(User user, HttpSession session) {
        System.out.println(user.toString());
        if (session.getAttribute("userId") != null) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userService.save(user);
            return "redirect:/logout";
        }
        return "redirect:/";
    }




}






