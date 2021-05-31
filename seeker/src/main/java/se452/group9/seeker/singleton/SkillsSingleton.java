package se452.group9.seeker.singleton;

import java.util.HashMap;
import java.util.List;

public class SkillsSingleton {

    private static SkillsSingleton instance = new SkillsSingleton();
    private HashMap<Long, List<String>> skills;

    private SkillsSingleton(){
        skills = new HashMap<>();    
    }

    public static List<String> getSkills(Long id){
        return instance.get(id);
    }

    public static void createSkills(Long id, List<String> studentCerts){
        instance.create(id, studentCerts);
    }

    public static void updateSkills(Long id, List<String> studentCerts){
        instance.update(id, studentCerts);
    }

    public static void deleteSkills(Long id){
        instance.delete(id);
    }

    private List<String> get(Long id){
        if(skills.containsKey(id)) return skills.get(id);
        else return null;
    }

    private void create(Long id, List<String> studentCerts){
        if(!skills.containsKey(id)) skills.put(id, studentCerts);
        else update(id, studentCerts);
    }

    private void update(Long id, List<String> studentCerts){
        skills.replace(id, studentCerts);
    }   

    private void delete(Long id){
        if(skills.containsKey(id)) skills.remove(id);
        return;
    }
    
}
