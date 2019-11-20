import devices.*;

public class User {
    public static void main(String args[]){
        Smartphone smartphone = new Smartphone();
        smartphone.addDevice(new InitializedOven());
        // Add other devices
    }

}
