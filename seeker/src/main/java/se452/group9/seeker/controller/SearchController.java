package se452.group9.seeker.controller;

import se452.group9.seeker.model.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.group9.seeker.repo.JobRepository;
import se452.group9.seeker.repo.JobTypeRepository;

@Controller
@RequestMapping("/search/")
public class SearchController {

    private final JobRepository jobRepository;
    private final JobTypeRepository jobTypeRepository;

    @Autowired
	public SearchController(JobRepository jobRepository, JobTypeRepository jobTypeRepository){
		this.jobRepository = jobRepository;
        this.jobTypeRepository = jobTypeRepository;
	}

    @GetMapping("jobsListing")
    public String listAllJobs(Model model){
        Iterable<Job> jobs = jobRepository.findAll();
        // Iterable<JobType> jobTypes = jobTypeRepository.findAll();
        model.addAttribute("jobs", jobs);
        // model.addAttribute("jobTypes", jobTypes);
        return "jobsListing";
    }
    
}
