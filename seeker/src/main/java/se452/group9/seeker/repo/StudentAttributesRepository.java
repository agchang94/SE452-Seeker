package se452.group9.seeker.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import se452.group9.seeker.model.StudentAttributes;
import java.util.List;

public interface StudentAttributesRepository extends JpaRepository <StudentAttributes, Long>{
    List<StudentAttributes> findByStudentID(int studentID);
}