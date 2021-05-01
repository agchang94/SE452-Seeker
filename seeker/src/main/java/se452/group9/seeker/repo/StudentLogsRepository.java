package se452.group9.seeker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se452.group9.seeker.model.StudentLogs;

public interface StudentLogsRepository extends JpaRepository <StudentLogs, Long>{
    StudentLogs findByStudentID(int studentID);
}
