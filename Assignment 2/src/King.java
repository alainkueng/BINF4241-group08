public class King implements Figure {


    Colors color;
    public King(Colors color){
        this.color = color;
    }

    //check documentation of Interface
    public boolean isValidMove(int xCurrent, int yCurrent, int xMove, int yMove){
        boolean valid = false;
        //move down or down
        if(xCurrent == xMove && yMove == yCurrent + 1 || xCurrent == xMove && yMove == yCurrent - 1)
            valid = true;
        //move left or right
        else if(yCurrent == yMove && xMove == xCurrent - 1 || yCurrent == yMove && xMove == xCurrent + 1){valid = true;}
        //move down-diagonally
        else if(xMove == xCurrent - 1 && yMove == yCurrent + 1 || xMove == xCurrent + 1 && yMove == yCurrent + 1){valid = true;}
        //move up-diagonally
        else if(xMove == xCurrent - 1 && yMove == yCurrent - 1 || xMove == xCurrent + 1 && yMove == yCurrent - 1){valid = true;}
        else {valid = false;}
        return valid;
    }
}
