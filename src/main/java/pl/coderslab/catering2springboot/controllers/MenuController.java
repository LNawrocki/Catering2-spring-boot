package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.entity.NewMenu;
import pl.coderslab.catering2springboot.repository.NewMenuRepository;
import pl.coderslab.catering2springboot.repository.NewOrderRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.WeekFields;


@Controller

public class MenuController {
    private final NewMenuRepository newMenuRepository;


    public MenuController(NewMenuRepository newMenuRepository) {
        this.newMenuRepository = newMenuRepository;
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
        return "redirect:/";
    }

    @PostMapping("/admin/menu/update")
    public String updateMenu(NewMenu newMenu, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            newMenuRepository.save(newMenu);
            return "redirect:/admin/menu/update";
        }
        return "redirect:/";
    }

                        //do poprawy - metoda get nie powinna zmieniać stanu bazy danych, zmian na post i miniformularz
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

                        //do poprawy - metoda get nie powinna zmieniać stanu bazy danych, zmian na post i miniformularz
                        @GetMapping("/menu/deleteDay")
                        public String deleteMenuDay(@RequestParam Integer dayId, HttpSession session) {
                            if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
                                newMenuRepository.deleteByDayNo(dayId);
                                return "redirect:/admin/menu/update";
                            }
                            session.invalidate();
                            return "redirect:/";
                        }
}
