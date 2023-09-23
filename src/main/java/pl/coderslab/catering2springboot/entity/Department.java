package pl.coderslab.catering2springboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Department {
    @Id
    private Integer id;
    private String name;
    @Column(name = "payment_perc")
    private Integer paymentPerc;

}
