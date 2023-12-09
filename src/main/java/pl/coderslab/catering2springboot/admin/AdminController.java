package pl.coderslab.catering2springboot.admin;

import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.actualOrder.ActualOrderService;
import pl.coderslab.catering2springboot.department.DepartmentService;
import pl.coderslab.catering2springboot.actualOrder.ActualOrder;
import pl.coderslab.catering2springboot.newOrder.NewOrder;
import pl.coderslab.catering2springboot.newOrder.NewOrderService;
import pl.coderslab.catering2springboot.user.User;
import pl.coderslab.catering2springboot.user.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final DepartmentService departmentService;
    private final UserService userService;
    private final NewOrderService newOrderService;
    private final ActualOrderService actualOrderService;

    @GetMapping("/userList")
    public String userList(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            model.addAttribute("departments", departmentService.findAll());

            if (session.getAttribute("searchId") != null && session.getAttribute("searchId") != "") {
                Long searchUserId = Long.parseLong((String) session.getAttribute("searchId"));
                User user = userService.getUserById(searchUserId);
                List<User> users = new ArrayList<>();
                users.add(user);
                model.addAttribute("usersList", users);
                model.addAttribute("searchId", null);
                return "/user/user-list";
            }

            if (session.getAttribute("searchLogin") != null && session.getAttribute("searchLogin") != "") {
                String login = (String) session.getAttribute("searchLogin");
                User user = userService.getUserByLogin(login);
                List<User> users = new ArrayList<>();
                users.add(user);
                model.addAttribute("usersList", users);
                model.addAttribute("searchLogin", null);
                return "/user/user-list";
            }

            if (session.getAttribute("searchDepartmentId") != null && session.getAttribute("searchDepartmentId") != "") {
                List<User> users = userService.findUsersByDepartment(departmentService.getById((Integer) session.getAttribute("searchDepartmentId")));
                model.addAttribute("usersList", users);
                return "/user/user-list";
            }

            model.addAttribute("usersList", userService.findAll());
            return "/user/user-list";
        }
        return "redirect:/";
    }

    @PostMapping("/userList/searchId")
    public String userListId(@RequestParam String searchId, Model model) {
        model.addAttribute("searchId", searchId);
        return "redirect:/admin/list";
    }

    @PostMapping("/userList/searchLogin")
    public String userListLogin(@RequestParam String searchLogin, Model model) {
        model.addAttribute("searchLogin", searchLogin);
        return "redirect:/admin/list";
    }

    @PostMapping("/userList/searchDepartment")
    public String userListDepartment(@RequestParam Integer searchDepartmentId, Model model) {
        model.addAttribute("searchDepartmentId", searchDepartmentId);
        return "redirect:/admin/list";
    }

    @PostMapping("/userList/searchClean")
    public String userListClean(Model model) {
        model.addAttribute("searchId", "");
        model.addAttribute("searchLogin", "");
        model.addAttribute("searchDepartmentId", "");
        return "redirect:/admin/list";
    }

    @GetMapping("/addUser")
    public String addView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            model.addAttribute("user", new User());
            model.addAttribute("departments", departmentService.findAll());
            return "/user/user-add";
        }
        return "redirect:/";
    }

    @PostMapping("/addUser")
    public String add(@Valid User user, BindingResult bindingResult, HttpSession session, Model model) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            if (Objects.nonNull(userService.getUserById(user.getUserId()))) {
                model.addAttribute("msg", "Użytkownik o podanym numere już istnieje");
                return "admin/admin-user-add-info-exist";
            }

            if (Objects.nonNull(userService.getUserByLogin(user.getLogin()))) {
                model.addAttribute("msg", "Użytkownik o podanym loginie już istnieje");
                return "admin/admin-user-add-info-exist";
            }

            if (bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getFieldErrors().stream()
                        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
                return "/user/user-add";
            }
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userService.save(user);
            return "redirect:/admin/list";
        }
        return "redirect:/";
    }
    @PostMapping("/deleteUser/confirm")
    public String deleteConfirm(@RequestParam Long deleteUserId, @RequestParam(required = false) Boolean confirm, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            User user = userService.getUserById(deleteUserId);

            if (user.getUserId() == session.getAttribute("userId")) {
                return "/admin/admin-delete-info";
            }

            if (user.getSuperAdmin()) {
                model.addAttribute("departments", departmentService.findAll());
                return "/admin/admin-delete-info";
            }

            ActualOrder actualOrderByUserId = actualOrderService.getActualOrderByUserId(deleteUserId);
            NewOrder newOrder = newOrderService.getNewOrderByUserId(deleteUserId);
            if ((actualOrderByUserId != null || newOrder != null) && Objects.isNull(confirm)) {
                model.addAttribute("deleteUserId", deleteUserId);
                return "/user/user-list-delete-info";
            }

            if (actualOrderByUserId != null) {
                actualOrderService.delete(actualOrderByUserId);
            }

            if (newOrder != null) {
                newOrderService.delete(newOrder);
            }

            userService.delete(user);
            model.addAttribute("usersList", userService.findAll());
            model.addAttribute("departments", departmentService.findAll());
            return "redirect:/admin/userList";
        }
        return "redirect:/";
    }


    @GetMapping("/userUpdate")
    public String adminUpdateView(@RequestParam Long editUserId, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            User user = userService.getUserById(editUserId);
            model.addAttribute("user", user);
            model.addAttribute("departments", departmentService.findAll());
            return "/admin/admin-update";
        }
        return "redirect:/";
    }

    @PostMapping("/userUpdate")
    public String adminUpdate(User user, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            if (userService.getUserById(user.getUserId()).getPassword().equals(user.getPassword())) {
                userService.save(user);
                return "redirect:/admin/userList";
            }
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userService.save(user);
            return "redirect:/admin/userList";
        }
        return "redirect:/";
    }

}
