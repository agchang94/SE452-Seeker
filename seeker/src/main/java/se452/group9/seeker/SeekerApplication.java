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
import java.util.Calendar;
import java.util.Optional;
import java.util.List;
import java.util.Set;
import java.sql.Date;
import java.sql.Timestamp;

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
public CommandLineRunner addStudents (StudentRepository studentRepository, StudentAcademicRepository studentAcademicRepository, StudentCertsRepository studentCertsRepository) {
	
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


	@Bean
	public CommandLineRunner addDummyStudentResumeLogAttr(StudentResumeRepository resumeRepo, StudentLogsRepository logsRepo, StudentAttributesRepository SARepo) {
		return (args) -> {
			// adding dummy resume information
			log.info("--------  Adding Resume info ----------- ");
			StudentResume sr = new StudentResume();
			sr.setStudentID(9999);
			sr.setIsCurrentJob("N");
			sr.setStartDate(Date.valueOf("2014-12-03"));
			sr.setEndDate(Date.valueOf("2019-04-19"));			
			sr.setCompany("company name");
			sr.setTitle("title");
			sr.setCity("city");
			sr.setState("state");
			sr.setCountry("country");
			sr.setDescription("...");	
			resumeRepo.save(sr);

			StudentLogs sl = new StudentLogs();
			sl.setStudentID(9999);
			sl.setLastApplication(Date.valueOf("2021-04-30"));
			sl.setLastLogin(Date.valueOf("2021-04-30"));
			logsRepo.save(sl);

			StudentAttributes sa = new StudentAttributes();
			sa.setStudentID(9999);
			sa.setSkills("skills");
			sa.setLanguages("languages");
			SARepo.save(sa);
		};
	}

	@Bean
	public CommandLineRunner showResumes(StudentResumeRepository repository) {
		return (args) -> {
			// fetching companies
			log.info("--------  Companies found with findAll() ----------- ");
			repository.findAll().forEach((resume)-> {
				log.info(resume.toString());
			});
			log.info("------------------------------------------------------");
		};
	}

	@Bean
	public CommandLineRunner showAttributes(StudentAttributesRepository repository) {
		return (args) -> {
			// fetching companies
			log.info("--------  Companies found with findAll() ----------- ");
			repository.findAll().forEach((attributes)-> {
				log.info(attributes.toString());
			});
			log.info("------------------------------------------------------");
		};
	}

	@Bean
	public CommandLineRunner showLogs(StudentLogsRepository repository) {
		return (args) -> {
			// fetching companies
			log.info("--------  Companies found with findAll() ----------- ");
			repository.findAll().forEach((logs)-> {
				log.info(logs.toString());
			});
			log.info("------------------------------------------------------");
		};
	}
}
