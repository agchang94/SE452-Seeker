package se452.group9.seeker.repo;

import se452.group9.seeker.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}