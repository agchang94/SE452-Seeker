package se452.group9.seeker;

import se452.Persistence.StudentAcademicReader;
// import se452.Persistence.StudentCertsReader;
import se452.Persistence.StudentReader;

import se452.group9.seeker.model.*;
import se452.group9.seeker.repo.StudentAcademicRepository;
import se452.group9.seeker.repo.StudentRepository;
// import se452.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import java.util.Optional;
import java.util.List;
import java.sql.Date;


// import org.h2.command.Command;
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
  public CommandLineRunner saveJobAlert(JobAlertRepository repository) {
    return (args) -> {
      JobAlert JobAlert1 = new JobAlert();
      JobAlert1.setId(111);
      JobAlert1.setSearchTerm1("searchTerm1");
	  JobAlert1.setSearchTerm2("searchTerm2");
	  JobAlert1.setSearchTerm3("searchTerm3");
      repository.deleteAll(); 
	  repository.save(JobAlert1);
   
    };
  }

  @Bean
  public CommandLineRunner saveJobSkill(JobSkillRepository repository) {
    return (args) -> {
      JobSkill JobSkill1 = new JobSkill();
      JobSkill1.setId(111);
      JobSkill1.setSkills("Skill1, Skill2, Skill3");
	  repository.deleteAll();  
	  repository.save(JobSkill1);
   
    };
  }

  @Bean
  public CommandLineRunner saveJobType(JobTypeRepository repository) {
    return (args) -> {
      JobType JobType1 = new JobType();
      JobType1.setId(111);
      JobType1.setType("Computer Engineer");;
	  repository.deleteAll();  
	  repository.save(JobType1);
   
    };
  }


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
	  repository.deleteAll();  
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
	
/*@Bean
public CommandLineRunner addStudentCerts (StudentCertsRepository studentCertsRepository) {
	return (args) -> {
	
		StudentCerts s0 = new StudentCerts();
        StudentCerts s1 = new StudentCerts();
        StudentCerts s3 = new StudentCerts();
        
		s0.setCerts(Arrays.asList("cisco, html, java"));

        s1.setCerts(Arrays.asList("none"));

        s3.setCerts(Arrays.asList("Microsoft tech associate", "test"));            

        studentCertsRepository.deleteAll();
        studentCertsRepository.save(s0);
		studentCertsRepository.save(s1);
		studentCertsRepository.save(s3);
	};
	} */


