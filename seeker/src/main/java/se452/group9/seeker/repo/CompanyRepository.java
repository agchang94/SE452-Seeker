package se452.group9.seeker.repo;

import se452.group9.seeker.model.Company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findBycompanyID(int id);
    List<Company> findBycompanyName(String companyName);
}