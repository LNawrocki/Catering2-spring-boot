package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.catering2springboot.controllers.ActualOrderController;
import pl.coderslab.catering2springboot.entity.ActualOrder;
import pl.coderslab.catering2springboot.entity.NewMenu;

public interface ActualOrderRepository extends JpaRepository<ActualOrder, Long> {

    ActualOrder getActualOrderByUser_UserId(Long userId);

}