@Bean
public CommandLineRunner addStudents (StudentRepository studentRepository ,StudentAcademicRepository studentAcademicRepository, StudentLogsRepository logsRepo, 
StudentResumeRepository resumeRepo ) {

	
	return (args) -> {
		try{
			InputStream in =  getClass().getResourceAsStream("/students.txt");
			Reader fr = new InputStreamReader(in, "utf-8");

			InputStream in2 =  getClass().getResourceAsStream("/student_academics.txt");
			Reader fr2 = new InputStreamReader(in2, "utf-8");

		
			BufferedReader reader = new BufferedReader(fr); 
			ArrayList<Student> students = new ArrayList<Student>();
			students = StudentReader.getStudents(reader);

			BufferedReader reader2 = new BufferedReader(fr2); 
			ArrayList<StudentAcademics> studentAcademics = new ArrayList<StudentAcademics>();
			studentAcademics = StudentAcademicReader.getStudentAcademics(reader2);


			
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

			StudentLogs sl = new StudentLogs();
			sl.setLastApplication(Date.valueOf("2021-04-30"));
			sl.setLastLogin(Date.valueOf("2021-04-30"));
			sl.setStudent(students.get(2));
			logsRepo.save(sl);

			
			StudentResume sr = new StudentResume();
			sr.setIsCurrentJob("N");
			sr.setStartDate(Date.valueOf("2014-12-03"));
			sr.setEndDate(Date.valueOf("2019-04-19"));			
			sr.setCompany("company name");
			sr.setTitle("title");
			sr.setCity("city");
			sr.setState("state");
			sr.setCountry("country");
			sr.setDescription("...");
			sr.setStudent(students.get(2));
			resumeRepo.save(sr);

			StudentResume sr2 = new StudentResume();
			sr2.setIsCurrentJob("N");
			sr2.setStartDate(Date.valueOf("2010-10-10"));
			sr2.setEndDate(Date.valueOf("2015-06-25"));			
			sr2.setCompany("Apple");
			sr2.setTitle("Developer");
			sr2.setCity("Los Angeles");
			sr2.setState("California");
			sr2.setCountry("United States");
			sr2.setDescription("Coded cool stuff");
			sr2.setStudent(students.get(2));
			resumeRepo.save(sr2);

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	};
}

//@Bean




	/** SAMPLE Queries **/
	// private static final Logger log = LoggerFactory.getLogger(SeekerApplication.class);

	@Bean
	public CommandLineRunner addCompanyJobs(CompanyRepository companyRepo, JobRepository jobRepo) {
		return (args) -> {
			log.info("--------  Adding jobs to the job table  ----------");
			Job job1 = new Job();
			Job job2 = new Job();
			Job job3 = new Job();
			Job job4 = new Job();

			job1.setTitle("Junior Software Developer");
			job2.setTitle("Python Software Engineer");
			job3.setTitle("Front End Developer");
			job4.setTitle("Software Developer");

			job1.setDescription("Skills with relational databases and moderately advanced SQL is a must");
			job2.setDescription("Work on backend applications to support new features and capabilities");
			job3.setDescription("Build front-end frames that integrate easily with other systems and technologies");
			job4.setDescription("Develops and codes software programs, algorithms and automated processes");

			job1.setDatePosted("2021-04-01");
			job2.setDatePosted("2021-04-02");
			job3.setDatePosted("2021-04-03");
			job4.setDatePosted("2021-04-04");

			job1.setIsActive(true);
			job2.setIsActive(true);
			job3.setIsActive(true);
			job4.setIsActive(true);

			job1.setRequirements("College degree (Associates or Bachelors)");
			job2.setRequirements("AWS, Python, Javascript, XML, JSON, MySQL etc.");
			job3.setRequirements("2-5 years application development experience");
			job4.setRequirements("Java, 1 year (Preferred). Master degree (Preferred)");

			log.info("--------------------------------------------------");


			log.info("--------  Adding companies ----------- ");

			Company c1 = new Company();
			Company c2 = new Company();
			Company c3 = new Company();
			Company c4 = new Company();

			c1.setCompanyName("Capital One");
			c2.setCompanyName("AllState");
			c3.setCompanyName("Motorola Solutions");
			c4.setCompanyName("FakeCompany");

			c1.setAddress("Chicago, IL 60695");
			c2.setAddress("2775 Sanders Rd, NORTHBROOK, IL 60062");
			c3.setAddress("500 W. Monroe St, Chicago, IL");
			c4.setAddress("600 W. Fake Street, Chicago, IL");

			c1.setCompanyInfo("A bank holding company");
			c2.setCompanyInfo("An insurance company");
			c3.setCompanyInfo("Data communications and telecommunications equipment provider");
			c4.setCompanyInfo("A company created for testing purposes");
			
			// matching jobs to companies
			c1.addJob(job1);
			c1.addJob(job2);
			c2.addJob(job3);
			c3.addJob(job4);

			// saving to the repositories
			jobRepo.save(job1);
			jobRepo.save(job2);
			jobRepo.save(job3);
			jobRepo.save(job4);

			companyRepo.save(c1);
			companyRepo.save(c2);
			companyRepo.save(c3);
			companyRepo.save(c4);

			log.info("--------------------------------------------------- ");
		};
	}
	
	@Bean
	public CommandLineRunner showAllJobs(JobRepository repository) {
		return (args) -> {
			log.info("--------  Jobs found with findAll() ----------- ");
			repository.findAll().forEach((job)-> {
				log.info(job.toString());
			});
			log.info("-------------------------------------------------");
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
	public CommandLineRunner addRecruiter(RecruiterRepository repository) {
		return (args) -> {
			log.info("--------  Adding recruiter Chris Field ----------- ");

			Recruiter rec = repository.findByrecruiterID(4);

			
			// if recruiter with id 4 is not in database, add him
			if((rec == null) || (rec.getRecruiterID() != 4)) {
				Recruiter newRecruit = new Recruiter();
				newRecruit.setRecruiterID(4);
				newRecruit.setRecruiterCompany("FakeCompany");
				newRecruit.setFname("Chris");
				newRecruit.setLname("Field");
				newRecruit.setEmail("test01@depaul.edu");

				repository.save(newRecruit);
			}
			else {
				log.info("recruiter with recruiterID [4] is already in database");
			}
			
			log.info("----------------------------------------------------- ");

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
	

	//@Bean
	public CommandLineRunner addStudentResume(StudentResumeRepository resumeRepo) {
		return (args) -> {
			// adding dummy resume information
			log.info("--------  Adding Resume info ----------- ");
			StudentResume sr = new StudentResume();
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

			
		};
	}

	// [Mike]: I commented out this section for now since it kept creating duplicate entries starting the app
	/*
	@Bean
	public CommandLineRunner addDummyAttributes (StudentAttributesRepository sar){
		return strings -> {
			StudentAttributes sa = new StudentAttributes();
			sa.setStudentID(1);
			sa.setLanguages("languages");
			sa.setSkills("skills");
			sar.save(sa);

			sar.save(new StudentAttributes(9999, "i exist i guess", "languages"));
        };
	}
	*/
	
	//@Bean
	public CommandLineRunner showStudentResumes(StudentResumeRepository repository) {
		return (args) -> {
			// fetching student resumes
			log.info("--------  Resumes found with findAll() ----------- ");
			repository.findAll().forEach((resume)-> {
				log.info(resume.toString());
			});
			log.info("------------------------------------------------------");
		};
	}

	//@Bean
	public CommandLineRunner showStudentLogs(StudentLogsRepository repository) {
		return (args) -> {
			// fetching student logs
			log.info("--------  Logs found with findAll() ----------- ");
			repository.findAll().forEach((logs)-> {
				log.info(logs.toString());
			});
			log.info("------------------------------------------------------");
		};
	}

	@Bean
	public CommandLineRunner showStudentAttributes(StudentAttributesRepository repository) {
		return (args) -> {
			// fetching attributes
			log.info("--------  Attributes found with findAll() ----------- ");
			repository.findAll().forEach((attributes)-> {
				log.info(attributes.toString());
			});
			log.info("------------------------------------------------------");
		};
	}

}