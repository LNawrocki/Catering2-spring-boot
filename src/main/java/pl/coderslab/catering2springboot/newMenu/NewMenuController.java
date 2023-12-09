package pl.coderslab.catering2springboot.newMenu;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.dish.DishService;
import pl.coderslab.catering2springboot.newOrder.NewOrderService;
import pl.coderslab.catering2springboot.price.PriceService;

import javax.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

@Controller
@AllArgsConstructor
public class NewMenuController {

    private final NewMenuService newMenuService;
    private final PriceService priceService;
    private final DishService dishService;
    private final NewOrderService newOrderService;

    @GetMapping("/admin/newMenu/edit")
    public String newMenuEditView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            model.addAttribute("newMenu", new NewMenu());
            model.addAttribute("mealsMonday", newMenuService.newMenuFindByDayId(1));
            model.addAttribute("mealsTuesday", newMenuService.newMenuFindByDayId(2));
            model.addAttribute("mealsWednesday", newMenuService.newMenuFindByDayId(3));
            model.addAttribute("mealsThursday", newMenuService.newMenuFindByDayId(4));
            model.addAttribute("mealsFriday", newMenuService.newMenuFindByDayId(5));
            model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
            model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
            model.addAttribute("prices", priceService.findAll());
            model.addAttribute("dishes", dishService.findAll());

            if (newOrderService.getQuantityOfNewOrders() == 0) {
                model.addAttribute("deleteButtonVisible", true);
            } else {
                model.addAttribute("deleteButtonVisible", false);
            }
            return "/menu/new-menu-edit";
        }
        return "redirect:/";
    }

    //TODO: Validacja - brak pustych pól

    @PostMapping("/admin/newMenu/addMeal")
    public String newMenuAdd(NewMenu newMenu, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            newMenuService.save(newMenu);
            return "redirect:/admin/newMenu/edit";
        }
        return "redirect:/";
    }

    @PostMapping("/admin/newMenu/deleteMeal")
    public String deleteMenu(@RequestParam Integer mealNo, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            newMenuService.deleteMeal(mealNo);
            return "redirect:/admin/newMenu/edit";
        }
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/admin/newMenu/deleteDayMeals")
    public String deleteMenuDay(@RequestParam Integer dayId, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            newMenuService.deleteByDayNo(dayId);
            return "redirect:/admin/newMenu/edit";
        }
        session.invalidate();
        return "redirect:/";
    }

}

