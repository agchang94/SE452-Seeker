package se452.group9.seeker.service;

import java.lang.ProcessHandle.Info;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se452.group9.seeker.model.Company;
import se452.group9.seeker.model.Job;
import se452.group9.seeker.model.JobType;
import se452.group9.seeker.repo.CompanyRepository;
import se452.group9.seeker.repo.JobRepository;
import se452.group9.seeker.repo.JobTypeRepository;


@Service
public class SearchService {
    @Autowired
    private final JobRepository jobRepo;
    private final CompanyRepository companyRepo;
    private final JobTypeRepository jobTypeRepo;

    public SearchService(JobRepository jobRepo, CompanyRepository companyRepo, JobTypeRepository jobTypeRepo){
        this.jobRepo = jobRepo;
        this.companyRepo = companyRepo;
        this.jobTypeRepo = jobTypeRepo;
    }

    
    public List<Job> search(String keyword){
        if(keyword != null ){
            keyword = keyword.toLowerCase();
            List<Job> j = searchByTitle(keyword);
            j.addAll(searchByLocation(keyword));
            j.addAll(searchByCompany(keyword));
            j.addAll(searchByJobType(keyword));
            // return removeDupes(j);
            return j;
        }
        return jobRepo.findAll();
    }

    private List<Job> searchByTitle(String keyword){       
        return jobRepo.searchByTitle(keyword);      
    }
    
    private List<Job> searchByLocation(String keyword){
        List<Job> job = new ArrayList<>();
        for(Company c : companyRepo.findByAddress(keyword)) {
            job.addAll(c.getJobs());
        } 
        return job;
    }
    
    private List<Job> searchByCompany(String keyword){
        //find all companies similar to search term
        List<Company> comp = companyRepo.findByCompanyName(keyword);

        //get all jobs related to the company search
        List<Job> job = new ArrayList<>();
        for(Company c : comp){
            job.addAll(c.getJobs());
        }
        return job;
    }

    private List<Job> searchByJobType(String keyword){
       
        //get all jobs related to the company search
        List<Job> job = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        jobTypeRepo.findByType(keyword).forEach((jt)->{
            ids.add(jt.getId());
        });
        job = jobRepo.findAllById(ids);
               
        return job;
    }

    private List<Job> removeDupes(List<Job> jobs){
        
        List<Job> j = new ArrayList<>();

        for(Job job : jobs){
            if(!j.contains(job)){
                j.add(job);
            }
        }
        return j;
    }
}
