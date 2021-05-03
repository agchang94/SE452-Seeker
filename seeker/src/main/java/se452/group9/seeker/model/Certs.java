package se452.group9.seeker.model;

import lombok.Data;

@Data 
public class Certs {
    String type;
    String name;
    int yearObtained;

    public Certs(String type, String name, int yearObtained) {
        this.type=type;
        this.name=name;
        this.yearObtained = yearObtained;
    }
    
}

