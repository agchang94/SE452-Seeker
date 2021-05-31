package se452.group9.seeker.controller;

import se452.group9.seeker.model.JobSkill;
import se452.group9.seeker.model.Student;
import se452.group9.seeker.model.StudentAcademics;
import se452.group9.seeker.model.StudentCerts;
import se452.group9.seeker.model.StudentResume;
import se452.group9.seeker.repo.JobSkillRepository;
import se452.group9.seeker.repo.StudentAcademicRepository;
import se452.group9.seeker.repo.StudentAttributesRepository;
import se452.group9.seeker.repo.StudentCertsRepository;
import se452.group9.seeker.repo.StudentRepository;
import se452.group9.seeker.repo.StudentResumeRepository;
import se452.group9.seeker.singleton.CertsSingleton;
import se452.group9.seeker.singleton.LanguageSingleton;
import se452.group9.seeker.singleton.SkillsSingleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class StudentProfileController {
    private final StudentRepository studentRepository;
    private final StudentAcademicRepository studentAcademicRepository;
    private final StudentCertsRepository studentCertsRepository;
    private final StudentResumeRepository studentResumeRepository;
    private final JobSkillRepository jobSkillRepository;
    private final StudentAttributesRepository attributesRepo;

    @Autowired
	public StudentProfileController(StudentRepository studentRepository, 
                                    StudentAcademicRepository studentAcademicRepository, 
                                    StudentCertsRepository studentCertsRepository, 
                                    StudentResumeRepository studentResumeRepository,
                                    JobSkillRepository jobSkillRepository, 
                                    StudentAttributesRepository attributesRepo){
        this.studentRepository=studentRepository;
        this.studentAcademicRepository=studentAcademicRepository;
        this.studentCertsRepository=studentCertsRepository;
        this.studentResumeRepository=studentResumeRepository;
        this.jobSkillRepository=jobSkillRepository;
        this.attributesRepo = attributesRepo;
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
        List<Student> listStudents = studentRepository.findAll();
        model.addAttribute("listStudents", listStudents);
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

    @GetMapping("student/{id}")
    public String profile(@PathVariable("id") long id, Model model){
        Student st = studentRepository.getOne(id);
        Optional<StudentCerts> temp = studentCertsRepository.findById(id);
        StudentCerts cert = new StudentCerts();
        cert.setCerts(Arrays.asList("blank"));
        if(temp.isPresent()){
            cert=temp.get();
            cert.setId(id);
        }

           
        
        Iterable<String> c = CertsSingleton.getCerts(id);
        Iterable<String> l = LanguageSingleton.getLanguages(id);
        Iterable<String> s = SkillsSingleton.getSkills(id);

        model.addAttribute("cert", cert);
        model.addAttribute("student", st);
        model.addAttribute("academics", st.getStudentAcademics());
        model.addAttribute("resume", st.getStudentResumes());
        model.addAttribute("certs", c);
        model.addAttribute("language", l);
        model.addAttribute("skills", s);
        model.addAttribute("apps", st.getStudentApplications());
        return "studentProfile";        
    }


    @RequestMapping(path = {"/editCerts/{id}"})
	public String editCertsById(@PathVariable(value = "id") long id, Model model) 
							
	{
            Optional<StudentCerts> temp = studentCertsRepository.findById(id);
            StudentCerts cert = temp.get();
            //List<Student> listStudents = studentRepository.findAll();
            //model.addAttribute("student", student);
            model.addAttribute("studentcerts", cert);
            
		return "update_certs";
	}

    
    @RequestMapping(path = "/successCerts", method = RequestMethod.POST)
	public String createOrUpdateStudentCerts(StudentCerts cert) 
	{

		this.createOrUpdateCert(cert);
		
		return "index";
	}

    public StudentCerts createOrUpdateCert(StudentCerts studentCert) {
        if(studentCert.getId()  == null) 
		{
			studentCert = studentCertsRepository.save(studentCert);
			
			return studentCert;
		}
        else 
		{
			// update existing entry 
			Optional<StudentCerts> temp  = studentCertsRepository.findById(studentCert.getId());
			
			if(temp.isPresent()) 
			{
            StudentCerts newCert = temp.get();
            newCert.setId(studentCert.getId());
            newCert.setCerts(studentCert.getCerts());
            newCert=studentCertsRepository.save(newCert);
            return newCert;
    } else{
        studentCert = studentCertsRepository.save(studentCert);
        return studentCert;
    }

}

    }

    @RequestMapping(path = {"/deletecert{id}"})
	public String deleteCertById(@PathVariable(value = "id") long id, Model model) 
							
	{

			studentCertsRepository.deleteById(id);
            return "index";
	}
}
