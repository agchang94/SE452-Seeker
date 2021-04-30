package se452.group9.seeker;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import se452.group9.seeker.School;

public interface ApplicationRepository extends JpaRepository <Application, Long>{

}
