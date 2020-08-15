package ds;
import java.util.*;

public class exampleProperties {
    public static void main(String[] args) {
        Properties props = new Properties();
        Enumeration rollno;
        props.setProperty("rollno1","Parag");
        props.setProperty("rollno2","Mayur");
        props.setProperty("rollno3","Manish");
        props.setProperty("rollno4","Sumit");
        rollno = props.keys();
        while(rollno.hasMoreElements()){
            String r=(String)rollno.nextElement();
            System.out.println(r+" "+props.getProperty(r));
        }
    }
}