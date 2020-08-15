import java.util.*;

public class collect {
    public static void main(String[] args){
        System.out.println("ArrayList"); 
        List l = new ArrayList();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        System.out.println(l);

        System.out.println("Linked List");
        List l1 = new LinkedList();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        System.out.println(l1);

        System.out.println("Hash Set");
        List h = new LinkedList();
        h.add("Rehan");
        h.add("Sanchit");
        h.add("Kaira");
        h.add("Sara");
        System.out.println(h);

        System.out.println("Linked Hash List");
        List h1 = new LinkedList();
        h1.add("Rehan");
        h1.add("Sanchit");
        h1.add("Sara");
        System.out.println(h1);

        System.out.println("TreeSet");
        Set ts = new TreeSet();
        ts.add("Rehan");
        ts.add("Sanchit");
        ts.add("Kaira");
        ts.add("Sara");
        System.out.println(ts);
    }
}