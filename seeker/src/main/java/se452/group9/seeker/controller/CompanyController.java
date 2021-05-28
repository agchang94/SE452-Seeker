package se452.group9.seeker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se452.group9.seeker.model.Company;
import se452.group9.seeker.model.ICompanyService;

@Controller
@RequestMapping("/company/")
public class CompanyController {

	@Autowired
    private ICompanyService companyService;

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
        return "redirect:listCompanies";
    }    
    
    
}
