package se452.group9.seeker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se452.group9.seeker.model.Job;
import se452.group9.seeker.repo.JobRepository;

@Service
public class SearchService {
    @Autowired
    private final JobRepository repo;

    public SearchService(JobRepository repo){
        this.repo = repo;
    }    

    public List<Job> listAll(String keyword){
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
}
