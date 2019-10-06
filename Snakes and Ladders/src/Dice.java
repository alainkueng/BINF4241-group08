import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private int number;

    public int dice(){
        this.number = ThreadLocalRandom.current().nextInt(1,7);
        return number;
    }

}
