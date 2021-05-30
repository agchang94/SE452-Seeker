package se452.group9.seeker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se452.group9.seeker.model.StudentResume;

public interface StudentResumeRepository extends JpaRepository <StudentResume, Long>{
}