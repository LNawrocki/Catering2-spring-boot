package pl.coderslab.catering2springboot.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.catering2springboot.entity.NewMenu;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.DepartmentRepository;
import pl.coderslab.catering2springboot.repository.NewMenuRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Objects;


//@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
@SessionAttributes({"userId", "name", "lastName", "superAdmin"})
public class UserController {

    public final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final NewMenuRepository newMenuRepository;

    public UserController(UserRepository userRepository, DepartmentRepository departmentRepository, NewMenuRepository newMenuRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.newMenuRepository = newMenuRepository;
    }

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("usersList", userRepository.findAll());
        return "/user/user-list";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("departments", departmentRepository.findAll());
        return "/user/user-add";
    }

    @PostMapping("/add")
    public String add(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/update")
    public String updateView(@RequestParam Long userId, Model model) {
        User user = userRepository.getByUserId(userId);
        user.setPassword("");
        model.addAttribute("user", user);
        model.addAttribute("departments", departmentRepository.findAll());
        return "/user/user-update";
    }

    @PostMapping("/update")
    public String update(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/delete")
    public String update(@RequestParam Long userId, Model model, User user) {
        userRepository.delete(user);
        model.addAttribute("usersList", userRepository.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/auth")
    public String authenticate() {
        return "/user/user-auth";
    }

    @PostMapping("/auth")
    public String authenticate(@RequestParam String login,
                               @RequestParam String password, Model model) {

        User user = userRepository.getByLogin(login);
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("name", user.getName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("superAdmin", user.getSuperAdmin());

        if (Objects.nonNull(user)) {
            if (BCrypt.checkpw(password, user.getPassword())) {

                BigDecimal paymentPerc  = BigDecimal.valueOf(user.getDepartment().getPaymentPerc());

                List<NewMenu> menuMonday = newMenuRepository.findByDayId(1);
                List<NewMenu> menuTuesday = newMenuRepository.findByDayId(2);
                List<NewMenu> menuWednesday = newMenuRepository.findByDayId(3);
                List<NewMenu> menuThursday = newMenuRepository.findByDayId(4);
                List<NewMenu> menuFriday = newMenuRepository.findByDayId(5);

                menuMonday
                        .forEach(e -> {
                            e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                            e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                        });
                menuTuesday
                        .forEach(e -> {
                            e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                            e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                        });
                menuWednesday
                        .forEach(e -> {
                            e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                            e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                        });
                menuThursday
                        .forEach(e -> {
                            e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                            e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                        });
                menuFriday
                        .forEach(e -> {
                            e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                            e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                        });


                int kw = LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1;
                NewOrder newOrder = getNewOrder(kw, user);
                model.addAttribute("newOrder", newOrder);
                model.addAttribute("newMenuMonday", menuMonday);
                model.addAttribute("newMenuTuesday", menuTuesday);
                model.addAttribute("newMenuWednesday", menuWednesday);
                model.addAttribute("newMenuThursday", menuThursday);
                model.addAttribute("newMenuFriday", menuFriday);
                model.addAttribute("userId", user.getUserId());
                model.addAttribute("date", kw);
                return "/menu/new-order";
            }
        }
        return "redirect:/user/auth";
    }

    private static NewOrder getNewOrder(int kw, User user) {
        NewOrder newOrder = new NewOrder();
        newOrder.setQtyMon(1);
        newOrder.setQtyTue(1);
        newOrder.setQtyWed(1);
        newOrder.setQtyThu(1);
        newOrder.setQtyFri(1);
        newOrder.setShiftMon(0);
        newOrder.setShiftTue(0);
        newOrder.setShiftWed(0);
        newOrder.setShiftThu(0);
        newOrder.setShiftFri(0);

        newOrder.setKw(kw);
        newOrder.setUser(user);
        newOrder.setToPay(BigDecimal.valueOf(0));
        newOrder.setIsPaid(false);
        return newOrder;
    }
}
