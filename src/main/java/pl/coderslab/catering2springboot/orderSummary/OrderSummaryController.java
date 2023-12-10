package pl.coderslab.catering2springboot.orderSummary;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.actualOrder.ActualOrder;
import pl.coderslab.catering2springboot.actualOrder.ActualOrderRepository;
import pl.coderslab.catering2springboot.config.Config;
import pl.coderslab.catering2springboot.config.ConfigService;
import pl.coderslab.catering2springboot.newMenu.NewMenu;
import pl.coderslab.catering2springboot.newMenu.NewMenuRepository;
import pl.coderslab.catering2springboot.newOrder.NewOrder;
import pl.coderslab.catering2springboot.newOrder.NewOrderRepository;
import pl.coderslab.catering2springboot.newOrder.NewOrderService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class OrderSummaryController {

    private final OrderSummaryRepository orderSummaryRepository;
    private final OrderSummaryService orderSummaryService;



    @GetMapping("/orderSummary")
    public String actualOrderView(@RequestParam(required = false) Integer shift, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

            List<OrderSummary> orderSummaryList = orderSummaryRepository.findAll();
            List<Integer> mealsQtyPerDayFirstShift = new ArrayList<>();
            List<Integer> mealsQtyPerDaySecondShift = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                Integer sumPerDayFirstShift = 0;
                Integer sumPerDaySecondShift = 0;
                for (OrderSummary orderSummary : orderSummaryRepository.findOrderSummaryByDayId(i)) {
                    if (!orderSummary.getMealName().equals("Brak")) {
                        sumPerDayFirstShift = sumPerDayFirstShift + orderSummary.getFirstShiftQuantity();
                        sumPerDaySecondShift = sumPerDaySecondShift + orderSummary.getSecondShiftQuantity();
                    }
                }
                mealsQtyPerDayFirstShift.add(sumPerDayFirstShift);
                mealsQtyPerDaySecondShift.add(sumPerDaySecondShift);
            }
            model.addAttribute("mealsQtyPerDayFirstShift", mealsQtyPerDayFirstShift);
            model.addAttribute("mealsQtyPerDaySecondShift", mealsQtyPerDaySecondShift);
            model.addAttribute("orderSummaryList", orderSummaryList);
            model.addAttribute("shift", shift);

            if (orderSummaryRepository.findAll().isEmpty()) {
                model.addAttribute("kw", "");
            } else {
                model.addAttribute("kw", orderSummaryRepository.findAll().get(0).getKw());
            }
            if (shift == null || shift == 1) {
                return "/admin/admin-dinner-ids-1shift";
            } else {
                return "/admin/admin-dinner-ids-2shift";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/orderSummary/update")
    public String actualOrderUpdate(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {

//            ModelMapper modelMapper = new ModelMapper();
//            List<NewOrder> newOrdersList = newOrderService.findAll();
//
//            for (NewOrder newOrder : newOrdersList) {
//                ActualOrder actualOrder;
//                actualOrder = modelMapper.map(newOrder, ActualOrder.class);
//                actualOrderRepository.save(actualOrder);
//            }
//
//            List<NewMenu> newMenuMeals = newMenuRepository.findAll();
//            List<ActualOrder> actualOrderMeals = actualOrderRepository.findAll();
//
//            for (NewMenu newMenuMeal : newMenuMeals) {
//                String idsFirstShift = "";
//                Integer idsFirstShiftQty = 0;
//                String idsSecondShift = "";
//                Integer idsSecondShiftQty = 0;
//                for (ActualOrder actualOrderMeal : actualOrderMeals) {
//                    if (newMenuMeal.getMealNo().equals(actualOrderMeal.getMealMon())
//                            || newMenuMeal.getMealNo().equals(actualOrderMeal.getMealTue())
//                            || newMenuMeal.getMealNo().equals(actualOrderMeal.getMealWed())
//                            || newMenuMeal.getMealNo().equals(actualOrderMeal.getMealThu())
//                            || newMenuMeal.getMealNo().equals(actualOrderMeal.getMealFri())) {
//                        if (actualOrderMeal.getShiftMon() == 1
//                                || actualOrderMeal.getShiftTue() == 1
//                                || actualOrderMeal.getShiftWed() == 1
//                                || actualOrderMeal.getShiftThu() == 1
//                                || actualOrderMeal.getShiftFri() == 1) {
//                            idsFirstShift = idsFirstShift + actualOrderMeal.getUser().getUserId() + ", ";
//                            idsFirstShiftQty++;
//                        } else if (actualOrderMeal.getShiftMon() == 2
//                                || actualOrderMeal.getShiftTue() == 2
//                                || actualOrderMeal.getShiftWed() == 2
//                                || actualOrderMeal.getShiftThu() == 2
//                                || actualOrderMeal.getShiftFri() == 2) {
//                            idsSecondShift = idsSecondShift + actualOrderMeal.getUser().getUserId() + ", ";
//                            idsSecondShiftQty++;
//                        }
//                    }
//                }
//                OrderSummary orderSummary = new OrderSummary();
//                orderSummary.setMealNo(newMenuMeal.getMealNo());
//                orderSummary.setMealName(newMenuMeal.getMealName());
//                orderSummary.setDayId(newMenuMeal.getDayId());
//                orderSummary.setKw(newMenuMeal.getKw());
//                orderSummary.setFirstShiftQuantity(idsFirstShiftQty);
//                orderSummary.setFirstShiftUsersId(idsFirstShift);
//                orderSummary.setSecondShiftQuantity(idsSecondShiftQty);
//                orderSummary.setSecondShiftUsersId(idsSecondShift);
//                orderSummaryRepository.save(orderSummary);
//            }
            orderSummaryService.rewriteNewOrdersToActualOrders();
            return "redirect:/admin/orderSummary";
        }
        return "redirect:/";
    }
}
