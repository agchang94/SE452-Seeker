package se452.group9.seeker.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import se452.group9.seeker.model.School;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends MongoRepository <School, String>{

}