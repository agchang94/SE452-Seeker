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

	/*

	*/



}
