package snakesandladders;
import org.junit.*;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class DiceTest {

    @RepeatedTest( 1000 )
    /**@param randomInt Gets a random number from the dice.dice() method
     * @return randomInt from dice.dice() that is generated there
     * This method tests if the returned number is between 1-6 for a 1000 times to be sure that the generation
     * will always be correct.
     */
    void diceTest() {
        Dice dice = new Dice();
        int randomInt = dice.dice();
        assertTrue((1 <= randomInt) && (randomInt <= 6) , "Dice number is not between 1-6");
    }
}

//Document all test cases using Javadoc.
//The Javadocs can be auto-generated, but you need to complete it with all method information and what the test is covering.
//If you discover a bug on your code when testing:
//1. Fix the code.
//2. Write a natural language description of how you found the bug and how you fixed it.