package se452.group9.seeker.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import se452.group9.seeker.model.JobType;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeRepository extends MongoRepository <JobType, Long>{
    
    public List<JobType> findByType(String type);
}
