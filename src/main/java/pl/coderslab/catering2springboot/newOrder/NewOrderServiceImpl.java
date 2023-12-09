package pl.coderslab.catering2springboot.newOrder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewOrderServiceImpl implements NewOrderService{

    private final NewOrderRepository newOrderRepository;

    @Override
    public NewOrder getNewOrderByUserId(Long userId) {
        return newOrderRepository.getNewOrderByUserId(userId);
    }

    @Override
    public void delete(NewOrder newOrder) {
        newOrderRepository.delete(newOrder);
    }

    @Override
    public void deleteAll() {
        newOrderRepository.deleteAll();
    }

    @Override
    public Integer getQuantityOfNewOrders() {
        return newOrderRepository.getQuantityOfNewOrders();
    }
}
