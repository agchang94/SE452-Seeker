package se452.group9.seeker.controller;

import java.util.List;
import java.util.Optional;

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
import se452.group9.seeker.repo.JobTypeRepository;
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
    public RecruiterController (JobRepository jobRepo, CompanyRepository company, RecruiterRepository recruiterRepo, ICompanyService companyService, JobTypeRepository jobTypeRepo) {
        searchService = new SearchService(jobRepo, company, jobTypeRepo);
        this.jobRepo = jobRepo;
        this.companyService = companyService;
        this.recruiterRepo = recruiterRepo;
        this.recruiterIDTracker = 5;
    }

     /* ---------------- Start of recruiter handling recruiter methods --------------- */

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

    
     /* ---------------- Start of recruiter handling companies --------------- */


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

    @GetMapping("edit/{companyID}")
    public String showUpdateCompany(@PathVariable("companyID") Long companyID, Model model) {
      Company company = companyService.findBycompanyID(companyID);

      model.addAttribute("company", company);
      return "update_company";
    }
  
    @PostMapping("edit")
    public String updateCompany(@Valid Company company, BindingResult result) {
        /*if (result.hasErrors()) {
            return "edit-student";
        }*/
    
        companyService.updateCompany(company);

      return "redirect:listCompanies";
    }


    

    /* ---------------- Start of recruiter handling jobs --------------- */

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
