package se452.group9.seeker.repo;

import se452.group9.seeker.model.Company;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findBycompanyID(long id);

    @Query("SELECT c FROM Company c WHERE lower(c.companyName) LIKE %?1%")
    List<Company> findByCompanyName(String companyName);

    @Query("SELECT c from Company c where lower(c.address) LIKE %?1%")
    List<Company> findByAddress(String address);    
}