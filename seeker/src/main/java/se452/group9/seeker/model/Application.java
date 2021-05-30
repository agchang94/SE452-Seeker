package se452.group9.seeker.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "applications")
public class Application {

    @Size(min = 1, max = 8, message = "application ID must be between 1 and 8 characters")
    @Id
    private long applicationID;
    
    //private long studentID;

    private long companyID;

    private String applicationDate;

    private String applicationStatus;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "st_id", nullable = false)
    private Student student;
}
