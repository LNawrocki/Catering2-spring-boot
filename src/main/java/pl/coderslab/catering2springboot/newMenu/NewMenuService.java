package pl.coderslab.catering2springboot.newMenu;

import java.util.List;

public interface NewMenuService {
    List<NewMenu> newMenuFindByDayId(Integer id);
}
