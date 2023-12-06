package pl.coderslab.catering2springboot.config;

public interface ConfigService {

    Boolean editModeStatus();
    Config getConfig();
    Config save(Config config);

}
