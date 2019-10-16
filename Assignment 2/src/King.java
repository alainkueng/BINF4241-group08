public class King implements Figure {

    Colors color;
    public King(Colors color){
        this.color = color;
    }


    @Override
    public boolean isValidMove(Board gameboard, int xCurrent, int yCurrent, int xMove, int yMove, Player player){
        return true;
    }
    public void move(){}
}
