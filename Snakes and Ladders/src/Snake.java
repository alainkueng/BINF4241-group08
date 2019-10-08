public class Snake extends Square {

    int new_position;

    public Snake(int number, Game game_obj, int squarenumber) {
        super(number, game_obj);
        new_position = squarenumber;
    }

    public void settrue(Snake snake){
        snake.partner = true;
    }
    @Override
    public void enter(Player player) {
        super.enter(player);
        player.move(this.position-new_position);
}
}
