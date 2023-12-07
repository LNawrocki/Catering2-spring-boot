package pl.coderslab.catering2springboot.department;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Data
public class Department {
    @Id
    private Integer id;
    private String name;
    @Range(min = 0, max = 100, message = "Błąd: Wartość musi być w przedziale 0 - 100")
    @Column(name = "payment_perc")
    private Integer paymentPerc;

}
