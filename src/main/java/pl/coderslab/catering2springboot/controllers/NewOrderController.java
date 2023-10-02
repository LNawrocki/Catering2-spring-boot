package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.catering2springboot.entity.NewMenu;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.NewMenuRepository;
import pl.coderslab.catering2springboot.repository.NewOrderRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;

@Controller
public class NewOrderController {
    public final NewOrderRepository newOrderRepository;
    public final UserRepository userRepository;
    public final NewMenuRepository newMenuRepository;

    public NewOrderController(NewOrderRepository newOrderRepository, UserRepository userRepository, NewMenuRepository newMenuRepository) {
        this.newOrderRepository = newOrderRepository;
        this.userRepository = userRepository;
        this.newMenuRepository = newMenuRepository;
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

    @GetMapping("/menu/newOrder")
    public String newOrderView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            if (newOrderRepository.getNewOrderByUserId((Long) session.getAttribute("userId")) != null){
                return "redirect:/admin/newOrder/check";
            }

            User user = userRepository.getByUserId((Long) session.getAttribute("userId"));
            BigDecimal paymentPerc = BigDecimal.valueOf(user.getDepartment().getPaymentPerc());

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

            NewOrder order = newOrderRepository.getNewOrderByUserId(user.getUserId());
            if (order != null && !order.getIsPaid()) {
                model.addAttribute("receivables", order.getToPay());
            } else {
                model.addAttribute("receivables", 0);
            }
            if ((Boolean) session.getAttribute("superAdmin")) {
                return "/menu/new-order-admin";
            } else {
                return "/menu/new-order-user";
            }
        }
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/menu/newOrder")
    public String newOrder(NewOrder newOrder, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {



            User user = userRepository.getByUserId(newOrder.getUser().getUserId());
            BigDecimal paymentPerc = BigDecimal.valueOf(user.getDepartment().getPaymentPerc());

            NewMenu mealMonday = newMenuRepository.findByMealNo(newOrder.getMealMon());
            NewMenu mealTuesday = newMenuRepository.findByMealNo(newOrder.getMealTue());
            NewMenu mealWednesday = newMenuRepository.findByMealNo(newOrder.getMealWed());
            NewMenu mealThursday = newMenuRepository.findByMealNo(newOrder.getMealThu());
            NewMenu mealFriday = newMenuRepository.findByMealNo(newOrder.getMealFri());

            newOrder.setPriceMon(mealMonday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
            newOrder.setPriceTue(mealTuesday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
            newOrder.setPriceWed(mealWednesday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
            newOrder.setPriceThu(mealThursday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
            newOrder.setPriceFri(mealFriday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));

            newOrder.setMealMonName(newMenuRepository.findByMealNo(newOrder.getMealMon()).getMealName());
            newOrder.setMealTueName(newMenuRepository.findByMealNo(newOrder.getMealTue()).getMealName());
            newOrder.setMealWedName(newMenuRepository.findByMealNo(newOrder.getMealWed()).getMealName());
            newOrder.setMealThuName(newMenuRepository.findByMealNo(newOrder.getMealThu()).getMealName());
            newOrder.setMealFriName(newMenuRepository.findByMealNo(newOrder.getMealFri()).getMealName());

            newOrder.setToPay(newOrder.getPriceMon().add(newOrder.getPriceTue()).add(newOrder.getPriceWed())
                    .add(newOrder.getPriceThu()).add(newOrder.getPriceFri()));

            newOrderRepository.save(newOrder);

            if ((Boolean) session.getAttribute("superAdmin")) {
                return "redirect:/admin/newOrder/check";
            } else {
                return "redirect:/user/home";
            }
        }
        session.invalidate();
        return "redirect:/";
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
