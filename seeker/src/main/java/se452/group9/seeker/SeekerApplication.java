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

import se452.group9.seeker.repo.*;

@SpringBootApplication
public class SeekerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SeekerApplication.class, args);

  }

  private static final Logger log = LoggerFactory.getLogger(SeekerApplication.class);


  @Bean
  public CommandLineRunner saveSchool(SchoolRepository repository) {
    return (args) -> {
      School school1 = new School();
      school1.setSchoolID(1);;
      school1.setSchoolName("Illinois");
      repository.save(school1);

      School school2 = new School();
      school2.setSchoolID(2);;
      school2.setSchoolName("Wisconsin");
      repository.save(school2);      
    };
  }

  @Bean
  public CommandLineRunner showCourseReview(SchoolRepository repository) {
    return (args) -> {
      List<School> reviews = repository.findAll();
      for (School review : reviews) {
        log.info(review.toString());
      }
    };
  }

	
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



	/** SAMPLE Queries **/
	// private static final Logger log = LoggerFactory.getLogger(SeekerApplication.class);

	@Bean
	public CommandLineRunner addRecruiter(RecruiterRepository repository) {
		return (args) -> {
			log.info("--------  Adding recruiter TESTING RECRUITER ----------- ");
			Recruiter newRecruit = new Recruiter();
			newRecruit.setRecruiterID(404);
			newRecruit.setCompanyID(502);
			newRecruit.setFName("TESTING");
			newRecruit.setLName("RECRUITER");
			newRecruit.setEmail("test01@depaul.edu");

			repository.save(newRecruit);
		};
	}

	@Bean
	public CommandLineRunner showRecruiters(RecruiterRepository repository) {
		return (args) -> {
			// fetching recruiters
			log.info("--------  Recruiters found with findAll() ----------- ");
			repository.findAll().forEach((recruiter)-> {
				log.info(recruiter.toString());
			});
			log.info("------------------------------------------------------");
		};
	}

	@Bean
	public CommandLineRunner addDummyJob(DummyJobsRepository repository) {
		return (args) -> {
			log.info("--------  Adding dummyJob dummyJob1 ----------- ");
			DummyJobs newJob = new DummyJobs();
			newJob.setJobTitle("dummyJob1");
			newJob.setDummyJobID(604);
			newJob.setJobMsg("Develop fake applications");

			repository.save(newJob);
		};
	}

	@Bean
	public CommandLineRunner showDummyJobs(DummyJobsRepository repository) {
		return (args) -> {
			// fetching jobs
			log.info("--------  Jobs found with findAll() ----------- ");
			repository.findAll().forEach((job)-> {
				log.info(job.toString());
			});
			log.info("------------------------------------------------------");
		};
	}

	@Bean
	public CommandLineRunner addCompany(CompanyRepository companyRepo, DummyJobsRepository jobRepo) {
		return (args) -> {
			log.info("--------  Adding company DummyCompany1 ----------- ");
			// create a fake job for the company
			DummyJobs tmpJob = new DummyJobs();
			tmpJob.setJobTitle("FakeCompanyJob1");
			tmpJob.setJobMsg("Job created by company DummyCompany1");
			jobRepo.save(tmpJob);

			// create the company
			Company newCompany = new Company();
			newCompany.setCompanyName("DummyCompany1");
			newCompany.setAddress("600 W. Fake Street, Chicago, IL");
			newCompany.setCompanyInfo("A company created for testing purposes");
			companyRepo.save(newCompany);

		};
	}

	@Bean
	public CommandLineRunner showCompanies(CompanyRepository repository) {
		return (args) -> {
			// fetching companies
			log.info("--------  Companies found with findAll() ----------- ");
			repository.findAll().forEach((company)-> {
				log.info(company.toString());
			});
			log.info("------------------------------------------------------");
		};
	}

}
