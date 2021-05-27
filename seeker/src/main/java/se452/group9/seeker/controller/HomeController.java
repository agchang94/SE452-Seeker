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
    private final StudentRepository studentRepository;
    private final RecruiterRepository recruiterRepository;

    @Autowired
	public HomeController(JobRepository jobRepository, 
                          StudentRepository studentRepository, 
                          RecruiterRepository recruiterRepository){
		this.jobRepository = jobRepository;
        this.studentRepository = studentRepository;
        this.recruiterRepository = recruiterRepository;
	}
}