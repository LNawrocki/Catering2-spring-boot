package pl.coderslab.catering2springboot.newOrder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.catering2springboot.actualOrder.ActualOrderService;
import pl.coderslab.catering2springboot.config.Config;
import pl.coderslab.catering2springboot.config.ConfigService;
import pl.coderslab.catering2springboot.department.DepartmentService;
import pl.coderslab.catering2springboot.newMenu.NewMenu;
import pl.coderslab.catering2springboot.newMenu.NewMenuService;
import pl.coderslab.catering2springboot.user.User;
import pl.coderslab.catering2springboot.user.UserService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes({"searchNewOrderId", "searchIsPaid", "searchLogin", "searchDepartmentId", "searchUserId"})
public class NewOrderController {
    public final NewOrderService newOrderService;
    public final UserService userService;
    public final NewMenuService newMenuService;
    private final ActualOrderService actualOrderService;
    private final ConfigService configService;
    private final DepartmentService departmentService;


    private static NewOrder getNewOrder(int kw, User user) {
        NewOrder newOrder = new NewOrder();
        newOrder.setQtyMon(1);
        newOrder.setQtyTue(1);
        newOrder.setQtyWed(1);
        newOrder.setQtyThu(1);
        newOrder.setQtyFri(1);
        newOrder.setKw(kw);
        newOrder.setUser(user);
        newOrder.setToPay(BigDecimal.valueOf(0));
        newOrder.setIsPaid(false);
        return newOrder;
    }

    @GetMapping("/admin/newOrder/list")
    public String orderListView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            model.addAttribute("departments", departmentService.findAll());

            if (session.getAttribute("searchNewOrderId") != null && session.getAttribute("searchNewOrderId") != "") {
                Long searchNewOrderId = Long.parseLong((String) session.getAttribute("searchNewOrderId"));
                NewOrder newOrder = newOrderService.getNewOrderById(searchNewOrderId);
                List<NewOrder> newOrders = new ArrayList<>();
                newOrders.add(newOrder);
                model.addAttribute("newOrders", newOrders);
                model.addAttribute("searchNewOrderId", null);
                model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
                model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
                model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
                return "/menu/admin-new-order-list";
            }

            if (session.getAttribute("searchIsPaid") != null && session.getAttribute("searchIsPaid") != "") {
                model.addAttribute("newOrders",
                        newOrderService.findNewOrderByIsPaid(Boolean.valueOf(String.valueOf(session.getAttribute("searchIsPaid")))));
                model.addAttribute("searchNewOrderId", null);
                model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
                model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
                model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
                return "/menu/admin-new-order-list";
            }

            if (session.getAttribute("searchDepartmentId") != null && session.getAttribute("searchDepartmentId") != "") {
                List<User> users = userService.findUsersByDepartment(departmentService.getDepartmentById((Integer) session.getAttribute("searchDepartmentId")));
                List<NewOrder> newOrders = new ArrayList<>();

                for (User user : users) {
                    if (newOrderService.getNewOrderByUserId(user.getUserId()) != null) {
                        newOrders.add(newOrderService.getNewOrderByUserId(user.getUserId()));
                    }
                }
                model.addAttribute("newOrders", newOrders);
                model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
                model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
                model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
                return "/menu/admin-new-order-list";
            }

            if (session.getAttribute("searchLogin") != null && session.getAttribute("searchLogin") != "") {
                String login = (String) session.getAttribute("searchLogin");
                List<NewOrder> newOrders = new ArrayList<>();
                newOrders.add(newOrderService.getNewOrderByUserId(userService.getUserByLogin(login).getUserId()));
                model.addAttribute("newOrders", newOrders);
                model.addAttribute("searchLogin", null);
                model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
                model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
                model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
                return "/menu/admin-new-order-list";
            }

            if (session.getAttribute("searchUserId") != null && session.getAttribute("searchUserId") != "") {

                Long searchUserId = Long.parseLong((String) session.getAttribute("searchUserId"));
                List<NewOrder> newOrders = new ArrayList<>();
                newOrders.add(newOrderService.getNewOrderByUserId(searchUserId));
                model.addAttribute("newOrders", newOrders);
                model.addAttribute("searchUserId", null);
                model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
                model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
                model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
                return "/menu/admin-new-order-list";
            }

