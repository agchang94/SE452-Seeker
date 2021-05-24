package se452.group9.seeker.repo;

import se452.group9.seeker.model.Recruiter;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecruiterRepository extends MongoRepository<Recruiter, Integer> {
 
    Recruiter findByrecruiterID(Integer id);
}
