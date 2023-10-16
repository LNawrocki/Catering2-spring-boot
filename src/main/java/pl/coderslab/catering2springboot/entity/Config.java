package pl.coderslab.catering2springboot.entity;

import lombok.Data;
import javax.persistence.*;


@Entity
@Data
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "edit_mode")
    private Boolean editMode;
    @Column(name = "new_menu_avaliable")
    private Boolean newMenuAvaliable;
}
