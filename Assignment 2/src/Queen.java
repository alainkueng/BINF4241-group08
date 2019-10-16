public class Queen implements Figure {

    Colors color;
    public Queen(Colors color){
        this.color = color;
    }

    @Override
    public boolean isValidMove(Board gameboard, int xCurrent, int yCurrent, int xMove, int yMove){
        return true;
    }    public void move(){}
}
