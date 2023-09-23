package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.repository.MenuRepository;
import pl.coderslab.catering2springboot.repository.NewOrderRepository;

import java.util.Calendar;

@Controller
public class MenuController {
    private final MenuRepository menuRepository;
    private final NewOrderRepository newOrderRepository;

    public MenuController(MenuRepository menuRepository, NewOrderRepository newOrderRepository) {
        this.menuRepository = menuRepository;
        this.newOrderRepository = newOrderRepository;
    }

    @GetMapping
    public String mealsView(Model model) {
        model.addAttribute("mealsMonday", menuRepository.findByDayId(1));
        model.addAttribute("mealsTuesday", menuRepository.findByDayId(2));
        model.addAttribute("mealsWednesday", menuRepository.findByDayId(3));
        model.addAttribute("mealsThursday", menuRepository.findByDayId(4));
        model.addAttribute("mealsFriday", menuRepository.findByDayId(5));
        model.addAttribute("date", Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) + 1);
        return "home";
    }


    @GetMapping("/menu/neworder")
    public String newOrderView(Model model) {
        NewOrder newOrder = new NewOrder();
        newOrder.setUserQtyMon(1);
        newOrder.setUserQtyTue(1);
        newOrder.setUserQtyWed(1);
        newOrder.setUserQtyThu(1);
        newOrder.setUserQtyFri(1);
        System.out.println(newOrder);
        model.addAttribute("newOrder", newOrder);
        model.addAttribute("newMenuMonday", menuRepository.findByDayId(1));
        model.addAttribute("newMenuTuesday", menuRepository.findByDayId(2));
        model.addAttribute("newMenuWednesday", menuRepository.findByDayId(3));
        model.addAttribute("newMenuThursday", menuRepository.findByDayId(4));
        model.addAttribute("newMenuFriday", menuRepository.findByDayId(5));
        model.addAttribute("kw", Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) + 1);
        return "/menu/new-order";
    }

    @PostMapping("/menu/neworder")
    public String newOrder(NewOrder newOrder) {
        newOrderRepository.save(newOrder);
        return "redirect:/menu/neworder";
    }


}
