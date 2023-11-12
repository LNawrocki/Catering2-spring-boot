package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
