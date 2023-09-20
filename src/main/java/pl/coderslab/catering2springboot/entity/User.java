package pl.coderslab.catering2springboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String department;
    @Column(unique = true)
    private String login;
    private String password;
    @Column(name = "super_admin")
    private Boolean superAdmin;
    private Boolean active;
}
