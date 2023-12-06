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
}
