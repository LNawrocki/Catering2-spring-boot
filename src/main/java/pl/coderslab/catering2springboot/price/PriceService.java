package pl.coderslab.catering2springboot.price;

import java.util.List;

public interface PriceService {

    List<Price> findAll();

    void deleteByPriceId(Integer priceId);
}
