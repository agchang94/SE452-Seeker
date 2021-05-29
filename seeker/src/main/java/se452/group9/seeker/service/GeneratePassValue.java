package se452.group9.seeker.service;

import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassValue {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ArrayList <String> pwordList = new ArrayList<String>();
        pwordList.add("pword1"); 
        pwordList.add("pword2");
        pwordList.add("pword3");
        pwordList.add("pword4");

        for (String pword:pwordList) {
            System.out.println(pword);
            String encodedPassword = encoder.encode(pword);
            System.out.println(encodedPassword);
        }
        


    }
    
}
