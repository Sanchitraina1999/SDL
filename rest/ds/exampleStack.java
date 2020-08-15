package ds;
import java.util.Stack;

public class exampleStack {
    public static void main(String[] args){
        Stack s = new Stack();
        for(int i = 0; i<10;i++){
            s.push(i);
        }
        System.out.println(s.peek());
        s.pop();
        System.out.println(s);
        int i=2;
        if(s.search(i)==-1){
            System.out.println("Not Found");
        }
        else{
            System.out.println("Found "+ i);
        }
    }
}