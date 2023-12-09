package pl.coderslab.catering2springboot.actualMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.entity.ActualMenu;

import java.util.List;

public interface ActualMenuRepository extends JpaRepository<ActualMenu, Long> {

    List<ActualMenu> findActualMenusByDayId(Integer dayId);
    ActualMenu findByMealNo(Integer mealNo);
}
