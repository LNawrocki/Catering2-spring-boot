package pl.coderslab.catering2springboot.orderSummary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderSummaryRepository extends JpaRepository<OrderSummary, Long> {

    List<OrderSummary> findOrderSummaryByDayId(Integer dayId);
}
