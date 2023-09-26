package pl.coderslab.catering2springboot.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.catering2springboot.entity.Department;
import pl.coderslab.catering2springboot.entity.NewMenu;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.DepartmentRepository;
import pl.coderslab.catering2springboot.repository.MenuRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
@SessionAttributes({"userId", "name", "lastName", "superAdmin"})
public class UserController {

    public final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    public UserController(UserRepository userRepository, DepartmentRepository departmentRepository, MenuRepository menuRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.menuRepository = menuRepository;
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
        System.out.println(user);
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
        Long userId = user.getUserId();
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("name", user.getName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("superAdmin", user.getSuperAdmin());
        BigDecimal paymentPerc  = BigDecimal.valueOf(user.getDepartment().getPaymentPerc());

        List<NewMenu> menuMonday = menuRepository.findByDayId(1);
        List<NewMenu> menuTuesday = menuRepository.findByDayId(2);
        List<NewMenu> menuWednesday = menuRepository.findByDayId(3);
        List<NewMenu> menuThursday = menuRepository.findByDayId(4);
        List<NewMenu> menuFriday = menuRepository.findByDayId(5);

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


        if (Objects.nonNull(user)) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                int kw = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) + 1;
                NewOrder newOrder = new NewOrder();
                newOrder.setUserQtyMon(1);
                newOrder.setUserPriceMon(BigInteger.valueOf(0));
                newOrder.setUserQtyTue(1);
                newOrder.setUserPriceTue(BigInteger.valueOf(0));
                newOrder.setUserQtyWed(1);
                newOrder.setUserPriceWed(BigInteger.valueOf(0));
                newOrder.setUserQtyThu(1);
                newOrder.setUserPriceMon(BigInteger.valueOf(0));
                newOrder.setUserQtyFri(1);
                newOrder.setKw(kw);
                newOrder.setUser(user);
                model.addAttribute("newOrder", newOrder);
                model.addAttribute("newMenuMonday", menuMonday);
                model.addAttribute("newMenuTuesday", menuTuesday);
                model.addAttribute("newMenuWednesday", menuWednesday);
                model.addAttribute("newMenuThursday", menuThursday);
                model.addAttribute("newMenuFriday", menuFriday);
                model.addAttribute("userId", user.getUserId());
                model.addAttribute("date", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
                return "/menu/new-order";
            }
        }
        return "redirect:/user/auth";
    }
}
