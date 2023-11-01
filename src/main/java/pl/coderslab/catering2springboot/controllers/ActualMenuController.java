package pl.coderslab.catering2springboot.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.entity.ActualMenu;
import pl.coderslab.catering2springboot.entity.ActualOrder;
import pl.coderslab.catering2springboot.entity.NewMenu;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.repository.ActualMenuRepository;
import pl.coderslab.catering2springboot.repository.ActualOrderRepository;
import pl.coderslab.catering2springboot.repository.NewMenuRepository;
import pl.coderslab.catering2springboot.repository.NewOrderRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
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

    @GetMapping("/admin/actualMenu")
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
            if (shift == null || shift == 1) {
                return "/admin/admin-dinner-ids-1shift";
            } else {
                return "/admin/admin-dinner-ids-2shift";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/admin/actualMenu/update")
    public String actualOrderUpdate(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            ModelMapper modelMapper = new ModelMapper();
            List<NewOrder> newOrdersList = newOrderRepository.findAll();

            for (NewOrder newOrder : newOrdersList) {
                ActualOrder actualOrder = new ActualOrder();
                actualOrder = modelMapper.map(newOrder, ActualOrder.class);
                actualOrderRepository.save(actualOrder);
            }

            List<NewMenu> allDinners = newMenuRepository.findAll();
            List<ActualOrder> allNewOrders = actualOrderRepository.findAll();

            for (NewMenu dinner : allDinners) {
                String idsFirstShift = "";
                Integer idsFirstShiftQty = 0;
                String idsSecondShift = "";
                Integer idsSecondShiftQty = 0;
                for (ActualOrder newOrder : allNewOrders) {
                    if (dinner.getMealNo().equals(newOrder.getMealMon())
                            || dinner.getMealNo().equals(newOrder.getMealTue())
                            || dinner.getMealNo().equals(newOrder.getMealWed())
                            || dinner.getMealNo().equals(newOrder.getMealThu())
                            || dinner.getMealNo().equals(newOrder.getMealFri())) {
                        if (newOrder.getShiftMon() == 1
                                || newOrder.getShiftTue() == 1
                                || newOrder.getShiftWed() == 1
                                || newOrder.getShiftThu() == 1
                                || newOrder.getShiftFri() == 1) {
                            idsFirstShift = idsFirstShift + newOrder.getUser().getUserId() + ", ";
                            idsFirstShiftQty++;
                        } else if (newOrder.getShiftMon() == 2
                                || newOrder.getShiftTue() == 2
                                || newOrder.getShiftWed() == 2
                                || newOrder.getShiftThu() == 2
                                || newOrder.getShiftFri() == 2) {
                            idsSecondShift = idsSecondShift + newOrder.getUser().getUserId() + ", ";
                            idsSecondShiftQty++;
                        }
                    }
                }
                ActualMenu actualMenu = new ActualMenu();
                actualMenu.setMealNo(dinner.getMealNo());
                actualMenu.setMealName(dinner.getMealName());
                actualMenu.setDayId(dinner.getDayId());
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
