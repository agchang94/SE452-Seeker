package se452.group9.seeker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se452.group9.seeker.model.Student;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long>{
    @Query("SELECT u FROM Student u WHERE u.email = ?1")
    Student findbyEmail(String email);

    
}
