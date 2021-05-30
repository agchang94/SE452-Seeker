package se452.group9.seeker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.group9.seeker.model.Company;
import se452.group9.seeker.model.ICompanyService;
import se452.group9.seeker.model.Job;
import se452.group9.seeker.model.Recruiter;
import se452.group9.seeker.repo.CompanyRepository;
import se452.group9.seeker.repo.JobRepository;
import se452.group9.seeker.repo.RecruiterRepository;
import se452.group9.seeker.service.SearchService;

@Controller
@RequestMapping("/recruiter/")
public class RecruiterController {

    private final SearchService searchService;
    private final ICompanyService companyService;
    private final RecruiterRepository recruiterRepo;
    private final JobRepository jobRepo;
    private long recruiterIDTracker;

    @Autowired
    public RecruiterController (JobRepository jobRepo, CompanyRepository company, RecruiterRepository recruiterRepo, ICompanyService companyService) {
        searchService = new SearchService(jobRepo, company);
        this.jobRepo = jobRepo;
        this.companyService = companyService;
        this.recruiterRepo = recruiterRepo;
        this.recruiterIDTracker = 5;
    }

	@GetMapping("addRecruiter")
    public String addRecruiterForm(Recruiter recruiter) {
        return "addRecruiter";
    }

	@GetMapping("listRecruiters")
	public String listRecruiters(Model model) {
        model.addAttribute("recruiters", recruiterRepo.findAll());
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

        recruiterRepo.save(recruiter);
        return "redirect:recruiterProfile";
    }

    
    @GetMapping("addCompany")
    public String addCompanyForm(Company company) {
        return "addCompany";
    }

	@GetMapping("listCompanies")
	public String listCompanies(Model model) {
        model.addAttribute("companies", companyService.findAll());
        return "listCompanies";
	}

	@PostMapping("addCompany")
    public String addCompany(@Valid Company company, BindingResult result, Model model) {
        /*if (result.hasErrors()) {
            return "addCompany";
        }*/

        companyService.add(company);
        return "redirect:addRecruiter";
    }    


    @GetMapping("updateCompany")
    public String updateCompanyForm(Company company) {
        return "updateCompany";
    }

    @PostMapping("updateCompany")
    public String updateCompany(@Valid Company company, BindingResult result, Model model) {
        /*if (result.hasErrors()) {
            return "updateCompany";
        }*/

        //companyService.add(company);
        return "redirect:listCompanies";
    } 

    @GetMapping("manageJobs")
    public String listManageJobs(Model model){
        //Iterable<Job> jobs = jobRepository.findAll();
        Iterable<Job> jobs = searchService.search(null);
        model.addAttribute("manageJobs", jobs);
        return "manageJobs";
    }


    @GetMapping("/deleteJob/{id}")
    public String delete(@PathVariable("id") Long jobId, Model model) {
        
        //delete from companyJobs, so delete from company that owns it
        List<Company> companyList = companyService.findAll();

        for (Company x : companyList) {
            List<Job> xJobs = x.getJobs();
            xJobs.remove(jobRepo.getOne(jobId));
        }

        // can safely delete from job table
        jobRepo.deleteById(jobId);

        return "redirect:/recruiter/manageJobs";
    } 


}
