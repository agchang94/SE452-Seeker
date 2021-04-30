package se452.Persistence;

import java.io.BufferedReader;
import java.util.ArrayList;

import se452.group9.seeker.model.*;

public class StudentAcademicReader {
    public static ArrayList<StudentAcademics> getStudentAcademics (BufferedReader reader){
        ArrayList<StudentAcademics> studentAcademics = new ArrayList<StudentAcademics>();
        try{
            String line1 = null;
            while((line1 = reader.readLine()) != null) {
                StudentAcademics studentAcademic = new StudentAcademics();
                String[] strArray = line1.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                studentAcademic.setAccomplishments(strArray[0]);
                studentAcademic.setMajor(strArray[1]);
                studentAcademic.setGpa(Double.parseDouble(strArray[2]));
                studentAcademic.setUniversityName(strArray[3]);
                studentAcademics.add(studentAcademic);
                

            }




    }catch (Exception e) {
			System.out.println(e.getMessage());
		}

        return studentAcademics;

}
}