package se452.group9.seeker;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "locations")
public class Location {

    @Size(min = 1, max = 8, message = "city ID must be between 1 and 8 characters")
    @Id
    private long cityID;
    
    private String cityName;

    private String stateName;

    private String companyName;

    private long companyID;

}
