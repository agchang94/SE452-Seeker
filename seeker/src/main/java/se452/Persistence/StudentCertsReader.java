/*package se452.Persistence;

import java.io.BufferedReader;
import java.util.ArrayList;

import se452.group9.seeker.model.*;


public class StudentCertsReader {
    public static ArrayList<StudentCerts> getStudentCerts (BufferedReader reader) {
        ArrayList<StudentCerts> studentCerts = new ArrayList<StudentCerts>();
        try{
            String line1 = null;
            while((line1 = reader.readLine()) != null) {
                StudentCerts studentCert = new StudentCerts();
                String[] strArray = line1.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                studentCert.setCertificates(strArray[0]);
                studentCerts.add(studentCert);
                System.out.println(studentCert.toString());
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return studentCerts;

    }
    
}
*/