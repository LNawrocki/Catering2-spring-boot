package pl.coderslab.catering2springboot.config;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfigServiceImpl implements ConfigService{

    private final ConfigRepository configRepository;

    @Override
    public Boolean editModeStatus() {
        return configRepository.getConfigById(1).getEditMode();
    }

    @Override
    public Config getConfig(){
        return configRepository.getConfigById(1);
    }

    @Override
    public Config save(Config config) {
        return configRepository.save(config);
    }

}
