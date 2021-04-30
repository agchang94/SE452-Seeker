package se452.group9.seeker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se452.group9.seeker.model.StudentCerts;

@Repository
public interface StudentCertsRepository extends JpaRepository<StudentCerts, Long> {
    
}
