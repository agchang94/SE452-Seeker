package se452.group9.seeker.StudentTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCertsRepository extends JpaRepository<StudentCerts, Long> {
    
}
