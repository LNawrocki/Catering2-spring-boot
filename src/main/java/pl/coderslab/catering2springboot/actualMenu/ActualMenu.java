package pl.coderslab.catering2springboot.actualMenu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@Table(name ="actual_menu") // Menu for current week
public class ActualMenu {

    @Id
    @Column(name = "meal_no", unique = true)
    private Integer mealNo;
    @Column(name = "meal_name")
    private String mealName;
    @Column(name = "meal_price")
    private BigDecimal mealPrice;
    @Column(name = "day_id")
    private Integer dayId;
    @Column(name = "first_shift_quantity")
    private Integer firstShiftQuantity;
    @Column(name = "first_shift_users_id")
    private String firstShiftUsersId;
    @Column(name = "second_shift_quantity")
    private Integer secondShiftQuantity;
    @Column(name = "second_shift_users_id")
    private String secondShiftUsersId;
    private Integer kw;
}
