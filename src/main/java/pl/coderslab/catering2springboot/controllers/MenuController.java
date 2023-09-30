package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.entity.NewMenu;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.NewMenuRepository;
import pl.coderslab.catering2springboot.repository.NewOrderRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;


@Controller

public class MenuController {
    private final NewMenuRepository newMenuRepository;
    private final NewOrderRepository newOrderRepository;
    private final UserRepository userRepository;

    public MenuController(NewMenuRepository newMenuRepository, NewOrderRepository newOrderRepository, UserRepository userRepository) {
        this.newMenuRepository = newMenuRepository;
        this.newOrderRepository = newOrderRepository;
        this.userRepository = userRepository;
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

    @GetMapping("/menu/newOrder")
    public String newOrderView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
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
                return "redirect:/admin/home";
            } else {
                return "redirect:/user/home";
            }
        }
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/admin/menu/update")
    public String updateMenuView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            model.addAttribute("newMenu", new NewMenu());

            model.addAttribute("mealsMonday", newMenuRepository.findByDayId(1));
            model.addAttribute("mealsTuesday", newMenuRepository.findByDayId(2));
            model.addAttribute("mealsWednesday", newMenuRepository.findByDayId(3));
            model.addAttribute("mealsThursday", newMenuRepository.findByDayId(4));
            model.addAttribute("mealsFriday", newMenuRepository.findByDayId(5));
            model.addAttribute("date", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            return "/menu/menu-update";
        }
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/admin/menu/update")
    public String updateMenu(NewMenu newMenu, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            newMenuRepository.save(newMenu);
            return "redirect:/admin/menu/update";
        }
        session.invalidate();
        return "redirect:/";
    }

    //do poprawy - metoda get nie powinna zmieniać stanu bazy danych
    @GetMapping("/menu/delete")
    public String deleteMenu(@RequestParam Integer mealNo, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            System.out.println(mealNo);
            newMenuRepository.deleteByMealNo(mealNo);
            return "redirect:/admin/menu/update";
        }
        session.invalidate();
        return "redirect:/";
    }

    //do poprawy - metoda get nie powinna zmieniać stanu bazy danych
    @GetMapping("/menu/deleteDay")
    public String deleteMenuDay(@RequestParam Integer dayId, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            newMenuRepository.deleteByDayNo(dayId);
            return "redirect:/admin/menu/update";
        }
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/admin/order/list")
    public String orderListView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            List<NewOrder> newOrders = newOrderRepository.findAll(); // pobranie wszystkich nowych zamówień
            List<String> mealsNames = new ArrayList<>();
            for (NewOrder newOrder : newOrders) {
                mealsNames.add(newMenuRepository.findByMealNo(newOrder.getMealMon()).getMealName());
                mealsNames.add(newMenuRepository.findByMealNo(newOrder.getMealTue()).getMealName());
                mealsNames.add(newMenuRepository.findByMealNo(newOrder.getMealWed()).getMealName());
                mealsNames.add(newMenuRepository.findByMealNo(newOrder.getMealThu()).getMealName());
                mealsNames.add(newMenuRepository.findByMealNo(newOrder.getMealFri()).getMealName());
            }
            model.addAttribute("newOrders", newOrderRepository.findAll());
            return "/menu/order-list";
        }
        session.invalidate();
        return "redirect:/";
    }
}
