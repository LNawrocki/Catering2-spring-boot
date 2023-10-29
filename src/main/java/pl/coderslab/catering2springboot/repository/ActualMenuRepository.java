package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.entity.ActualMenu;

public interface ActualMenuRepository extends JpaRepository<ActualMenu, Long> {

}
