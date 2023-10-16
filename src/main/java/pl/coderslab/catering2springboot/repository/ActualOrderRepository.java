package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.catering2springboot.controllers.ActualOrderController;
import pl.coderslab.catering2springboot.entity.ActualOrder;

public interface ActualOrderRepository extends JpaRepository<ActualOrder, Long> {

    @Query(value = "SELECT * FROM actual_orders WHERE user_id = ?1", nativeQuery = true)
    ActualOrder getActualOrderByUserId(Long userId);

//    ActualOrder getActualOrderById(Long id);
}
