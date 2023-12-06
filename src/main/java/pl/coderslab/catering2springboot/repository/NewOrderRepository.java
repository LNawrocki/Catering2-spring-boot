package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.catering2springboot.entity.NewOrder;

import java.util.List;


public interface NewOrderRepository extends JpaRepository<NewOrder, Long> {

    @Query(value = "SELECT * FROM new_orders WHERE user_id = ?1", nativeQuery = true)
    NewOrder getNewOrderByUserId(Long userId);

    NewOrder getNewOrderById(Long id);
    List<NewOrder> findNewOrderByIsPaid(Boolean isPaid);

}
