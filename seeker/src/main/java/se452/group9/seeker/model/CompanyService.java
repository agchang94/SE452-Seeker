package se452.group9.seeker.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se452.group9.seeker.repo.CompanyRepository;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
    
    @Override
    public Company add(Company company) {
        companyRepository.save(company);
        return company;
    }
  
    @Override
    public Company findBycompanyID(int id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public Company findBycompanyName(String companyName) {
        return companyRepository.findBycompanyName(companyName).get(0);
    }
    
    @Override
    public void deleteBycompanyID(int id) {
        companyRepository.deleteById(id);
    }
    
}
