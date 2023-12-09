package pl.coderslab.catering2springboot.actualOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.actualOrder.ActualOrder;

public interface ActualOrderRepository extends JpaRepository<ActualOrder, Long> {

    ActualOrder getActualOrderByUser_UserId(Long userId);

}
