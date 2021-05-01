package se452.group9.seeker.repo;

import se452.group9.seeker.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    
}
