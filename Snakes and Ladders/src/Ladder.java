public class Ladder extends Square{

    int new_position;

    public Ladder(int number, Game game_obj) {
        super(number, game_obj);
        new_position = 99; //implementation pending, calculates where player will be moved
    }

    @Override
    public void enter(Player player) {
        super.enter(player);
        player.move(new_position);
    }
}
