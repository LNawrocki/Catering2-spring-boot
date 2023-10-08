package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.catering2springboot.entity.ActualOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.ActualOrderRepository;
import pl.coderslab.catering2springboot.repository.NewOrderRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
public class ActualOrderController {
    public final ActualOrderRepository actualOrderRepository;
    public final UserRepository userRepository;
    public final NewOrderRepository newOrderRepository;

    public ActualOrderController(ActualOrderRepository actualOrderRepository, UserRepository userRepository, NewOrderRepository newOrderRepository) {
        this.actualOrderRepository = actualOrderRepository;
        this.userRepository = userRepository;
        this.newOrderRepository = newOrderRepository;
    }

    @GetMapping("/user/actualOrder")
    public String actualOrderView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            model.addAttribute("actualOrder", actualOrderRepository.getActualOrderByUserId((Long) session.getAttribute("userId")));

            if ((Boolean) session.getAttribute("superAdmin")) {
                return "/user/actual-order-admin";
            } else {
                return "/user/actual-order-user";
            }
        }
        return "redirect:/";
    }
}