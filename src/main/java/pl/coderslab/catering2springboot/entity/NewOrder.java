package pl.coderslab.catering2springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

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
    @Column(name = "user_meal_mon")
    private Integer userMealMon;
    @Column(name = "user_price_mon")
    private BigInteger userPriceMon;
    @Column(name = "user_shift_mon")
    private Integer userShiftMon;
    @Column(name = "user_qty_mon")
    private Integer userQtyMon;
    @Column(name = "user_meal_tue")
    private Integer userMealTue;
    @Column(name = "user_price_tue")
    private BigInteger userPriceTue;
    @Column(name = "user_shift_tue")
    private Integer userShiftTue;
    @Column(name = "user_qty_tue")
    private Integer userQtyTue;
    @Column(name = "user_meal_wed")
    private Integer userMealWed;
    @Column(name = "user_price_wed")
    private BigInteger userPriceWed;
    @Column(name = "user_shift_wed")
    private Integer userShiftWed;
    @Column(name = "user_qty_wed")
    private Integer userQtyWed;
    @Column(name = "user_meal_thu")
    private Integer userMealThu;
    @Column(name = "user_price_thu")
    private BigInteger userPriceThu;
    @Column(name = "user_shift_thu")
    private Integer userShiftThu;
    @Column(name = "user_qty_thu")
    private Integer userQtyThu;
    @Column(name = "user_meal_fri")
    private Integer userMealFri;
    @Column(name = "user_price_fri")
    private BigInteger userPriceFri;
    @Column(name = "user_shift_fri")
    private Integer userShiftFri;
    @Column(name = "user_qty_fri")
    private Integer userQtyFri;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

}

