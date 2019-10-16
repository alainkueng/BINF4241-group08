public class Knight implements Figure {


    Colors color;
    public Knight(Colors color){
        this.color = color;
    }

    //check documentation of Interface
    public boolean isValidMove(int xCurrent, int yCurrent, int xMove, int yMove){
        boolean valid = false;
        //move down-left or down-right
        if(xMove == xCurrent - 1 && yMove == yCurrent + 2 || xMove == xCurrent + 1 && yMove == yCurrent + 2){
            valid = true;
        }
        //move up-right or up-left
        else if(xMove == xCurrent - 1 && yMove == yCurrent - 2 || xMove == xCurrent + 1 && yMove == yCurrent - 2){
            valid = true;
        }
        else{ valid = false;}
        return valid;
    }
}
