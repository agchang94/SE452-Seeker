package se452.group9.seeker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// @EqualsAndHashCode
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    @Column(name="description", length=2000)
    private String desc;
    private String requirements;
    private String datePosted;
    private Boolean isActive;

   
    // private int companyID;
   

    //Constructor
    // public Job(int id, String title, String description, Boolean isActive){
    //     this.id = id;
    //     this.title = title;
    //     this.description = description;
    //     this.createdDate = createdDate;
    //     this.isActive = isActive;
    // }
    //Getters and Setters

    // public Long getId(){
    //     return id;
    // }

    // public void setId(Long id){
    //     this.id = id;
    // }

    // public String getTitle(){
    //     return title;
    // }

    // public void setTitle(String title){
    //     this.title = title;
    // }

    // public String getDescription(){
    //     return desc;
    // }

    // public void setDescription(String description){
    //     this.desc = description;
    // }
    // public String getDatePosted(){
    //     return datePosted;
    // }

    // public void setDatePosted(String datePosted){
    //     this.datePosted = datePosted;
    // }

    // public Boolean getIsActive(){
    //     return isActive;
    // }

    // public void setIsActive(Boolean isActive){
    //     this.isActive = isActive;
    // }

    // public String getRequirements(){
    //     return requirements;
    // }

    // public void setRequirements(String req){
    //     this.requirements = req;
    // }

}