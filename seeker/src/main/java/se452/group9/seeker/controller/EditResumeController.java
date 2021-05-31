package se452.group9.seeker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import se452.group9.seeker.model.Student;
import se452.group9.seeker.model.StudentAcademics;
import se452.group9.seeker.model.StudentResume;
import se452.group9.seeker.repo.JobSkillRepository;
import se452.group9.seeker.repo.StudentAcademicRepository;
import se452.group9.seeker.repo.StudentCertsRepository;
import se452.group9.seeker.repo.StudentRepository;
import se452.group9.seeker.repo.StudentResumeRepository;

@Controller
@RequestMapping("/")
public class EditResumeController {

    private final StudentRepository studentRepository;
    private final StudentAcademicRepository studentAcademicRepository;
    private final StudentCertsRepository studentCertsRepository;
    private final StudentResumeRepository studentResumeRepository;
    private final JobSkillRepository jobSkillRepository;

    @Autowired
    public EditResumeController(StudentRepository studentRepository, StudentAcademicRepository studentAcademicRepository, StudentCertsRepository studentCertsRepository, StudentResumeRepository studentResumeRepository, JobSkillRepository jobSkillRepository) {
        this.studentRepository = studentRepository;
        this.studentAcademicRepository = studentAcademicRepository;
        this.studentCertsRepository = studentCertsRepository;
        this.studentResumeRepository = studentResumeRepository;
        this.jobSkillRepository = jobSkillRepository;
    }

    public List<StudentResume> getAllResumes()
	{
		
		List<StudentResume> result = (List<StudentResume>) studentResumeRepository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<StudentResume>();
		}
	}

    public List<StudentResume> getResumes() {
        List<StudentResume> result = (List<StudentResume>) studentResumeRepository.findAll();

        if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<StudentResume>();
		}
    }

    @GetMapping("/studentresumes")
    public String getResumes(Model model) {
		
		List<StudentResume> list = this.getResumes();
        model.addAttribute("listResumes", list);
        
		
		return "studentresumelist";
    }

    public StudentResume getStudentResumeById(long id)
	{
		
		Optional<StudentResume> temp = studentResumeRepository.findById(id);
		StudentResume studentResume = null;
		if(temp.isPresent()) {
			studentResume=temp.get();
		} else {
			System.out.println("No employee record exist for given id");
		}
        return studentResume;
	}


     @RequestMapping(path = {"/editResume{id}"})
	public String editResumeById(@PathVariable(value = "id") long id, Model model) 
							
	{
			StudentResume studentResume = this.getStudentResumeById(id);
			Student student = studentResume.getStudent();
            //List<Student> listStudents = studentRepository.findAll();
            //model.addAttribute("student", student);
            model.addAttribute("studentResume", studentResume);
            
		return "update_resume";
	}

	@RequestMapping(path = {"/deleteResume{id}"})
	public String deleteResumeById(@PathVariable(value = "id") long id, Model model) 
							
	{

			studentResumeRepository.deleteById(id);
            return "editstudent";
	}

    public StudentResume createOrUpdateResume(StudentResume studentResume) 
	{
		// Create new entry 
		if(studentResume.getId()  == null) 
		{
			studentResume = studentResumeRepository.save(studentResume);
			
			return studentResume;
		} 
		else 
		{
			// update existing entry 
			Optional<StudentResume> temp  = studentResumeRepository.findById(studentResume.getId());
			
			if(temp.isPresent()) 
			{
			StudentResume newResume= temp.get();
			newResume.setIsCurrentJob(studentResume.getIsCurrentJob());
			newResume.setStartDate(studentResume.getStartDate());
			newResume.setEndDate(studentResume.getEndDate());			
			newResume.setCompany(studentResume.getCompany());
			newResume.setTitle(studentResume.getTitle());
			newResume.setCity(studentResume.getCity());
			newResume.setState(studentResume.getState());
			newResume.setCountry(studentResume.getCountry());
			newResume.setDescription(studentResume.getDescription());
			newResume.setStudent(studentResume.getStudent());

		    newResume = studentResumeRepository.save(newResume);
				
				return newResume;
			} else {
				studentResume = studentResumeRepository.save(studentResume);
				
				return studentResume;
			}
		}
	} 
    
    
    
    
    @RequestMapping(path = "/successResume", method = RequestMethod.POST)
	public String createOrUpdateStudentResume(StudentResume studentResume) 
	{
		
		this.createOrUpdateResume(studentResume);
		
		return "editstudent";
	}



}
