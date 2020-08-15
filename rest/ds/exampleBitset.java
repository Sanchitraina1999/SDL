package ds;
import java.util.BitSet;

public class exampleBitset {
    public static void main(final String[] args) {
        BitSet b1 = new BitSet(10);
        BitSet b2 = new BitSet(10);
        for(int i=0;i<11;i++) {
        	if(i%2==0)
        		b1.set(i);
        	if(i%3==0)
        		b2.set(i);
        }
        System.out.println("b1");
        System.out.println(b1);
        System.out.println("b2");
        System.out.println(b2);

        b1.and(b2);
        System.out.println("b1 AND b2");
        System.out.println(b1);

        b1.or(b2);
        System.out.println("b1 OR b2");
        System.out.println(b1);

        b1.xor(b2);
        System.out.println("b1 XOR b2");
        System.out.println(b1);
    }
}