package se452.group9.seeker.repo;

import se452.group9.seeker.model.Company;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findBycompanyID(long id);

    //@Query("SELECT c FROM companies c WHERE c.companyName is LIKE %?1%")
    List<Company> findBycompanyName(String companyName);
}