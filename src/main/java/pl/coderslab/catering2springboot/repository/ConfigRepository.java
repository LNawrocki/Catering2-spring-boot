package pl.coderslab.catering2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.catering2springboot.entity.Config;

public interface ConfigRepository extends JpaRepository<Config, Integer> {
}
