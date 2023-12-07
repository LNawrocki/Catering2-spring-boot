package pl.coderslab.catering2springboot.dish;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class DishController {

    private final DishService dishService;

    @GetMapping("/dish")
    public String dishesView(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("dishes", dishService.findAll());
        return "/admin/dish-list";
    }

    @PostMapping("/dish/delete")
    public String dishDelete(@RequestParam Integer deleteDishId){
        dishService.deleteByDishId(deleteDishId);
        return "redirect:/admin/dish";
    }



}
