package se452.group9.seeker;

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

}
