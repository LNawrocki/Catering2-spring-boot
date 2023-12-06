package pl.coderslab.catering2springboot.newOrder;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.catering2springboot.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "new_orders")
public class NewOrder {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer kw;
    @Column(name = "meal_mon")
    private Integer mealMon;
    @Column(name = "meal_mon_name")
    private String mealMonName;
    @Column(name = "price_mon")
    private BigDecimal priceMon;
    @Column(name = "shift_mon")
    private Integer shiftMon;
    @Column(name = "qty_mon")
    private Integer qtyMon;
    @Column(name = "meal_tue")
    private Integer mealTue;
    @Column(name = "meal_tue_name")
    private String mealTueName;
    @Column(name = "price_tue")
    private BigDecimal priceTue;
    @Column(name = "shift_tue")
    private Integer shiftTue;
    @Column(name = "qty_tue")
    private Integer qtyTue;
    @Column(name = "meal_wed")
    private Integer mealWed;
    @Column(name = "meal_wed_name")
    private String mealWedName;
    @Column(name = "price_wed")
    private BigDecimal priceWed;
    @Column(name = "shift_wed")
    private Integer shiftWed;
    @Column(name = "qty_wed")
    private Integer qtyWed;
    @Column(name = "meal_thu")
    private Integer mealThu;
    @Column(name = "meal_thu_name")
    private String mealThuName;
    @Column(name = "price_thu")
    private BigDecimal priceThu;
    @Column(name = "shift_thu")
    private Integer shiftThu;
    @Column(name = "qty_thu")
    private Integer qtyThu;
    @Column(name = "meal_fri")
    private Integer mealFri;
    @Column(name = "meal_fri_name")
    private String mealFriName;
    @Column(name = "price_fri")
    private BigDecimal priceFri;
    @Column(name = "shift_fri")
    private Integer shiftFri;
    @Column(name = "qty_fri")
    private Integer qtyFri;
    @Column(name = "to_pay")
    private BigDecimal toPay;
    @Column(name = "is_paid")
    private Boolean isPaid;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

