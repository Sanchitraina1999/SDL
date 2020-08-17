import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;

public class Agent extends MainMenu{
    private int agentId;
    private int agentPin;
    HashMap<Integer, Integer> agentUsers = new HashMap<Integer, Integer>(); // <AgentUsername, AgentPassword>

    Agent(){
        agentUsers.clear();
        agentUsers.put(1,1);
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }
    public int getAgentId() {
        return agentId;
    }

    public void setAgentPin(int agentPin) {
        this.agentPin = agentPin;
    }

    public int getAgentPin() {
        return agentPin;
    }

    public void AgentLogin(){
        boolean validLogin = false;
        try{
            System.out.println(centerString(70, "Welcome to Agent Portal"));
            System.out.print("Enter your Agent Login ID: ");
            setAgentId(input.nextInt());
            System.out.print("Enter your Secret PIN: ");
            setAgentPin(input.nextInt());
        }
        catch(InputMismatchException e){
            System.out.println("\n" + "Invalid Character(s). Please retry \n");
        }

        for (Entry<Integer, Integer> entry : agentUsers.entrySet()) {
            if (entry.getKey() == getAgentId() && entry.getValue() == getAgentPin()) {
                validLogin=true;
                break;
            }
        }
        if(validLogin){
            System.out.println("Login Successful.");
        }
        else{
            System.out.println("\n" + "Invalid Login Details.");
        }
    }
}