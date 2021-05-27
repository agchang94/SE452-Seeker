package se452.group9.seeker.controller;

import se452.group9.seeker.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.group9.seeker.repo.JobRepository;


@Controller
@RequestMapping("/")
public class HomeController {

    private final JobRepository jobRepository;

    @Autowired
	public HomeController(JobRepository jobRepository){
		this.jobRepository = jobRepository;
	}

    // @GetMapping("jobsListing")
    // public String listAllJobs(Model model){
    //     Iterable<Job> jobs = jobRepository.findAll();
    //     model.addAttribute("jobs", jobs);
    //     return "jobsListing";
    // }
}