            model.addAttribute("newOrders", newOrderService.findAll());
            model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
            model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
            return "/menu/admin-new-order-list";
        }
        return "redirect:/";
    }

    @PostMapping("/admin/newOrder/searchNewOrderId")
    public String searchNewOrderId(@RequestParam String searchNewOrderId,
                                   Model model) {
        model.addAttribute("searchNewOrderId", searchNewOrderId);
        model.addAttribute("searchIsPaid", "");
        model.addAttribute("searchDepartmentId", "");
        model.addAttribute("searchLogin", "");
        model.addAttribute("searchUserId", "");
        return "redirect:/admin/newOrder/list";
    }

    @PostMapping("/admin/newOrder/searchIsPaid")
    public String searchIdPaid(@RequestParam String searchIsPaid,
                               Model model) {
        model.addAttribute("searchIsPaid", searchIsPaid);
        model.addAttribute("searchNewOrderId", "");
        model.addAttribute("searchDepartmentId", "");
        model.addAttribute("searchUserId", "");
        return "redirect:/admin/newOrder/list";
    }

    @PostMapping("/admin/newOrder/searchDepartment")
    public String searchDepartment(@RequestParam Integer searchDepartmentId,
                                   Model model) {
        model.addAttribute("searchDepartmentId", searchDepartmentId);
        model.addAttribute("searchNewOrderId", "");
        model.addAttribute("searchIsPaid", "");
        model.addAttribute("searchUserId", "");
        return "redirect:/admin/newOrder/list";
    }

    @PostMapping("/admin/newOrder/searchLogin")
    public String searchLogin(@RequestParam String searchLogin,
                              Model model) {
        model.addAttribute("searchLogin", searchLogin);
        model.addAttribute("searchNewOrderId", "");
        model.addAttribute("searchIsPaid", "");
        model.addAttribute("searchDepartmentId", "");
        return "redirect:/admin/newOrder/list";
    }

    @PostMapping("/admin/newOrder/searchUserId")
    public String userListId(@RequestParam String searchUserId,
                             Model model) {
        model.addAttribute("searchUserId", searchUserId);
        return "redirect:/admin/newOrder/list";
    }

    @PostMapping("/admin/newOrder/searchClean")
    public String newOrderListClean(Model model) {
        model.addAttribute("searchNewOrderId", "");
        model.addAttribute("searchIsPaid", "");
        model.addAttribute("searchDepartmentId", "");
        return "redirect:/admin/newOrder/list";
    }

    @GetMapping("/admin/actualOrder/list")
    public String actualOrderListView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && (Boolean) session.getAttribute("superAdmin")) {
            model.addAttribute("actualOrders", actualOrderService.findAll());
            return "/menu/admin-actual-order-list";
        }
        return "redirect:/";
    }

    @PostMapping("/admin/newOrder/list/paid")
    public String orderListPaidButtonForm(@RequestParam Boolean paid,
                                          @RequestParam Long userIdUpdate) {
        NewOrder newOrder = newOrderService.getNewOrderByUserId(userIdUpdate);
        newOrder.setIsPaid(paid);
        newOrderService.save(newOrder);
        return "redirect:/admin/newOrder/list";
    }

    //TODO Zmienić obsługę usuwania zamówienia (get - POST)

    @GetMapping("/user/newOrder/delete")
    public String newOrderDelete(@RequestParam Long id, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            NewOrder newOrder = newOrderService.getNewOrderById(id);
            newOrderService.delete(newOrder);

            if ((Boolean) session.getAttribute("superAdmin")) {
                return "redirect:/admin/newOrder/list";
            } else {
                return "redirect:/menu/newOrder";
            }
        }
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/user/newOrder/check")
    public String NewOrderAdminCheckView(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            User user = userService.getUserById((Long) session.getAttribute("userId"));
            model.addAttribute("name", user.getName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("editUserId", user.getUserId());
            NewOrder order = newOrderService.getNewOrderByUserId(user.getUserId());
            model.addAttribute("newOrder", newOrderService.getNewOrderByUserId((Long) session.getAttribute("userId")));
            model.addAttribute("kw", LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
            model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));
            if (order != null && !order.getIsPaid()) {
                model.addAttribute("receivables", order.getToPay());
            } else {
                model.addAttribute("receivables", 0);
            }

            if ((Boolean) session.getAttribute("superAdmin")) {
                return "/menu/new-order-admin-check";
            } else {
                return "/menu/new-order-user-check";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/menu/newOrder")
    public String newOrderView(Model model, HttpSession session) {

        if (session.getAttribute("userId") != null) {
            User user = userService.getUserById((Long) session.getAttribute("userId"));
            model.addAttribute("user", user);
            model.addAttribute("name", user.getName());
            model.addAttribute("lastName", user.getLastName());
//            model.addAttribute("editUserId", user.getUserId());
            if (configService.getConfig().getEditMode()) {
                if ((Boolean) session.getAttribute("superAdmin")) {
                    return "/admin/admin-home-editmode";
                } else {
                    return "/user/user-home-editmode";
                }
            }

            //TODO Zmienić działanie zamawiania obiadu dla admina, opcja zawsze dostepna ze sprawdzeniem czy admin już nie zamówił dania
            if (newOrderService.getNewOrderByUserId((Long) session.getAttribute("userId")) != null) {
                return "redirect:/user/newOrder/check";
            }

            BigDecimal paymentPerc = BigDecimal.valueOf(user.getDepartment().getPaymentPerc());

            List<NewMenu> menuMonday = newMenuService.findNewMenusByDayId(1);
            List<NewMenu> menuTuesday = newMenuService.findNewMenusByDayId(2);
            List<NewMenu> menuWednesday = newMenuService.findNewMenusByDayId(3);
            List<NewMenu> menuThursday = newMenuService.findNewMenusByDayId(4);
            List<NewMenu> menuFriday = newMenuService.findNewMenusByDayId(5);

            menuMonday
                    .forEach(e -> {
                        e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                        e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                    });
            menuTuesday
                    .forEach(e -> {
                        e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                        e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                    });
            menuWednesday
                    .forEach(e -> {
                        e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                        e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                    });
            menuThursday
                    .forEach(e -> {
                        e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                        e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                    });
            menuFriday
                    .forEach(e -> {
                        e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                        e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                    });

            int kw = LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1;
            NewOrder newOrder = getNewOrder(kw, user);
            model.addAttribute("newOrder", newOrder);
            model.addAttribute("newMenuMonday", menuMonday);
            model.addAttribute("newMenuTuesday", menuTuesday);
            model.addAttribute("newMenuWednesday", menuWednesday);
            model.addAttribute("newMenuThursday", menuThursday);
            model.addAttribute("newMenuFriday", menuFriday);
            model.addAttribute("userId", user.getUserId());
            model.addAttribute("kw", kw);
            model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
            model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));

            NewOrder order = newOrderService.getNewOrderByUserId(user.getUserId());
            if (order != null && !order.getIsPaid()) {
                model.addAttribute("receivables", order.getToPay());
            } else {
                model.addAttribute("receivables", 0);
            }
            if ((Boolean) session.getAttribute("superAdmin")) {
                return "/menu/new-order-admin";
            } else {
                return "/menu/new-order-user";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/menu/newOrder")
    public String newOrder(NewOrder newOrder, HttpSession session) {
        if (session.getAttribute("userId") != null) {

            User user = userService.getUserById(newOrder.getUser().getUserId());
            BigDecimal paymentPerc = BigDecimal.valueOf(user.getDepartment().getPaymentPerc());

            NewMenu mealMonday = newMenuService.findByMealNo(newOrder.getMealMon());
            NewMenu mealTuesday = newMenuService.findByMealNo(newOrder.getMealTue());
            NewMenu mealWednesday = newMenuService.findByMealNo(newOrder.getMealWed());
            NewMenu mealThursday = newMenuService.findByMealNo(newOrder.getMealThu());
            NewMenu mealFriday = newMenuService.findByMealNo(newOrder.getMealFri());

            newOrder.setPriceMon(mealMonday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
            newOrder.setPriceTue(mealTuesday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
            newOrder.setPriceWed(mealWednesday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
            newOrder.setPriceThu(mealThursday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
            newOrder.setPriceFri(mealFriday.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));

            newOrder.setMealMonName(newMenuService.findByMealNo(newOrder.getMealMon()).getMealName());
            newOrder.setMealTueName(newMenuService.findByMealNo(newOrder.getMealTue()).getMealName());
            newOrder.setMealWedName(newMenuService.findByMealNo(newOrder.getMealWed()).getMealName());
            newOrder.setMealThuName(newMenuService.findByMealNo(newOrder.getMealThu()).getMealName());
            newOrder.setMealFriName(newMenuService.findByMealNo(newOrder.getMealFri()).getMealName());

            newOrder.setToPay(newOrder.getPriceMon().add(newOrder.getPriceTue()).add(newOrder.getPriceWed())
                    .add(newOrder.getPriceThu()).add(newOrder.getPriceFri()));

            newOrder.setKw(LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);

            newOrderService.save(newOrder);
            return "redirect:/admin/newOrder/list";
        }
        //TODO: poprawić przekirowanie user / admin - admin zamawianie dla usera
        return "redirect:/";
    }

    @GetMapping("/admin/newOrder/orderForUser")
    public String newOrderForUserView(@RequestParam(value = "userId") Long userId, Model model, HttpSession session) {


        System.out.println(userId);
        User user = userService.getUserById(userId);
        System.out.println(user);
        model.addAttribute("user", user);
//            model.addAttribute("name", user.getName());
//            model.addAttribute("lastName", user.getLastName());
//            model.addAttribute("editUserId", user.getUserId());
        if (configService.getConfig().getEditMode()) {
            return "/admin/admin-home-editmode";
        }


        //TODO przekazać dane użytkownika, dla którego zamawiamy obiad - strona check - czyszczenmie danych usera
        if (newOrderService.getNewOrderByUserId(user.getUserId()) != null) {
            return "redirect:/user/newOrder/check";
        }

        BigDecimal paymentPerc = BigDecimal.valueOf(user.getDepartment().getPaymentPerc());

        List<NewMenu> menuMonday = newMenuService.findNewMenusByDayId(1);
        List<NewMenu> menuTuesday = newMenuService.findNewMenusByDayId(2);
        List<NewMenu> menuWednesday = newMenuService.findNewMenusByDayId(3);
        List<NewMenu> menuThursday = newMenuService.findNewMenusByDayId(4);
        List<NewMenu> menuFriday = newMenuService.findNewMenusByDayId(5);

        menuMonday
                .forEach(e -> {
                    e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                    e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                });
        menuTuesday
                .forEach(e -> {
                    e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                    e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                });
        menuWednesday
                .forEach(e -> {
                    e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                    e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                });
        menuThursday
                .forEach(e -> {
                    e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                    e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                });
        menuFriday
                .forEach(e -> {
                    e.setMealPrice(e.getMealPrice().multiply(paymentPerc).divide(BigDecimal.valueOf(100)));
                    e.setMealName(e.getMealName().concat(" ").concat(String.valueOf(e.getMealPrice())).concat(" zł"));
                });

        int kw = LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1;
        NewOrder newOrder = getNewOrder(kw, user);
        model.addAttribute("newOrder", newOrder);
        model.addAttribute("newMenuMonday", menuMonday);
        model.addAttribute("newMenuTuesday", menuTuesday);
        model.addAttribute("newMenuWednesday", menuWednesday);
        model.addAttribute("newMenuThursday", menuThursday);
        model.addAttribute("newMenuFriday", menuFriday);
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("kw", kw);
        model.addAttribute("weekStart", LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY));
        model.addAttribute("weekEnd", LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY));

        NewOrder order = newOrderService.getNewOrderByUserId(user.getUserId());

        return "/menu/new-order-admin";
    }


    @PostMapping("/admin/newOrder/clear")
    public String newOrderClear() {
        Config config = configService.getConfig();
        config.setEditMode(true);
        configService.save(config);
        newOrderService.deleteAll();
        newMenuService.deleteAll();
        for (int i = 1; i <= 5; i++) {
            NewMenu newMenu = new NewMenu();
            newMenu.setMealNo(i);
            newMenu.setMealName("Brak");
            newMenu.setDayId(i);
            newMenu.setKw(LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()) + 1);
            newMenu.setMealPrice(BigDecimal.valueOf(0.00));
            newMenuService.save(newMenu);
        }
        return "redirect:/admin/financial";
    }
}
