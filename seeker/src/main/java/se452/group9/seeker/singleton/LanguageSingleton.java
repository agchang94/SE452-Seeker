package se452.group9.seeker.singleton;

import java.util.HashMap;
import java.util.List;

public class LanguageSingleton {

    private static LanguageSingleton instance = new LanguageSingleton();
    private HashMap<Long, List<String>> lang;

    private LanguageSingleton(){
        lang = new HashMap<>();    
    }

    public static List<String> getLanguages(Long id){
        return instance.get(id);
    }

    public static void createLanguages(Long id, List<String> languages){
        instance.create(id, languages);
    }

    public static void updateLanguages(Long id, List<String> languages){
        instance.update(id, languages);
    }

    public static void deleteCerts(Long id){
        instance.delete(id);
    }

    private List<String> get(Long id){
        if(lang.containsKey(id)) return lang.get(id);
        else return null;
    }

    private void create(Long id, List<String> studentCerts){
        if(!lang.containsKey(id)) lang.put(id, studentCerts);
        else update(id, studentCerts);
    }

    private void update(Long id, List<String> studentCerts){
        lang.replace(id, studentCerts);
    }   

    private void delete(Long id){
        if(lang.containsKey(id)) lang.remove(id);
        return;
    }
    
}
