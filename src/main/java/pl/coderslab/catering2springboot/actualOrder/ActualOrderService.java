package pl.coderslab.catering2springboot.actualOrder;

import java.util.List;

public interface ActualOrderService {

    ActualOrder getActualOrderByUserId(Long userId);
    void delete(ActualOrder actualOrder);
    List<ActualOrder> findAll();

    ActualOrder save(ActualOrder actualOrder);
}
