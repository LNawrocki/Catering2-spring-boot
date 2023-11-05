package pl.coderslab.catering2springboot.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.entity.*;
import pl.coderslab.catering2springboot.repository.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ActualMenuController {

    private final ActualMenuRepository actualMenuRepository;
    private final ActualOrderRepository actualOrderRepository;
    private final NewMenuRepository newMenuRepository;
    private final NewOrderRepository newOrderRepository;

    public ActualMenuController(ActualMenuRepository actualMenuRepository,
                                ActualOrderRepository actualOrderRepository,
                                NewMenuRepository newMenuRepository,
                                NewOrderRepository newOrderRepository) {
        this.actualMenuRepository = actualMenuRepository;
        this.actualOrderRepository = actualOrderRepository;
        this.newMenuRepository = newMenuRepository;
        this.newOrderRepository = newOrderRepository;
    }

    @GetMapping("/actualMenu")
    public String actualOrderView(@RequestParam(required = false) Integer shift, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            List<ActualMenu> actualMenuList = actualMenuRepository.findAll();
            List<Integer> mealsQtyPerDayFirstShift = new ArrayList<>();
            List<Integer> mealsQtyPerDaySecondShift = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                Integer sumPerDayFirstShift = 0;
                Integer sumPerDaySecondShift = 0;
                for (ActualMenu actualMenu : actualMenuRepository.findActualMenusByDayId(i)) {
                    if (!actualMenu.getMealName().equals("Brak")) {
                        sumPerDayFirstShift = sumPerDayFirstShift + actualMenu.getFirstShiftQuantity();
                        sumPerDaySecondShift = sumPerDaySecondShift + actualMenu.getSecondShiftQuantity();
                    }
                }
                mealsQtyPerDayFirstShift.add(sumPerDayFirstShift);
                mealsQtyPerDaySecondShift.add(sumPerDaySecondShift);
            }
            model.addAttribute("mealsQtyPerDayFirstShift", mealsQtyPerDayFirstShift);
            model.addAttribute("mealsQtyPerDaySecondShift", mealsQtyPerDaySecondShift);
            model.addAttribute("actualMenuList", actualMenuList);
            model.addAttribute("shift", shift);

            if (actualMenuRepository.findAll().isEmpty()) {
                model.addAttribute("kw", "");
            } else {
                model.addAttribute("kw", actualMenuRepository.findAll().get(0).getKw());
            }
            if (shift == null || shift == 1) {
                return "/admin/admin-dinner-ids-1shift";
            } else {
                return "/admin/admin-dinner-ids-2shift";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/actualMenu/update")
    public String actualOrderUpdate(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            ModelMapper modelMapper = new ModelMapper();
            List<NewOrder> newOrdersList = newOrderRepository.findAll();

            for (NewOrder newOrder : newOrdersList) {
                ActualOrder actualOrder;
                actualOrder = modelMapper.map(newOrder, ActualOrder.class);
                actualOrderRepository.save(actualOrder);
            }

            List<NewMenu> newMenuMeals = newMenuRepository.findAll();
            List<ActualOrder> actualOrderMeals = actualOrderRepository.findAll();

            for (NewMenu newMenuMeal : newMenuMeals) {
                String idsFirstShift = "";
                Integer idsFirstShiftQty = 0;
                String idsSecondShift = "";
                Integer idsSecondShiftQty = 0;
                for (ActualOrder actualOrderMeal : actualOrderMeals) {
                    if (newMenuMeal.getMealNo().equals(actualOrderMeal.getMealMon())
                            || newMenuMeal.getMealNo().equals(actualOrderMeal.getMealTue())
                            || newMenuMeal.getMealNo().equals(actualOrderMeal.getMealWed())
                            || newMenuMeal.getMealNo().equals(actualOrderMeal.getMealThu())
                            || newMenuMeal.getMealNo().equals(actualOrderMeal.getMealFri())) {
                        if (actualOrderMeal.getShiftMon() == 1
                                || actualOrderMeal.getShiftTue() == 1
                                || actualOrderMeal.getShiftWed() == 1
                                || actualOrderMeal.getShiftThu() == 1
                                || actualOrderMeal.getShiftFri() == 1) {
                            idsFirstShift = idsFirstShift + actualOrderMeal.getUser().getUserId() + ", ";
                            idsFirstShiftQty++;
                        } else if (actualOrderMeal.getShiftMon() == 2
                                || actualOrderMeal.getShiftTue() == 2
                                || actualOrderMeal.getShiftWed() == 2
                                || actualOrderMeal.getShiftThu() == 2
                                || actualOrderMeal.getShiftFri() == 2) {
                            idsSecondShift = idsSecondShift + actualOrderMeal.getUser().getUserId() + ", ";
                            idsSecondShiftQty++;
                        }
                    }
                }
                ActualMenu actualMenu = new ActualMenu();
                actualMenu.setMealNo(newMenuMeal.getMealNo());
                actualMenu.setMealName(newMenuMeal.getMealName());
                actualMenu.setDayId(newMenuMeal.getDayId());
                actualMenu.setKw(newMenuMeal.getKw());
                actualMenu.setFirstShiftQuantity(idsFirstShiftQty);
                actualMenu.setFirstShiftUsersId(idsFirstShift);
                actualMenu.setSecondShiftQuantity(idsSecondShiftQty);
                actualMenu.setSecondShiftUsersId(idsSecondShift);
                actualMenuRepository.save(actualMenu);
            }
            return "redirect:/admin/actualMenu";
        }
        return "redirect:/";
    }
}
