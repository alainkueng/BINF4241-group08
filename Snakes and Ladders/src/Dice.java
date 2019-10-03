import java.util.Random;
public class Dice {
    private int number;
    private int maxNum;
    private int minNum;
    Random r = new Random();

    Dice(){
        number = 0;
        maxNum = 6;
   }

    public int diced(){
        number = r.nextInt(maxNum);
        return number;
    }
}
