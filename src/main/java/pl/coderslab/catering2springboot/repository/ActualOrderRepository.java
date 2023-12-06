package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.entity.ActualOrder;

public interface ActualOrderRepository extends JpaRepository<ActualOrder, Long> {

    ActualOrder getActualOrderByUser_UserId(Long userId);

}
