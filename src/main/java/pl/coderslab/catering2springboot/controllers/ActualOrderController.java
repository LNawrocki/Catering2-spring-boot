package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.catering2springboot.repository.ActualOrderRepository;

import javax.servlet.http.HttpSession;

@Controller
public class ActualOrderController {
    private final ActualOrderRepository actualOrderRepository;


    public ActualOrderController(ActualOrderRepository actualOrderRepository) {
        this.actualOrderRepository = actualOrderRepository;

    }

    @GetMapping("/user/actualOrder")
    public String actualOrderView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null) {
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