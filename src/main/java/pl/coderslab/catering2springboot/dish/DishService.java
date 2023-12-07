package pl.coderslab.catering2springboot.dish;

import java.util.List;

public interface DishService {
    List<Dish> findAll();
    void deleteByDishId(Integer dishId);
}
