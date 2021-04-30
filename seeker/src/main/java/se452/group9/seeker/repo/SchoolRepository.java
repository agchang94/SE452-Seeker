package se452.group9.seeker.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import se452.group9.seeker.model.School;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository <School, String>{

}