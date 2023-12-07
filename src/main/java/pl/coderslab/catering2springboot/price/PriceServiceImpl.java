package pl.coderslab.catering2springboot.price;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService{

    private final PriceRepository priceRepository;

    @Override
    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    @Override
    public void deleteByPriceId(Integer priceId) {
        priceRepository.deleteById(priceId);
    }
}
