package se452.group9.seeker.repo;

import se452.group9.seeker.model.Job;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("Select j from Job j where lower(j.title) like %?1%")
    public List<Job> searchByTitle(String title);
}