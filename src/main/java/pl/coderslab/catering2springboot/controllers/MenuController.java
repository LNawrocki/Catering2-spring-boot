package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.repository.MenuRepository;
import pl.coderslab.catering2springboot.repository.NewOrderRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.WeekFields;



@Controller

public class MenuController {
    private final MenuRepository menuRepository;
    private final NewOrderRepository newOrderRepository;
    private final UserRepository userRepository;

    public MenuController(MenuRepository menuRepository, NewOrderRepository newOrderRepository, UserRepository userRepository) {
        this.menuRepository = menuRepository;
        this.newOrderRepository = newOrderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String mealsView(Model model) {
        model.addAttribute("mealsMonday", menuRepository.findByDayId(1));
        model.addAttribute("mealsTuesday", menuRepository.findByDayId(2));
        model.addAttribute("mealsWednesday", menuRepository.findByDayId(3));
        model.addAttribute("mealsThursday", menuRepository.findByDayId(4));
        model.addAttribute("mealsFriday", menuRepository.findByDayId(5));
        model.addAttribute("date", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
        return "home";
    }

    @PostMapping("/menu/newOrder")
    public String newOrder(NewOrder newOrder) {

//        menuRepository.findByMealNo(newOrder.getUserMealMon()).getMealPrice(); // pobranie ceny posiłku wybranego przez użytkownika
//        newOrder.getUser().getUserId(); // pobranie id użytkownika z formularza w celu przeliczenia ceny wybranych potraw

//        newOrder.setUserPriceMon(BigInteger.valueOf(5));
//        newOrder.setUserPriceTue();
//        newOrder.setUserPriceWed();
//        newOrder.setUserPriceThu();
//        newOrder.setUserPriceFri();

        newOrderRepository.save(newOrder);
        return "redirect:/";
    }
}
