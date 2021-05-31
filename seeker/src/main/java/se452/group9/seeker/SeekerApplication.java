package se452.group9.seeker;

import se452.Persistence.StudentAcademicReader;
// import se452.Persistence.StudentCertsReader;
import se452.Persistence.StudentReader;

import se452.group9.seeker.model.*;
import se452.group9.seeker.repo.StudentAcademicRepository;
import se452.group9.seeker.repo.StudentRepository;
// import se452.*;
import se452.group9.seeker.singleton.CertsSingleton;
import se452.group9.seeker.singleton.LanguageSingleton;
import se452.group9.seeker.singleton.SkillsSingleton;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	@Bean
	public CommandLineRunner addStudentCerts (StudentCertsRepository studentCertsRepository) {
		return (args) -> {
		
			StudentCerts s0 = new StudentCerts();
			StudentCerts s1 = new StudentCerts();
			StudentCerts s3 = new StudentCerts();
			StudentCerts s4 = new StudentCerts();
			StudentCerts s5 = new StudentCerts();
			
			s0.setCerts(Arrays.asList("cisco, html, java"));
			s0.setId((long) 1);
			s1.setId((long) 2);
			s3.setId((long) 3);
			s4.setId((long) 4);
			s5.setId((long) 5);
	
			s1.setCerts(Arrays.asList("none"));
	
			s3.setCerts(Arrays.asList("Microsoft tech associate", "test"));
			
			s4.setCerts(Arrays.asList("AWS Certified Solutions Architect "));
			s5.setCerts(Arrays.asList("CompTIA A+, Certified Information Security Manager "));
	
			studentCertsRepository.deleteAll();
			studentCertsRepository.save(s0);
			studentCertsRepository.save(s1);
			studentCertsRepository.save(s3);
			studentCertsRepository.save(s4);
			studentCertsRepository.save(s5);
		};
		} 

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

			StudentResume sr3 = new StudentResume();
			sr3.setIsCurrentJob("N");
			sr3.setStartDate(Date.valueOf("2013-12-03"));
			sr3.setEndDate(Date.valueOf("2017-04-19"));			
			sr3.setCompany("Drake's House");
			sr3.setTitle("Janitor");
			sr3.setCity("Toronto");
			sr3.setState("Ontorio");
			sr3.setCountry("Canada");
			sr3.setDescription("Cleaned Floors");
			sr3.setStudent(students.get(1));
			resumeRepo.save(sr3);
			
			
			StudentResume sr = new StudentResume();
			sr.setIsCurrentJob("N");
			sr.setStartDate(Date.valueOf("2014-12-03"));
			sr.setEndDate(Date.valueOf("2019-04-19"));			
			sr.setCompany("MicroSoft");
			sr.setTitle("Developer");
			sr.setCity("Seattle");
			sr.setState("Washington");
			sr.setCountry("United States");
			sr.setDescription("Managed database operations");
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

			StudentResume sr4 = new StudentResume();
			sr4.setIsCurrentJob("N");
			sr4.setStartDate(Date.valueOf("2010-10-10"));
			sr4.setEndDate(Date.valueOf("2015-06-25"));			
			sr4.setCompany("Cisco");
			sr4.setTitle("Cybersecurity Expert");
			sr4.setCity("Los Angeles");
			sr4.setState("California");
			sr4.setCountry("United States");
			sr4.setDescription("Prevented Hackers from Stealing Information");
			sr4.setStudent(students.get(3));
			resumeRepo.save(sr4);

			StudentResume sr5 = new StudentResume();
			sr5.setIsCurrentJob("Y");
			sr5.setStartDate(Date.valueOf("2019-07-01"));
			sr5.setEndDate(Date.valueOf("2021-05-30"));			
			sr5.setCompany("University of Illinois at Chicago");
			sr5.setTitle("Registered Nurse");
			sr5.setCity("Chicago");
			sr5.setState("Illinois");
			sr5.setCountry("United States");
			sr5.setDescription("Take Care of Sick People");
			sr5.setStudent(students.get(4));
			resumeRepo.save(sr5);

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	};
}

