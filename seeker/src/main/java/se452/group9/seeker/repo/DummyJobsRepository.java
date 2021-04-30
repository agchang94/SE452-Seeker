package se452.group9.seeker.repo;

import se452.group9.seeker.model.DummyJobs;
import org.springframework.data.repository.CrudRepository;

public interface DummyJobsRepository extends CrudRepository<DummyJobs,Integer> {
    
}
