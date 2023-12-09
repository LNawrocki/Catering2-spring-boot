package pl.coderslab.catering2springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.catering2springboot.newMenu.NewMenu;
import pl.coderslab.catering2springboot.dish.DishRepository;
import pl.coderslab.catering2springboot.newMenu.NewMenuRepository;
import pl.coderslab.catering2springboot.newOrder.NewOrderRepository;
import pl.coderslab.catering2springboot.price.PriceRepository;

import javax.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;


@Controller

public class MenuController {
    private final NewMenuRepository newMenuRepository;
    private final NewOrderRepository newOrderRepository;
    private final PriceRepository priceRepository;
    private final DishRepository dishRepository;

    public MenuController(NewMenuRepository newMenuRepository, NewOrderRepository newOrderRepository, PriceRepository priceRepository, DishRepository dishRepository) {
        this.newMenuRepository = newMenuRepository;
        this.newOrderRepository = newOrderRepository;
        this.priceRepository = priceRepository;
        this.dishRepository = dishRepository;
    }






    //do poprawy - metoda get nie powinna zmieniać stanu bazy danych, zmian na post i miniformularz

}
