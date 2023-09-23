package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.entity.NewOrder;

public interface NewOrderRepository extends JpaRepository<NewOrder, Long> {
}
