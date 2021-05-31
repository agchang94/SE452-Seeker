package se452.group9.seeker.controller;

import se452.group9.seeker.model.Job;
import se452.group9.seeker.model.JobSkill;
import se452.group9.seeker.model.JobType;
import se452.group9.seeker.model.Student;
import se452.group9.seeker.model.Application;
import se452.group9.seeker.repo.ApplicationRepository;
// import se452.group9.seeker.model.Student;
// import se452.group9.seeker.model.StudentAcademics;
// import se452.group9.seeker.model.StudentCerts;
import se452.group9.seeker.repo.JobRepository;
import se452.group9.seeker.repo.JobSkillRepository;
import se452.group9.seeker.repo.JobTypeRepository;
// import se452.group9.seeker.repo.StudentAcademicRepository;
// import se452.group9.seeker.repo.StudentCertsRepository;
// import se452.group9.seeker.repo.StudentRepository;
import se452.group9.seeker.repo.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;




@Controller
@RequestMapping("/jobs/")
public class JobController {

	private final JobRepository jobRepository;
    private final JobTypeRepository jobTypeRepository;
    private final JobSkillRepository jobSkillRepository;
    private long id;
    private final StudentRepository studentRepository;
    private final ApplicationRepository appRepo;
	
	@Autowired
	public JobController(JobRepository jobRepository, JobTypeRepository jobTypeRepository, JobSkillRepository jobSkillRepository, StudentRepository studentRepository, ApplicationRepository appRepo){
		this.jobRepository = jobRepository;
        this.jobTypeRepository = jobTypeRepository;
        this.jobSkillRepository = jobSkillRepository;
        this.studentRepository = studentRepository;
        this.appRepo = appRepo;
    }

        // this.studentRepository=studentRepository;
    // private final StudentRepository studentRepository;
    // private final StudentCertsRepository studentCertsRepository;
    // private final StudentAcademicRepository studentAcademicRepository;
	
	// @Autowired
	// public JobController(StudentCertsRepository studentCertsRepository,
    //     JobRepository jobRepository, StudentRepository studentRepository, StudentAcademicRepository studentAcademicRepository){
	// 	this.jobRepository = jobRepository;
    //     this.studentRepository=studentRepository;
    //     this.studentCertsRepository=studentCertsRepository;
    //     this.studentAcademicRepository=studentAcademicRepository;
	// } 

	@GetMapping("addJob")
    public String addJobForm(Job job) {
        return "addJob";
    }

    @GetMapping("jobView/{id}")
    public String jobView(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobRepository.getOne(id));
        jobTypeRepository.findById(id).ifPresent(o -> model.addAttribute("jobType", o));
        return "jobView";
    }

    @GetMapping("applyJob/{id}")
    public String applyJob(@PathVariable("id") long id, Authentication User, Model model, Application app){
        Student stu = studentRepository.findbyEmail(User.getName());
        model.addAttribute("job", jobRepository.getOne(id));
        jobTypeRepository.findById(id).ifPresent(o -> model.addAttribute("jobType", o));
        model.addAttribute("student", stu);
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("mm-dd-yy");  
        String strDate = dateFormat.format(date);
        app.setApplicationDate(strDate);
        app.setApplicationStatus("Pending");
        app.setJobID(id);
        app.setStudent(stu);
        appRepo.save(app);
        return "applyJob";
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
        return "redirect:addJobSkill";
    }

    @GetMapping("addJobSkill")
    public String getJobSkill(Model model, JobSkill jobSkill){
        model.addAttribute("jobSkillCarry", jobRepository.getOne(id));
        return "addJobSkill";
    }

    @PostMapping("addJobSkill")
    public String addJobType(JobSkill jobSkill) {
        // if (result.hasErrors()) {
        //     return "addJobType";
        // }
        jobSkill.setId(id);
        jobSkillRepository.save(jobSkill);
        return "redirect:/jobsListing";
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