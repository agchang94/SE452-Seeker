package se452.group9.seeker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se452.group9.seeker.model.Job;
import se452.group9.seeker.repo.CompanyRepository;
import se452.group9.seeker.repo.JobRepository;
import se452.group9.seeker.repo.LocationRepository;
import se452.group9.seeker.model.Location;

@Service
public class SearchService {
    @Autowired
    private final JobRepository jobRepo;
    private final CompanyRepository companyRepo;
    private final LocationRepository locationRepo;

    public SearchService(JobRepository jobRepo, LocationRepository locationRepo, CompanyRepository companyRepo){
        this.jobRepo = jobRepo;
        this.companyRepo = companyRepo;
        this.locationRepo = locationRepo;
    }
    
    public List<Job> search(String keyword){
        if(keyword != null ){
            String upper = keyword.toUpperCase();
            String lower = keyword.toLowerCase();
            String k = upper.charAt(0) + lower.substring(1);

            List<Job> j = searchByTitle(upper, lower, k);
            //j.addAll(searchByLocation(upper, lower, k));
            return j;
        }
        return jobRepo.findAll();
    }

    private List<Job> searchByTitle(String upper, String lower, String k){
        List<Job> search = jobRepo.searchByTitle(k);
        search.addAll(jobRepo.searchByTitle(upper));
        search.addAll(jobRepo.searchByTitle(lower));
        return search;      
    }

    /** 
    private List<Job> searchByLocation(String upper, String lower, String k){
        //find all the matching locations
        List<Location> loc = locationRepo.search(upper);
        loc.addAll(locationRepo.search(lower));
        loc.addAll(locationRepo.search(k));

        List<Job> job;
        for(Location l: loc){
            companyRepo.findBycompanyID(l.getCompanyID());
        }
 
    }

    private List<Job> searchByCompany(String keyword){

    }*/
}
