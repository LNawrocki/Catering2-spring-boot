package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.entity.NewMenu;
import pl.coderslab.catering2springboot.entity.NewOrder;
import pl.coderslab.catering2springboot.entity.User;
import pl.coderslab.catering2springboot.repository.NewMenuRepository;
import pl.coderslab.catering2springboot.repository.NewOrderRepository;
import pl.coderslab.catering2springboot.repository.UserRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;


@Controller

public class MenuController {
    private final NewMenuRepository newMenuRepository;
    private final NewOrderRepository newOrderRepository;
    private final UserRepository userRepository;

    public MenuController(NewMenuRepository newMenuRepository, NewOrderRepository newOrderRepository, UserRepository userRepository) {
        this.newMenuRepository = newMenuRepository;
        this.newOrderRepository = newOrderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String mealsView(Model model) {
        model.addAttribute("mealsMonday", newMenuRepository.findByDayId(1));
        model.addAttribute("mealsTuesday", newMenuRepository.findByDayId(2));
        model.addAttribute("mealsWednesday", newMenuRepository.findByDayId(3));
        model.addAttribute("mealsThursday", newMenuRepository.findByDayId(4));
        model.addAttribute("mealsFriday", newMenuRepository.findByDayId(5));
        model.addAttribute("date", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
        return "home";
    }

    @PostMapping("/menu/newOrder")
    public String newOrder(NewOrder newOrder) {

        User user = userRepository.getByUserId(newOrder.getUser().getUserId());
        BigDecimal paymentPerc  = BigDecimal.valueOf(user.getDepartment().getPaymentPerc());

        NewMenu mealMonday = newMenuRepository.findByMealNo(newOrder.getUserMealMon());
        NewMenu mealTuesday = newMenuRepository.findByMealNo(newOrder.getUserMealTue());
        NewMenu mealWednesday = newMenuRepository.findByMealNo(newOrder.getUserMealWed());
        NewMenu mealThursday = newMenuRepository.findByMealNo(newOrder.getUserMealThu());
        NewMenu mealFriday = newMenuRepository.findByMealNo(newOrder.getUserMealFri());

        newOrder.setUserPriceMon(mealMonday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
        newOrder.setUserPriceTue(mealTuesday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
        newOrder.setUserPriceWed(mealWednesday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
        newOrder.setUserPriceThu(mealThursday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
        newOrder.setUserPriceFri(mealFriday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));

        newOrder.setToPay(newOrder.getUserPriceMon().add(newOrder.getUserPriceTue()).add(newOrder.getUserPriceWed())
                .add(newOrder.getUserPriceThu()).add(newOrder.getUserPriceFri()));

        newOrderRepository.save(newOrder);
        return "redirect:/";
    }





    @GetMapping("/menu/update")
    public String updateMenuView(Model model){
        model.addAttribute("newMenu",new NewMenu());

        model.addAttribute("mealsMonday", newMenuRepository.findByDayId(1));
        model.addAttribute("mealsTuesday", newMenuRepository.findByDayId(2));
        model.addAttribute("mealsWednesday", newMenuRepository.findByDayId(3));
        model.addAttribute("mealsThursday", newMenuRepository.findByDayId(4));
        model.addAttribute("mealsFriday", newMenuRepository.findByDayId(5));
        model.addAttribute("date", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
        return "/menu/menu-update";
    }

    @PostMapping("/menu/update")
    public String updateMenu(NewMenu newMenu){
//        newMenu.setMealNo(newMenu.getMealNo());
        newMenuRepository.save(newMenu);
        return "redirect:/menu/update";
    }

    //do poprawy - metoda get nie powinna zmieniać stanu bazy danych
    @GetMapping("/menu/delete")
    public String deleteMenu(@RequestParam Integer mealNo) {
        System.out.println(mealNo);
        newMenuRepository.deleteByMealNo(mealNo);
        return "redirect:/menu/update";
    }

    //do poprawy - metoda get nie powinna zmieniać stanu bazy danych
    @GetMapping("/menu/deleteDay")
    public String deleteMenuDay(@RequestParam Integer dayId){
        newMenuRepository.deleteByDayNo(dayId);
        return "redirect:/menu/update";
    }

    @GetMapping("/menu/order/list")
    public String orderListView(Model model){
        List<NewOrder> newOrders = newOrderRepository.findAll(); // pobranie wszystkich nowych zamówień
        List<String> mealsNames = new ArrayList<>();
        for (NewOrder newOrder : newOrders) {
            mealsNames.add(newMenuRepository.findByMealNo(newOrder.getUserMealMon()).getMealName());
            mealsNames.add(newMenuRepository.findByMealNo(newOrder.getUserMealTue()).getMealName());
            mealsNames.add(newMenuRepository.findByMealNo(newOrder.getUserMealWed()).getMealName());
            mealsNames.add(newMenuRepository.findByMealNo(newOrder.getUserMealThu()).getMealName());
            mealsNames.add(newMenuRepository.findByMealNo(newOrder.getUserMealFri()).getMealName());
        }


        model.addAttribute("newOrders", newOrderRepository.findAll());

        return "/menu/order-list";
    }
}
