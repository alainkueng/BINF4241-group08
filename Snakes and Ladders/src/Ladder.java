import java.util.concurrent.ThreadLocalRandom;

public class Ladder extends Square {

    int new_position;

    public Ladder(int number, Game game_obj, int squarepos) {
        super(number, game_obj);
        partner = true;
        new_position = squarepos;}


    @Override
    public void enter(Player player) {
        super.enter(player);
        player.move(new_position-this.position);

    }

    }
