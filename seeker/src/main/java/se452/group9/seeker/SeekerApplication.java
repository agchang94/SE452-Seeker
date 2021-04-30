package se452.group9.seeker;

import lombok.Data;
import se452.Persistence.StudentAcademicReader;
import se452.Persistence.StudentCertsReader;
import se452.Persistence.StudentReader;

import se452.group9.seeker.model.*;
import se452.group9.seeker.repo.StudentAcademicRepository;
import se452.group9.seeker.repo.StudentCertsRepository;
import se452.group9.seeker.repo.StudentRepository;
// import se452.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeekerApplication {

  private static final Logger log = LoggerFactory.getLogger(SeekerApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SeekerApplication.class, args);

    //final SchoolRepository repo;

    School school = new School();

    school.setSchoolID(4);
    school.setSchoolName("California Berkely");
    //repo.save(school);

    List<School> schools;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<School>> violations = validator.validate(school);
    for (ConstraintViolation<School> violation : violations) {
      System.err.println(violation.getMessage());
    }
    System.out.print(school);

  }

  /*
   * private static final Logger log =
   * LoggerFactory.getLogger(SeekerApplication.class);
   * 
   * @Bean public CommandLineRunner showSchools(SchoolRepository repository) {
   * return (args) -> { // fetch all Students
   * log.info("Schools found with findAll():");
   * log.info("-------------------------------");
   * repository.findAll().forEach((school) -> { log.info(school.toString()); });
   * log.info("-------------------------------"); }; }
   */

	
	public CommandLineRunner showStudents (StudentRepository studentRepository) {
		return (args) -> {
			log.info("Students found with findAll():");
			log.info("-------------");
			studentRepository.findAll().forEach((Student) -> {
				log.info(Student.toString());
			}); 
			log.info("--------------------");

		};

}
@Bean
public CommandLineRunner addStudents (StudentRepository studentRepository ,StudentAcademicRepository studentAcademicRepository, 
StudentCertsRepository studentCertsRepository) {
	
	return (args) -> {
		try{
			InputStream in =  getClass().getResourceAsStream("/students.txt");
			Reader fr = new InputStreamReader(in, "utf-8");

			InputStream in2 =  getClass().getResourceAsStream("/student_academics.txt");
			Reader fr2 = new InputStreamReader(in2, "utf-8");

			InputStream in3 =  getClass().getResourceAsStream("/student_certs.txt");
			Reader fr3= new InputStreamReader(in3, "utf-8");
		
			BufferedReader reader = new BufferedReader(fr); 
			ArrayList<Student> students = new ArrayList<Student>();
			students = StudentReader.getStudents(reader);

			BufferedReader reader2 = new BufferedReader(fr2); 
			ArrayList<StudentAcademics> studentAcademics = new ArrayList<StudentAcademics>();
			studentAcademics = StudentAcademicReader.getStudentAcademics(reader2);

			BufferedReader reader3 = new BufferedReader(fr3); 
			ArrayList<StudentCerts> studentCerts = new ArrayList<StudentCerts>();
			studentCerts = StudentCertsReader.getStudentCerts(reader3);
			
			for(Student student:students) {
				System.out.println(student.toString());
				studentRepository.save(student);
			}
			int count = 0;
			for(StudentAcademics studentAcademic : studentAcademics) {
				students.get(count).setStudentAcademics(studentAcademic);
				studentAcademic.setStudent(students.get(count));
				studentAcademicRepository.save(studentAcademic);
				count++;
			}
			count = 0;  

			for(StudentCerts studentCert : studentCerts) {
				students.get(count).setStudentCerts(studentCert);
				studentCert.setStudent(students.get(count));
				studentCertsRepository.save(studentCert);
				count++;
			}

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	};
}

//@Bean
public CommandLineRunner deleteStudents (StudentRepository studentRepository) {
	return(args) ->  {
	Optional<Student> stu = studentRepository.findById((long) 127);
	Student deleteStudent = stu.orElse(new Student());
	studentRepository.delete(deleteStudent);

	log.info(stu.toString());
	studentRepository.save(deleteStudent);
	
	};
}

//@Bean
public CommandLineRunner updateStudents (StudentRepository studentRepository) {
return(args) -> {
	Optional<Student> stu = studentRepository.findById((long) 127);
	Student updateStudent = stu.orElse(new Student());
	updateStudent.setFname("Kyle");

	log.info(stu.toString());
	studentRepository.save(updateStudent);
	
};
}


}
