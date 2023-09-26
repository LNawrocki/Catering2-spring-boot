package pl.coderslab.catering2springboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name ="users")
public class User {
    @Id
    @Column(name = "user_id", unique = true)
    private Long userId;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String login;
    private String password;
    @Column(name = "super_admin")
    private Boolean superAdmin;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
