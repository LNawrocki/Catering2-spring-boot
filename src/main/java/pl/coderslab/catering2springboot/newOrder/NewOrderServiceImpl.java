package pl.coderslab.catering2springboot.newOrder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public NewOrder getNewOrderById(Long id) {
        return newOrderRepository.getNewOrderById(id);
    }

    @Override
    public List<NewOrder> findAll() {
        return newOrderRepository.findAll();
    }

    @Override
    public NewOrder save(NewOrder newOrder) {
        return newOrderRepository.save(newOrder);
    }

    @Override
    public List<NewOrder> findNewOrderByIsPaid(Boolean isPaid) {
        return newOrderRepository.findNewOrderByIsPaid(isPaid);
    }
}
