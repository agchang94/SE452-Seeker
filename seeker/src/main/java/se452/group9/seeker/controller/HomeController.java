package se452.group9.seeker.controller;

import se452.group9.seeker.repo.*;
import se452.group9.seeker.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.group9.seeker.repo.JobRepository;
//import se452.group9.seeker.service.JobService;
import se452.group9.seeker.service.SearchService;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

    private final JobRepository jobRepository;
    private final SearchService service;
    
    @Autowired
	public HomeController(JobRepository jobRepository, LocationRepository location, CompanyRepository company){
		this.jobRepository = jobRepository;
        service = new SearchService(jobRepository, location, company);
    }

    @GetMapping("jobsListing")
    public String listAllJobs(Model model){
        //Iterable<Job> jobs = jobRepository.findAll();
        Iterable<Job> jobs = service.search(null);
        model.addAttribute("jobs", jobs);
        return "jobsListing";
    }    
    
    @GetMapping("searchResults")
    public String searchResults(Model model, @Param("keyword") String keyword){
        Iterable<Job> jobs = service.search(keyword);
        model.addAttribute("jobs", jobs);
        model.addAttribute("keyword", keyword);  
        return "jobsListing";
    }
}