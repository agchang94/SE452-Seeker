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
import se452.group9.seeker.repo.JobSkillRepository;
import se452.group9.seeker.repo.StudentAcademicRepository;
import se452.group9.seeker.repo.StudentCertsRepository;
import se452.group9.seeker.repo.StudentRepository;
import se452.group9.seeker.repo.StudentResumeRepository;

@Controller
@RequestMapping("/")
public class EditStudentController {
    
    private final StudentRepository studentRepository;
    private final StudentAcademicRepository studentAcademicRepository;
    private final StudentCertsRepository studentCertsRepository;
    private final StudentResumeRepository studentResumeRepository;
    private final JobSkillRepository jobSkillRepository;

    @Autowired
    public EditStudentController(StudentRepository studentRepository, StudentAcademicRepository studentAcademicRepository, StudentCertsRepository studentCertsRepository, StudentResumeRepository studentResumeRepository, JobSkillRepository jobSkillRepository) {
        this.studentRepository = studentRepository;
        this.studentAcademicRepository = studentAcademicRepository;
        this.studentCertsRepository = studentCertsRepository;
        this.studentResumeRepository = studentResumeRepository;
        this.jobSkillRepository = jobSkillRepository;
    }

 
    public List<Student> getAllStudents()
	{
		
		List<Student> result = (List<Student>) studentRepository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Student>();
		}
	}

    public List<StudentAcademics> getStudentAcademics() {
        List<StudentAcademics> result = (List<StudentAcademics>) studentAcademicRepository.findAll();

        if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<StudentAcademics>();
		}
    }
    
    
    @GetMapping("/students")
    public String getStudents(Model model) {
        System.out.println("getAllEmployees");
		
		List<Student> list = this.getAllStudents();
        model.addAttribute("listStudents", list);
        
		
		return "studentslist";
    }

    @GetMapping("/studentacademics")
    public String getAllStudentAcademics(Model model) {
        List<StudentAcademics> list = this.getStudentAcademics();
        System.out.println(list.size());
        model.addAttribute("listStudentAcademics", list);
        return "studentacademiclist";
    }
    
    /*public String getStudents(Model model) {
        model.addAttribute("listStudents", studentRepository.findAll());
        return "studentslist";
    }*/


    
    @GetMapping("/editstudent")
    public String getEditPage() {
        return "editstudent";
    }

    public StudentAcademics getStudentAcademicById(long id)
	{
		
		Optional<StudentAcademics> studentAcademic = studentAcademicRepository.findById(id);
		StudentAcademics academic = null;
		if(studentAcademic.isPresent()) {
			academic=studentAcademic.get();
		} else {
			System.out.println("No employee record exist for given id");
		}
        return academic;
	}
    
    @RequestMapping(path = {"/editacademic/{id}"})
	public String editAcademicById(@PathVariable(value = "id") long id, Model model) 
							
	{
			StudentAcademics studentAcademic = this.getStudentAcademicById(id);
			Student student = studentAcademic.getStudent();
            //List<Student> listStudents = studentRepository.findAll();
            //model.addAttribute("student", student);
            model.addAttribute("studentAcademic", studentAcademic);
            
		return "update_academic";
	}

    public StudentAcademics createOrUpdateAcademic(StudentAcademics studentAcademics) 
	{
		// Create new entry 
		if(studentAcademics.getId()  == null) 
		{
			studentAcademics = studentAcademicRepository.save(studentAcademics);
			
			return studentAcademics;
		} 
		else 
		{
			// update existing entry 
			Optional<StudentAcademics> temp  = studentAcademicRepository.findById(studentAcademics.getId());
			
			if(temp.isPresent()) 
			{
			StudentAcademics newAcademics = temp.get();
			newAcademics.setAccomplishments(studentAcademics.getAccomplishments());
            newAcademics.setGpa(studentAcademics.getGpa());
            newAcademics.setMajor(studentAcademics.getMajor());
            newAcademics.setUniversityName(studentAcademics.getUniversityName());
            newAcademics.setStudent(studentAcademics.getStudent());

		    newAcademics = studentAcademicRepository.save(newAcademics);
				
				return newAcademics;
			} else {
				studentAcademics = studentAcademicRepository.save(studentAcademics);
				
				return studentAcademics;
			}
		}
	} 
    
    
    
    
    @RequestMapping(path = "/successAcademics", method = RequestMethod.POST)
	public String createOrUpdateStudentAcademics(StudentAcademics studentAcademics) 
	{
		System.out.println("createOrUpdateEmployee ");
		
		this.createOrUpdateAcademic(studentAcademics);
		
		return "editstudent";
	}


    
    
    public Student getStudentById(long id)
	{
		System.out.println("getEmployeeById");
		Optional<Student> employee = studentRepository.findById(id);
		Student student = null;
		if(employee.isPresent()) {
			student=employee.get();
		} else {
			System.out.println("No employee record exist for given id");
		}
        return student;
	}
    
    
    
    @RequestMapping(path = {"/edit/{id}"})
	public String editEmployeeById(@PathVariable(value = "id") long id, Model model) 
							
	{
		
		System.out.println("editEmployeeById" + id);
			Student student = this.getStudentById(id);
			model.addAttribute("student", student);
		
		return "update_student";
	}

    public Student createOrUpdateEmployee(Student student) 
	{
		System.out.println("createOrUpdateEmployee");
		// Create new entry 
		if(student.getId()  == null) 
		{
			student = studentRepository.save(student);
			
			return student;
		} 
		else 
		{
			// update existing entry 
			Optional<Student> employee = studentRepository.findById(student.getId());
			
			if(employee.isPresent()) 
			{
			Student student3 = employee.get();
			student3.setFname(student.getFname());
            student3.setLname(student.getLname());
            student3.setEmail(student.getEmail());
            student3.setPhone(student.getPhone());
            student3.setDob(student.getDob());
            student3.setPassword(student.getPassword());

				student3 = studentRepository.save(student3);
				
				return student3;
			} else {
				student= studentRepository.save(student);
				
				return student;
			}
		}
	} 
    
    
    
    
    @RequestMapping(path = "/success", method = RequestMethod.POST)
	public String createOrUpdateEmployee2(Student employee) 
	{
		System.out.println("createOrUpdateEmployee ");
		
		this.createOrUpdateEmployee(employee);
		
		return "editstudent";
	}


    
    
    
    

}
