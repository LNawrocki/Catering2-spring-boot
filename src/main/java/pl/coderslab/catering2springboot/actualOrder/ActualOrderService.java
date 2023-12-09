package pl.coderslab.catering2springboot.actualOrder;

public interface ActualOrderService {

    ActualOrder getActualOrderByUserId(Long userId);
    void delete(ActualOrder actualOrder);
}
