package snakesandladders;

public class Snake extends Square {

    int new_position;

    public Snake(int number, Game game_obj, int squarenumber) {
        super(number, game_obj);
        new_position = squarenumber;
        partner = true;
    }



    @Override
    public void enter(Player player) {
        super.enter(player);
        player.move(new_position-this.position);
}
}
