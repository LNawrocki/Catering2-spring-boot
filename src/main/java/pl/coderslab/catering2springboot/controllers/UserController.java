package pl.coderslab.catering2springboot.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.DepartmentRepository;
import pl.coderslab.catering2springboot.repository.MenuRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Objects;

//@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
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
                newOrder.setUserId(user.getUserId());
                model.addAttribute("newOrder", newOrder);
                model.addAttribute("newMenuMonday", menuRepository.findByDayId(1));
                model.addAttribute("newMenuTuesday", menuRepository.findByDayId(2));
                model.addAttribute("newMenuWednesday", menuRepository.findByDayId(3));
                model.addAttribute("newMenuThursday", menuRepository.findByDayId(4));
                model.addAttribute("newMenuFriday", menuRepository.findByDayId(5));
                model.addAttribute("userId", user.getUserId());
                model.addAttribute("kw", Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) + 1);
                return "/menu/new-order";
            }
        }
        return "redirect:/user/auth";
    }
}
