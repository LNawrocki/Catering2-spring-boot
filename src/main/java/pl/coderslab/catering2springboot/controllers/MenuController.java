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



    //DO poprawy - metoda get i zmiana
    @GetMapping("/admin/newOrder/delete")
    public String newOrderDelete(@RequestParam Long id){
        NewOrder newOrder = newOrderRepository.getNewOrderById(id);
        newOrderRepository.delete(newOrder);
        return "/menu/order-list";
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
