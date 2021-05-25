package se452.group9.seeker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.group9.seeker.repo.StudentAcademicRepository;
import se452.group9.seeker.repo.StudentCertsRepository;
import se452.group9.seeker.repo.StudentResumeRepository;

@Controller
@RequestMapping("/profile/")
public class StudentProfileController {
    
    private final StudentResumeRepository resumeRepo;
    private final StudentAcademicRepository academicsRepo;
    private final StudentCertsRepository certsRepo;

    @Autowired
    public StudentProfileController(StudentResumeRepository resumeRepo,
                                    StudentAcademicRepository academicsRepo,
                                    StudentCertsRepository certsRepo){
        this.resumeRepo = resumeRepo;
        this.academicsRepo = academicsRepo;
        this.certsRepo = certsRepo;
    }
}
