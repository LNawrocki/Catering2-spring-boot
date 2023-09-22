package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.catering2springboot.repository.MenuRepository;


import java.util.Calendar;

@Controller
public class HomeController {

    private final MenuRepository menuRepository;

    public HomeController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
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

}

