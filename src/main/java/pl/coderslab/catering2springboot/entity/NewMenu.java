package pl.coderslab.catering2springboot.entity;

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
@Table(name ="new_menu") // menu for next week
public class NewMenu {

    @Id
    @Column(name = "meal_no", unique = true)
    private Integer mealNo;
    @Column(name = "meal_name")
    private String mealName;
    @Column(name = "meal_price")
    private BigDecimal mealPrice;
    @Column(name = "day_id")
    private Integer dayId;

}
