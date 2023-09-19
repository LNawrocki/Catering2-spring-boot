package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.catering2springboot.repository.MealRepository;


import java.util.Calendar;

@Controller
@SessionAttributes({"userId", "superAdmin"})
public class ViewController {

    private final MealRepository mealRepository;

    public ViewController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }


    @GetMapping
    public String mealsView(Model model) {
        model.addAttribute("mealsMonday", mealRepository.findByDayId(1));
        model.addAttribute("mealsTuesday", mealRepository.findByDayId(2));
        model.addAttribute("mealsWednesday", mealRepository.findByDayId(3));
        model.addAttribute("mealsThursday", mealRepository.findByDayId(4));
        model.addAttribute("mealsFriday", mealRepository.findByDayId(5));
        model.addAttribute("date", Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) + 1);
        return "home";
    }

}

