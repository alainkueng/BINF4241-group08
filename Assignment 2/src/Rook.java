public class Rook implements Figure {


    Colors color;
    public Rook(Colors color){
        this.color = color;
    }

    //check documentation of Interface
    public boolean isValidMove(int xCurrent, int yCurrent, int xMove, int yMove){
        boolean valid = false;
        //move up or down
        if(xCurrent == xMove && yCurrent > yMove || xCurrent == xMove && yCurrent < yMove){
            valid = true;
        }
        //move left
        else if(xCurrent > xMove && yCurrent == yMove || xCurrent < xMove && yCurrent == yMove){
            valid = true;
        }
        return valid;
    }
}
