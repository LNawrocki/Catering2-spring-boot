package pl.coderslab.catering2springboot.dish;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Dish {

    @Id
    @Column(name = "dish_id")
    private Integer dishId;
    private String name;
}
