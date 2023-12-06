package pl.coderslab.catering2springboot.home;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.catering2springboot.config.ConfigService;
import pl.coderslab.catering2springboot.newMenu.NewMenuService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

@Controller
@AllArgsConstructor
public class HomeController {

private final ConfigService configService;
private final NewMenuService newMenuService;

    @GetMapping
    public String homeView(Model model) {
        if (configService.editModeStatus()) {
            model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
            model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
            return "home-editmode";
        }
        model.addAttribute("mealsMonday", newMenuService.newMenuFindByDayId(1));
        model.addAttribute("mealsTuesday", newMenuService.newMenuFindByDayId(2));
        model.addAttribute("mealsWednesday", newMenuService.newMenuFindByDayId(3));
        model.addAttribute("mealsThursday", newMenuService.newMenuFindByDayId(4));
        model.addAttribute("mealsFriday", newMenuService.newMenuFindByDayId(5));
        model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
        model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
        model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
        return "home";
    }
}
