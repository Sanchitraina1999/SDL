package atm;
import java.io.IOException;

 class start extends options{
    public static void main(String[] args) throws IOException{
        options menu = new options();
        menu.getLoginDetails();
    }
}
