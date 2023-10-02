package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.NewOrderRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

@Controller
public class NewOrderController {
    public final NewOrderRepository newOrderRepository;
    public final UserRepository userRepository;

    public NewOrderController(NewOrderRepository newOrderRepository, UserRepository userRepository) {
        this.newOrderRepository = newOrderRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/admin/newOrder/check")
    public String NewOrderAdminCheckView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            User user = userRepository.getByUserId((Long) session.getAttribute("userId"));
            NewOrder order = newOrderRepository.getNewOrderByUserId(user.getUserId());

            model.addAttribute("newOrder", newOrderRepository.getNewOrderByUserId((Long) session.getAttribute("userId")));
            model.addAttribute("date", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            if (order != null && !order.getIsPaid()) {
                model.addAttribute("receivables", order.getToPay());
            } else {
                model.addAttribute("receivables", 0);
            }

            return "/menu/new-order-admin-check";
        }
        session.invalidate();
        return "redirect:/";
    }
}
