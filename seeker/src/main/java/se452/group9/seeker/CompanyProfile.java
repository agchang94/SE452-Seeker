package se452.group9.seeker;

import java.util.List;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
public class CompanyProfile implements ICompanyDesciber {
    @Size(min = 1, max = 50, message = "limit for company title is 50 chars.")
    private String companyTitle;

    @Size(max = 250, message = "limit for company description is 250 chars.")
    private String companyInfo;

    @ToString.Exclude
    private List<Object> companyJobs;

    public CompanyProfile (String companyTitle) {
        this.companyTitle = companyTitle;
    }

    @Override
    public void editInfo(String info) {
        companyInfo = info;
    }

    @Override
    public String getInfo() {
        return companyInfo;
    }
    


}
