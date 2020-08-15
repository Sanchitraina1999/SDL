package ds;
import java.util.*;

public class exampleDictionary {
    public static void main(String[] args) {
        Dictionary d = new Hashtable();
        d.put("123", "Code");
        d.put("456", "Program");
        d.remove("123");
        Enumeration e = d.elements();
        while(e.hasMoreElements()) {
            String r = (String)e.nextElement();
            System.out.println(r+" "+d.get(r));
        }
    }
}