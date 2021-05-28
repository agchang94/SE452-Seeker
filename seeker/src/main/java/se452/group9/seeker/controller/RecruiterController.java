package se452.group9.seeker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.group9.seeker.model.Recruiter;
import se452.group9.seeker.repo.RecruiterRepository;

@Controller
@RequestMapping("/recruiter/")
public class RecruiterController {
    
    private final RecruiterRepository recruiterRepository;
    private long recruiterIDTracker;

    @Autowired
    public RecruiterController (RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
        this.recruiterIDTracker = 5;
    }

	@GetMapping("addRecruiter")
    public String addRecruiterForm(Recruiter recruiter) {
        return "addRecruiter";
    }

	@GetMapping("listRecruiters")
	public String listCompanies(Model model) {
        model.addAttribute("recruiters", recruiterRepository.findAll());
        return "listRecruiters";
	}

    @GetMapping("recruiterProfile")
    public String redirectToRecruiterProfile() {
        return "recruiterProfile";
    }

	@PostMapping("addRecruiter")
    public String addRecruiter(@Valid Recruiter recruiter, BindingResult result, Model model) {
        /*if (result.hasErrors()) {
            return "addRecruiter";
        }*/

        recruiter.setRecruiterID(recruiterIDTracker);
        recruiterIDTracker = recruiterIDTracker + 1;

        recruiterRepository.save(recruiter);
        return "redirect:recruiterProfile";
    }


    



}
