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
import se452.group9.seeker.repo.JobSkillRepository;
import se452.group9.seeker.repo.StudentAcademicRepository;
import se452.group9.seeker.repo.StudentCertsRepository;
import se452.group9.seeker.repo.StudentRepository;
import se452.group9.seeker.repo.StudentResumeRepository;

@Controller
@RequestMapping("/")
public class EditStudentController {
    @Autowired
    private StudentRepository studentRepository;
    private StudentAcademicRepository studentAcademicRepository;
    private StudentCertsRepository studentCertsRepository;
    private StudentResumeRepository studentResumeRepository;
    private JobSkillRepository jobSkillRepository;


 
    public List<Student> getAllEmployees()
	{
		System.out.println("getAllEmployees");
		List<Student> result = (List<Student>) studentRepository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Student>();
		}
	}
    
    
    @GetMapping("/students")
    public String getStudents(Model model) {
        System.out.println("getAllEmployees");
		
		List<Student> list = this.getAllEmployees();

		model.addAttribute("listStudents", list);
		
		return "studentslist";
    }
    
    /*public String getStudents(Model model) {
        model.addAttribute("listStudents", studentRepository.findAll());
        return "studentslist";
    }*/

    
    @GetMapping("/editstudent")
    public String getEditPage() {
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
            /*student3.setState(student.getState());
            student3.setCity(student.getCity());
            student3.setAddress(student.getAddress());
            student3.setZip(student.getZip()); */
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
		
		return "index";
	}


    
    
    
    /*@RequestMapping(path = { "/edit", "/updatestudent/{id}"})
    public String editEmployee(Model model, @PathVariable("id") Optional<Long> id) {
        
        if(id.isPresent()) {
            Optional<Student> student = studentRepository.findById(id.get());
            Student student2 = student.get();
            model.addAttribute("student2", student2);
    }else {
        System.out.println("Student does not exist");
    }
       
        return "update_student";
    }

    @PostMapping("success")
    public String processRegistration(Student student){
        Optional<Student> student2 = studentRepository.findById(student.getId());
        System.out.println(student.getId());
        if(student.getId()==1)
        {
            Student student3  = student2.get();
            student3.setFname(student.getFname());
            student3.setLname(student.getLname());
            student3.setEmail(student.getPassword());
            student3.setState(student.getState());
            student3.setCity(student.getCity());
            student3.setAddress(student.getAddress());
            student3.setZip(student.getZip());
            student3.setPhone(student.getPhone());
            student3.setDob(student.getDob());
            student3.setPassword(student.getPassword());
            student3 = studentRepository.save(student3);
        } else{
            //System.out.println("In here");
            student = studentRepository.save(student);
        }
        
        return "index";
    } */

   

}
