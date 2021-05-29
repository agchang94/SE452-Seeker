package se452.group9.seeker.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "companies")
@Data
public class Company {

    @Id
    @GeneratedValue
    @Min(value = 1, message = "company ID must be greater than 0")
    @Max(value = 99999999, message = "company ID can be at most 8 digits")
    private long companyID;

    @Column(name = "companyName")
    @Size(min = 1, max = 50, message = "limit for company name is 50 chars.")
    private String companyName;

    @Column(name = "companyAddress")
    @Size(max = 250, message = "limit for address is 250 chars.")
    private String address;

    @Column(name = "companyInfo")
    @Size(max = 250, message = "limit for company info is 250 chars.")
    private String companyInfo;

    @OneToMany
    @JoinTable
    (
        name = "company_jobs",
        joinColumns = { @JoinColumn(name="company_ID", referencedColumnName = "companyID") }
    )
    @ToString.Exclude
    private List<Job> jobs;

    public Company() {
        this.jobs = new ArrayList<Job>();
    }
    
    public void addJob(Job newJob) {
        if (newJob == null) throw new IllegalArgumentException();

        jobs.add(newJob);
    }

    public List<Job> getCompanyJobs(){
        return jobs;
    }


}
