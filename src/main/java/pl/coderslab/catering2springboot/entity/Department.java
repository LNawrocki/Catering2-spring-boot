package pl.coderslab.catering2springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Department {
    @Id
    @Column(name = "department_id")
    private Integer departmentId;
    private String name;
    @Column(name = "payment_perc")
    private Integer paymentPerc;

}
