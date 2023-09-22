package pl.coderslab.catering2springboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;
}
