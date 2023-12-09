package pl.coderslab.catering2springboot.actualMenu;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActualMenuServiceImpl implements ActualMenuService{

    private final ActualMenuRepository actualMenuRepository;

    @Override
    public void deleteAll() {
        actualMenuRepository.deleteAll();
    }
}
