package pl.coderslab.catering2springboot.newMenu;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewMenuServiceImpl implements NewMenuService{
    public final NewMenuRepository newMenuRepository;


    @Override
    public List<NewMenu> newMenuFindByDayId(Integer id) {
        return newMenuRepository.findByDayId(id);
    }

    @Override
    public NewMenu save(NewMenu newMenu) {
        return newMenuRepository.save(newMenu);
    }

    @Override
    public void deleteMeal(Integer mealNo) {
        newMenuRepository.deleteNewMenuByMealNo(mealNo);
    }

    @Override
    public void deleteByDayNo(Integer dayId) {
        newMenuRepository.deleteByDayNo(dayId);
    }

    @Override
    public NewMenu findByMealNo(Integer mealNo) {
        return newMenuRepository.findByMealNo(mealNo);
    }

    @Override
    public Boolean newMenuListNotEmpty() {
        return !newMenuRepository.findAll().isEmpty();
    }


}
