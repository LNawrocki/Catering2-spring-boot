package pl.coderslab.catering2springboot.newMenu;

import java.util.List;

public interface NewMenuService {
    List<NewMenu> newMenuFindByDayId(Integer id);
    NewMenu save(NewMenu newMenu);
    void deleteMeal(Integer mealNo);

    void deleteByDayNo(Integer dayId);
}
