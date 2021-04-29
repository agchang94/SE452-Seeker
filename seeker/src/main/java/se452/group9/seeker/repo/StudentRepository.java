package se452.group9.seeker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se452.group9.seeker.model.Student;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long>{

    
}
