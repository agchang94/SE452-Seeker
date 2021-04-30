package se452.group9.seeker.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import se452.group9.seeker.model.School;

public interface SchoolRepository extends JpaRepository <School, String>{

}