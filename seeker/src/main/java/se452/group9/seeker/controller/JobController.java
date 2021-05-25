package se452.group9.seeker.controller;

import se452.group9.seeker.model.Job;
import se452.group9.seeker.model.Student;
import se452.group9.seeker.repo.JobRepository;
import se452.group9.seeker.repo.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;



@Controller
@RequestMapping("/jobs/")
public class JobController {

	private final JobRepository jobRepository;
    private final StudentRepository studentRepository;
	
	@Autowired
	public JobController(JobRepository jobRepository, StudentRepository studentRepository){
		this.jobRepository = jobRepository;
        this.studentRepository=studentRepository;
	} 

	@GetMapping("addJob")
    public String addJobForm(Job job) {
        return "addJob";
    }

    @GetMapping("jobView/{id}")
    public String jobView(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobRepository.getOne(id));
        return "jobView";
    }

    @GetMapping("addRegister")
    public String getRegisterSuccess(Student student) {
        return "register_sucess";
    }


    @GetMapping("register")
    public String showSignUpForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("addRegister")
    public String processRegistration(Student student){

        studentRepository.save(student);
        return "register_success";
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