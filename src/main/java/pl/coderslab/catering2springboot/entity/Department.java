package pl.coderslab.catering2springboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
