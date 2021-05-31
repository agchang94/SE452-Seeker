package se452.group9.seeker.singleton;

import java.util.HashMap;
import java.util.List;

public class CertsSingleton {

    private static CertsSingleton instance = new CertsSingleton();
    private HashMap<Long, List<String>> certs;

    private CertsSingleton(){
        certs = new HashMap<>();    
    }

    public static List<String> getCerts(Long id){
        return instance.get(id);
    }

    public static void createCerts(Long id, List<String> studentCerts){
        instance.create(id, studentCerts);
    }

    public static void updateCerts(Long id, List<String> studentCerts){
        instance.update(id, studentCerts);
    }

    public static void deleteCerts(Long id){
        instance.delete(id);
    }

    private List<String> get(Long id){
        if(certs.containsKey(id)) return certs.get(id);
        else return null;
    }

    private void create(Long id, List<String> studentCerts){
        if(!certs.containsKey(id)) certs.put(id, studentCerts);
        else update(id, studentCerts);
    }

    private void update(Long id, List<String> studentCerts){
        certs.replace(id, studentCerts);
    }   

    private void delete(Long id){
        if(certs.containsKey(id)) certs.remove(id);
        return;
    }
    
}
