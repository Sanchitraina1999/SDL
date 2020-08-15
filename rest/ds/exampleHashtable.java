package ds;
import java.util.*;

public class exampleHashtable {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        Enumeration rollno;
        ht.put(35,"Parag");
        ht.put(42,"Mayur");
        ht.put(51,"Manish");
        ht.put(60,"Sumit");
        rollno = ht.keys();
        while(rollno.hasMoreElements()){
            int r=(int)rollno.nextElement();
            System.out.println(r+" "+ht.get(r));
        }
    }
}