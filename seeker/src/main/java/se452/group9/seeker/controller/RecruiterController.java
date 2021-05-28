package se452.group9.seeker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.group9.seeker.model.Recruiter;
import se452.group9.seeker.repo.RecruiterRepository;

@Controller
@RequestMapping("/recruiter/")
public class RecruiterController {
    
    private final RecruiterRepository recruiterRepository;

    @Autowired
    public RecruiterController (RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
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

	@PostMapping("addRecruiter")
    public String addRecruiter(@Valid Recruiter recruiter, BindingResult result, Model model) {
        /*if (result.hasErrors()) {
            return "addRecruiter";
        }*/

        recruiterRepository.save(recruiter);
        return "redirect:listRecruiters";
    }

    @GetMapping("/delete/{recruiterID}")
    public String delete(@PathVariable("recruiterID") int recruiterID, Model model) {
        recruiterRepository.deleteByrecruiterID(recruiterID);
        return "redirect:listRecruiters";
    }


}
