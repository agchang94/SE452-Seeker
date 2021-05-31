package se452.group9.seeker.model;

import java.util.List;

public interface ICompanyService {

    public List<Company> findAll();
    
    public Company add(Company company);
  
    public Company findBycompanyID(long id);

    public Company findBycompanyName(String companyName);

    public Company updateCompany(Company company);
    
    
}
