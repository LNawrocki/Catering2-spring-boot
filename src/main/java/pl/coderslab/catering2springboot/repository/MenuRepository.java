package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.entity.NewMenu;

import java.util.List;

public interface MenuRepository extends JpaRepository<NewMenu, Long> {
    List<NewMenu> findByDayId(Integer dayId);
}
