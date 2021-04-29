package se452.group9.seeker.controller;

import se452.group9.seeker.model.Job;
import se452.group9.seeker.repo.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;



@Controller
@RequestMapping("/jobs/")
public class JobController {

	private final JobRepository jobRepository;
	
	@Autowired
	public JobController(JobRepository jobRepository){
		this.jobRepository = jobRepository;
	} 

	@GetMapping("addJob")
    public String addJobForm(Job job) {
        return "addJob";
    }

	@GetMapping("jobPosts")
	public String jobPost(Model model) {
		model.addAttribute("jobs", jobRepository.findAll());
        return "jobPosts";
	}

	@PostMapping("addJob")
    public String addJob(@Valid Job job, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addJob";
        }

        jobRepository.save(job);
        return "redirect:jobPosts";
    }

}