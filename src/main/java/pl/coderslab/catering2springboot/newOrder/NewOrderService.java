package pl.coderslab.catering2springboot.newOrder;

public interface NewOrderService {

    NewOrder getNewOrderByUserId(Long userId);
    void delete(NewOrder newOrder);
}
