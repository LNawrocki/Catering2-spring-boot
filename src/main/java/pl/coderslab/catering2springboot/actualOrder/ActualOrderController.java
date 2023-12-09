package pl.coderslab.catering2springboot.actualOrder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.catering2springboot.user.User;
import pl.coderslab.catering2springboot.user.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
public class ActualOrderController {
    private final ActualOrderRepository actualOrderRepository;
    private final UserRepository userRepository;


    public ActualOrderController(ActualOrderRepository actualOrderRepository, UserRepository userRepository) {
        this.actualOrderRepository = actualOrderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/actualOrder")
    public String actualOrderView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            User user = userRepository.getByUserId((Long) session.getAttribute("userId"));
            model.addAttribute("name", user.getName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("editUserId", user.getUserId());
            model.addAttribute("actualOrder",
                    actualOrderRepository.getActualOrderByUser_UserId((Long) session.getAttribute("userId")));
            if ((Boolean) session.getAttribute("superAdmin")) {
                return "/user/actual-order-admin";
            } else {
                return "/user/actual-order-user";
            }
        }
        return "redirect:/";
    }
}