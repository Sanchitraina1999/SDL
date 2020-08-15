package ds;
import java.util.*;

public class exampleEnum {
    public static void main(String[] args){
        Enumeration e;
        Vector months =  new Vector();
        for(int i=1;i<=12;i++)
            months.add(i);
        System.out.println(months);
        e = months.elements();
        while(e.hasMoreElements()){
            System.out.print(e.nextElement() + " ");
        }
    }
}