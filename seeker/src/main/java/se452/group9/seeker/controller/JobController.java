package se452.group9.seeker.controller;

import se452.group9.seeker.model.Job;
import se452.group9.seeker.model.JobType;
import se452.group9.seeker.model.Student;
import se452.group9.seeker.repo.JobRepository;
import se452.group9.seeker.repo.JobTypeRepository;
import se452.group9.seeker.repo.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;



@Controller
@RequestMapping("/jobs/")
public class JobController {

	private final JobRepository jobRepository;
    private final JobTypeRepository jobTypeRepository;
    private long id;
    // private final StudentRepository studentRepository;
	
	@Autowired
	public JobController(JobRepository jobRepository, JobTypeRepository jobTypeRepository){
		this.jobRepository = jobRepository;
        this.jobTypeRepository = jobTypeRepository;

        // this.studentRepository=studentRepository;
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

    @GetMapping("jobPosts")
	public String jobPost(Model model) {
		model.addAttribute("jobs", jobRepository.findAll());
        return "jobPosts";
	}

	@PostMapping("addJob")
    public String addJob(@Valid Job job, BindingResult result) {
        if (result.hasErrors()) {
            return "addJob";
        }
        jobRepository.save(job);
        JobType jt = new JobType();
        this.id = job.getId();
        jt.setId(id);
        jobTypeRepository.save(jt);
        return "redirect:addJobType";
    }

    @GetMapping("addJobType")
    public String getJobType(Model model, JobType jobType){
        model.addAttribute("jobTypeCarry", jobRepository.getOne(id));
        return "addJobType";
    }

    @PostMapping("addJobType")
    public String addJobType(JobType jobType) {
        // if (result.hasErrors()) {
        //     return "addJobType";
        // }
        jobType.setId(id);
        jobTypeRepository.save(jobType);
        return "redirect:../jobsListing";
    }

    // @GetMapping("addRegister")
    // public String getRegisterSuccess(Student student) {
    //     return "register_sucess";
    // }


    // @GetMapping("register")
    // public String showSignUpForm(Model model) {
    //     model.addAttribute("student", new Student());
    //     return "register";
    // }

    // @PostMapping("addRegister")
    // public String processRegistration(Student student){

    //     studentRepository.save(student);
    //     return "register_success";
    // }



}