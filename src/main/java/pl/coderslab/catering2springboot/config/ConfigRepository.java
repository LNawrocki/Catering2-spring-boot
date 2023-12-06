package pl.coderslab.catering2springboot.config;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Integer> {
    Config getConfigById(Integer id);
}
