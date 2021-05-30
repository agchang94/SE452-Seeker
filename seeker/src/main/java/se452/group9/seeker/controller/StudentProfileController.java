package se452.group9.seeker.controller;

import se452.group9.seeker.model.JobSkill;
import se452.group9.seeker.model.Student;
import se452.group9.seeker.model.StudentAcademics;
import se452.group9.seeker.model.StudentCerts;
import se452.group9.seeker.model.StudentResume;
import se452.group9.seeker.repo.JobSkillRepository;
import se452.group9.seeker.repo.StudentAcademicRepository;
import se452.group9.seeker.repo.StudentCertsRepository;
import se452.group9.seeker.repo.StudentRepository;
import se452.group9.seeker.repo.StudentResumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/")
public class StudentProfileController {
    private final StudentRepository studentRepository;
    private final StudentAcademicRepository studentAcademicRepository;
    private final StudentCertsRepository studentCertsRepository;
    private final StudentResumeRepository studentResumeRepository;
    private final JobSkillRepository jobSkillRepository;

    @Autowired
	public StudentProfileController(StudentRepository studentRepository, StudentAcademicRepository studentAcademicRepository, 
        StudentCertsRepository studentCertsRepository, StudentResumeRepository studentResumeRepository
        ,JobSkillRepository jobSkillRepository){
        this.studentRepository=studentRepository;
        this.studentAcademicRepository=studentAcademicRepository;
        this.studentCertsRepository=studentCertsRepository;
        this.studentResumeRepository=studentResumeRepository;
        this.jobSkillRepository=jobSkillRepository;
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
    
    
    
    
    @GetMapping("studentacademic")
    public String showAcademicForm(Model model) {
        List<Student> listStudents = studentRepository.findAll();
        model.addAttribute("listStudents", listStudents);
        model.addAttribute("studentacademics", new StudentAcademics());
        return "addAcademic";
    }

    @PostMapping("studentacademic")
    public String addAcademic(StudentAcademics studentAcademics){
        studentAcademicRepository.save(studentAcademics);
        return "register_success";
    }

    @GetMapping("studentjob")
    public String showJobform(Model model) {
        List<Student> listStudents = studentRepository.findAll();
        model.addAttribute("listStudents", listStudents);
        model.addAttribute("studentresume", new StudentResume());
        return "addStudentJob";
    }

    @PostMapping("studentjob")
    public String addJob(StudentResume studentResume) {
        studentResumeRepository.save(studentResume);
        return "register_success";
    }
    
    
    @GetMapping("studentcerts")
    public String showCertsForm(Model model) {
        model.addAttribute("studentcerts", new StudentCerts());
        return "addCerts";
    }

    @PostMapping("studentcerts")
    public String addCerts(StudentCerts studentCerts){
        studentCertsRepository.save(studentCerts);
        return "register_success"; 
    }

    @GetMapping("studentskills")
    public String showSkillsForm(Model model) {
        model.addAttribute("jobskill", new JobSkill());
        return "addSkill";
    }

    @PostMapping("studentskills")
    public String addSkills(JobSkill jobSkill) {
        jobSkillRepository.save(jobSkill);
        return "register_success";
    }
    }


