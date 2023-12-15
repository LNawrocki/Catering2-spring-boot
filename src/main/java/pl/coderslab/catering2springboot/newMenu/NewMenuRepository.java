package pl.coderslab.catering2springboot.newMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.catering2springboot.newMenu.NewMenu;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

public interface NewMenuRepository extends JpaRepository<NewMenu, Integer> {
    List<NewMenu> findByDayId(Integer dayId);
    NewMenu findByMealNo(Integer mealNo);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM new_menu WHERE meal_no = ?1", nativeQuery = true)
    int deleteByMealNo(Integer mealNo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM new_menu WHERE day_id = ?1", nativeQuery = true)
    int deleteByDayNo(Integer dayId);

    @Modifying
    @Transactional
    void deleteNewMenuByMealNo(Integer mealNo);

    List<NewMenu> findNewMenusByDayId(Integer id);

    @Query(value ="SELECT max(nm.mealNo) FROM NewMenu nm")
    Integer lastIndex();

}
