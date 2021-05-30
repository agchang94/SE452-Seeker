package se452.Persistence;

import java.io.BufferedReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import antlr.collections.List;
import se452.group9.seeker.model.*;

public class StudentReader {
    public static ArrayList<Student> getStudents (BufferedReader reader){
        
        ArrayList<Student> students = new ArrayList<Student>();
        try{
            String line1 = null;
            while((line1 = reader.readLine()) != null) {
                Student student = new Student();
                String [] fields = line1.split(" ");
                student.setFname(fields[0]);
                student.setLname(fields[1]);
                student.setEmail(fields[2]);
                /*student.setState(fields[3]);
                student.setCity(fields[4]);
                student.setAddress(fields[5].concat(" ").concat(fields[6]).concat(" ").concat(fields[7]));
                student.setZip(Integer.parseInt(fields[8])); */
                student.setPhone(fields[9]);
                student.setDob((fields[10]));
                student.setPassword(fields[11]);
                students.add(student);
            }




    }catch (Exception e) {
			System.out.println(e.getMessage());
		}

        return students;
    
}

}
