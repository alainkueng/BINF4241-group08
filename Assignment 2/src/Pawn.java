public class Pawn implements Figure {


    Colors color;
    public Pawn(Colors color){
        this.color = color;
    }




    @Override
    public boolean isValidMove(Board gameboard,int x_current, int y_current, int x_move, int y_move, Player player){
        return true;
    }
    public void move(){}
}