//@Bean




	/** SAMPLE Queries **/
	// private static final Logger log = LoggerFactory.getLogger(SeekerApplication.class);

	@Bean
	public CommandLineRunner addCompanyStats(CompanyRepository companyRepo, JobRepository jobRepo, JobTypeRepository jobTypeRepo, JobSkillRepository jobSkillRepo){
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

			job1.setDesc("Skills with relational databases and moderately advanced SQL is a must");
			job2.setDesc("Work on backend applications to support new features and capabilities");
			job3.setDesc("Build front-end frames that integrate easily with other systems and technologies");
			job4.setDesc("Develops and codes software programs, algorithms and automated processes");

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

			JobType jobType1 = new JobType(job1.getId(),"fulltime");
			JobType jobType2 = new JobType(job2.getId(),"parttime");
			JobType jobType3 = new JobType(job3.getId(),"fulltime");
			JobType jobType4 = new JobType(job4.getId(),"temporary");

			JobSkill jobSkill1 = new JobSkill(job1.getId(), "Java, Angular, HTML");
			JobSkill jobSkill2 = new JobSkill(job2.getId(), "JavaScript, Python");
			JobSkill jobSkill3 = new JobSkill(job3.getId(), "Test");
			JobSkill jobSkill4 = new JobSkill(job4.getId(), "Test2");


			jobTypeRepo.save(jobType1);
			jobTypeRepo.save(jobType2);
			jobTypeRepo.save(jobType3);
			jobTypeRepo.save(jobType4);

			jobSkillRepo.save(jobSkill1);
			jobSkillRepo.save(jobSkill2);
			jobSkillRepo.save(jobSkill3);
			jobSkillRepo.save(jobSkill4);


			//saving companies
			companyRepo.save(c1);
			companyRepo.save(c2);
			companyRepo.save(c3);
			companyRepo.save(c4);


			log.info("--------------------------------------------------- ");
		};
	}

	@Bean
	public CommandLineRunner setUpLocations(LocationRepository locRepo, CompanyRepository comRepo) {
		return (args) -> {
			log.info("--------  Setting up Locations  ----------");

			Location loc1 = new Location();
			Location loc2 = new Location();
			Location loc3 = new Location();
			Location loc4 = new Location();

			loc1.setStreetName("1465 E 53rd St");
			loc2.setStreetName("2775 Sanders Rd");
			loc3.setStreetName("500 W. Monroe St");
			loc4.setStreetName("600 W. Fake St");

			loc1.setCityName("Chicago");
			loc2.setCityName("Northbrook");
			loc3.setCityName("Chicago");
			loc4.setCityName("Chicago");

			loc1.setStateName("IL");
			loc2.setStateName("IL");
			loc3.setStateName("IL");
			loc4.setStateName("IL");

			loc1.setCompanyName("Capital One");
			loc2.setCompanyName("AllState");
			loc3.setCompanyName("Motorola Solutions");
			loc4.setCompanyName("FakeCompany");
			
			// getting companyID and setting them
			List<Company> companyList = comRepo.findAll();

			loc1.setCompanyID(companyList.get(0).getCompanyID());
			loc2.setCompanyID(companyList.get(1).getCompanyID());
			loc3.setCompanyID(companyList.get(2).getCompanyID());
			loc4.setCompanyID(companyList.get(3).getCompanyID());

			
			//saving locations
			locRepo.save(loc1);
			locRepo.save(loc2);
			locRepo.save(loc3);
			locRepo.save(loc4);




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
			// log.info("--------  Recruiters found with findAll() ----------- ");
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
			// log.info("--------  Resumes found with findAll() ----------- ");
			repository.findAll().forEach((resume)-> {
				log.info(resume.toString());
			});
			// log.info("------------------------------------------------------");
		};
	}

	//@Bean
	public CommandLineRunner showStudentLogs(StudentLogsRepository repository) {
		return (args) -> {
			// fetching student logs
			// log.info("--------  Logs found with findAll() ----------- ");
			repository.findAll().forEach((logs)-> {
				log.info(logs.toString());
			});
			// log.info("------------------------------------------------------");
		};
	}

	@Bean
	public CommandLineRunner showStudentAttributes(StudentAttributesRepository repository) {
		return (args) -> {
			// fetching attributes
			// log.info("--------  Attributes found with findAll() ----------- ");
			repository.findAll().forEach((attributes)-> {
				log.info(attributes.toString());
			});
			// log.info("------------------------------------------------------");
		};
	}

	@Bean
	public CommandLineRunner testSingletons(StudentRepository students) {
		return (args) -> {
			// adding dummy info to test the Singleton NoSQL classes for Attributes and Certs
			for(Student s : students.findAll()){
				List<String> toTest = new ArrayList<>();
				toTest.add(s.getFname()); 
				toTest.add(s.getLname());

				CertsSingleton.createCerts(s.getId(), toTest);
				LanguageSingleton.createLanguages(s.getId(), toTest);
				SkillsSingleton.createSkills(s.getId(), toTest);
			}			
		};
	}


    // @Bean
	// public CommandLineRunner addStudentwithApp(StudentRepository studentRepo, ApplicationRepository appRepo) {
	// 	return (args) -> {
	// 		// fetching attributes
	// 		// log.info("--------  Save App ----------- ");
	// 		Student student = new Student();
	// 		student.setFname("Korey");
	// 		student.setLname("Lo");

	// 		studentRepo.save(student);

	// 		Application app = new Application();
	// 		app.setApplicationDate("05-31-21");
	// 		app.setApplicationStatus("pending");
	// 		app.setStudent(student);

	// 		appRepo.save(app);

	// 		// log.info("------------------------------------------------------");
	// 	};
	// }

}