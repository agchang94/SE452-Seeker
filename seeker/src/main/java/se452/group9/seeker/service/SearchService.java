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
            //search parameters to display results regardless of case
            String upper = keyword.toUpperCase();
            String lower = keyword.toLowerCase();
            String k = upper.charAt(0) + lower.substring(1);

            List<Job> search = repo.search(k);
            search.addAll(repo.search(upper));
            search.addAll(repo.search(lower));
            return search;
        }
        return repo.findAll();
    }
}
