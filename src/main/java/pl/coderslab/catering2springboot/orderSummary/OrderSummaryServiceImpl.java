package pl.coderslab.catering2springboot.orderSummary;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.catering2springboot.actualOrder.ActualOrder;
import pl.coderslab.catering2springboot.actualOrder.ActualOrderService;
import pl.coderslab.catering2springboot.newMenu.NewMenu;
import pl.coderslab.catering2springboot.newMenu.NewMenuService;
import pl.coderslab.catering2springboot.newOrder.NewOrder;
import pl.coderslab.catering2springboot.newOrder.NewOrderService;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderSummaryServiceImpl implements OrderSummaryService {

    private final OrderSummaryRepository orderSummaryRepository;
    private final NewOrderService newOrderService;
    private final ActualOrderService actualOrderService;
    private final NewMenuService newMenuService;

    @Override
    public void deleteAll() {
        deleteAll();
    }

    @Override
    public void rewriteNewOrdersToActualOrders() {
        ModelMapper modelMapper = new ModelMapper();
        List<NewOrder> newOrdersList = newOrderService.findAll();

        for (NewOrder newOrder : newOrdersList) {
            ActualOrder actualOrder;
            actualOrder = modelMapper.map(newOrder, ActualOrder.class);
            actualOrderService.save(actualOrder);
        }

        List<NewMenu> newMenuList = newMenuService.findAll();
        List<ActualOrder> actualOrderMeals = actualOrderService.findAll();

        for (NewMenu newMenu : newMenuList) {
            String idsFirstShift = "";
            Integer idsFirstShiftQty = 0;
            String idsSecondShift = "";
            Integer idsSecondShiftQty = 0;
            for (ActualOrder actualOrderMeal : actualOrderMeals) {
                if (newMenu.getMealNo().equals(actualOrderMeal.getMealMon())
                        || newMenu.getMealNo().equals(actualOrderMeal.getMealTue())
                        || newMenu.getMealNo().equals(actualOrderMeal.getMealWed())
                        || newMenu.getMealNo().equals(actualOrderMeal.getMealThu())
                        || newMenu.getMealNo().equals(actualOrderMeal.getMealFri())) {
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
            OrderSummary orderSummary = new OrderSummary();
            orderSummary.setMealNo(newMenu.getMealNo());
            orderSummary.setMealName(newMenu.getMealName());
            orderSummary.setDayId(newMenu.getDayId());
            orderSummary.setKw(newMenu.getKw());
            orderSummary.setFirstShiftQuantity(idsFirstShiftQty);
            orderSummary.setFirstShiftUsersId(idsFirstShift);
            orderSummary.setSecondShiftQuantity(idsSecondShiftQty);
            orderSummary.setSecondShiftUsersId(idsSecondShift);
            System.out.println(orderSummary);
            save(orderSummary);
        }
    }

    @Override
    public OrderSummary save(OrderSummary orderSummary) {
        return orderSummaryRepository.save(orderSummary);
    }
}
