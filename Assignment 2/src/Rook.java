import java.util.ArrayList;
import java.util.Collection;

public class Rook implements Figure {
    Colors color;
    Collection<Square> possibleMoves;
    public Rook(Colors color){
        this.color = color;
        possibleMoves = new ArrayList<Square>();
    }


    @Override
    public boolean isValidMove(Board gameboard, int xCurrent, int yCurrent, int xMove, int yMove){
        boolean valid = false;
        if(xMove > 8 || yMove > 8){
            valid = false;
        }
        //move down
        else if(xCurrent == xMove && yCurrent < yMove){
            valid = true;
        }
        //move up
        else if(xCurrent == xMove && yCurrent > yMove){
            valid = true;
        }
        //move left
        else if(xCurrent > xMove && yCurrent == yMove){
            valid = true;
        }
        //move right
        else if(xCurrent < xMove && yCurrent == yMove){
            valid = true;
        }
        return valid;
    }
}
