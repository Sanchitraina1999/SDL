package ds;
import java.util.Vector;

public class exampleVector {
    public static void main(String[] args) {
        Vector v = new Vector();
        for(int i = 1; i <=20;i++){
            v.add(i);
            System.out.println("v.size() - "+v.size());
            System.out.println("v.capacity() - "+v.capacity());
            v.remove(0);
        }
        System.out.println(v);
    }
}