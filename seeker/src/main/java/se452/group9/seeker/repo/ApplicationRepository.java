package se452.group9.seeker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se452.group9.seeker.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository <Application, Long>{

}
