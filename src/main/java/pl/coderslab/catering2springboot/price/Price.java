package pl.coderslab.catering2springboot.price;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Price {
    @Id
    @Column(name = "price_id")
    private Integer priceId;
    private BigDecimal price;
}
