package pl.coderslab.catering2springboot.newOrder;

import java.util.List;

public interface NewOrderService {

    NewOrder getNewOrderByUserId(Long userId);
    void delete(NewOrder newOrder);
    void deleteAll();
    Integer getQuantityOfNewOrders();
    NewOrder getNewOrderById(Long id);
    List<NewOrder> findAll();
    NewOrder save(NewOrder newOrder);
    List<NewOrder> findNewOrderByIsPaid(Boolean isPaid);


}
