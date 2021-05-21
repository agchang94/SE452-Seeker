package se452.group9.seeker.controller;

import se452.group9.seeker.model.Job;
import se452.group9.seeker.model.Student;
import se452.group9.seeker.model.StudentAcademics;
import se452.group9.seeker.model.StudentCerts;
import se452.group9.seeker.model.StudentResume;
import se452.group9.seeker.repo.JobRepository;
import se452.group9.seeker.repo.StudentAcademicRepository;
import se452.group9.seeker.repo.StudentCertsRepository;
import se452.group9.seeker.repo.StudentRepository;
import se452.group9.seeker.repo.StudentResumeRepository;

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

import javax.validation.Valid;

@Controller
@RequestMapping("/jobs/")
public class StudentProfileController {
    private final StudentRepository studentRepository;
    private final StudentAcademicRepository studentAcademicRepository;
    private final StudentCertsRepository studentCertsRepository;
    private final StudentResumeRepository studentResumeRepository;

    @Autowired
	public StudentProfileController(StudentRepository studentRepository, StudentAcademicRepository studentAcademicRepository, 
        StudentCertsRepository studentCertsRepository, StudentResumeRepository studentResumeRepository){
        this.studentRepository=studentRepository;
        this.studentAcademicRepository=studentAcademicRepository;
        this.studentCertsRepository=studentCertsRepository;
        this.studentResumeRepository=studentResumeRepository;
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
    }


