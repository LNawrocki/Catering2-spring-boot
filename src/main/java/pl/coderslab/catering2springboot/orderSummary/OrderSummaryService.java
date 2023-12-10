package pl.coderslab.catering2springboot.orderSummary;

public interface OrderSummaryService {
    void deleteAll();

    void rewriteNewOrdersToActualOrders();
    OrderSummary save(OrderSummary orderSummary);
}
