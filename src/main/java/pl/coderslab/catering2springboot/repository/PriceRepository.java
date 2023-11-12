package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
