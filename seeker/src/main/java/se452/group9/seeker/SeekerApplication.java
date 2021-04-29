package se452.group9.seeker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeekerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeekerApplication.class, args);
	}


	/** SAMPLE Queries **/
	private static final Logger log = LoggerFactory.getLogger(SeekerApplication.class);

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
