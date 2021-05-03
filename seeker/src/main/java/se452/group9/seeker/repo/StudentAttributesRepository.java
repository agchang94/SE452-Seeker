package se452.group9.seeker.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import se452.group9.seeker.model.StudentAttributes;

public interface StudentAttributesRepository extends MongoRepository <StudentAttributes, Long>{
    StudentAttributes findByStudentID(int studentID);
}