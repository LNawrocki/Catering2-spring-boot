package pl.coderslab.catering2springboot.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.catering2springboot.entity.Department;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name ="users")
public class User {
    @Id
    @Column(name = "user_id", unique = true)
//    @NotBlank(message = "Pole nie może być puste")
//    @NotNull(message = "Pole nie może być puste")
    private Long userId;
//    @NotBlank
    private String name;
//    @NotBlank
    @Column(name = "last_name")
    private String lastName;
//    @NotBlank
    @Column(unique = true)
    private String login;
//    @NotBlank
    private String password;
//    @NotBlank
    @Column(name = "super_admin")
    private Boolean superAdmin;
//    @NotBlank
    private Boolean active;
//    @NotBlank
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
