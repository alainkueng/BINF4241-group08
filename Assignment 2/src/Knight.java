public class Knight implements Figure {


    private Colors color;
    public Knight(Colors color){
        this.color = color;
    }

    //check documentation of Interface
    public boolean isValidMove(int yCurrent, int xCurrent, int yMove, int xMove){
        boolean valid = false;
        //move down-left or down-right
        if(xMove == xCurrent - 1 && yMove == yCurrent + 2 || xMove == xCurrent + 1 && yMove == yCurrent + 2){
            valid = true;
        }
        //move up-right or up-left
        else if(xMove == xCurrent - 1 && yMove == yCurrent - 2 || xMove == xCurrent + 1 && yMove == yCurrent - 2){
            valid = true;
        }
        //move left-up or left-down
        else if (xMove == xCurrent - 2 && yMove == yCurrent - 1 || xMove == xCurrent - 2 && yMove == yCurrent + 1){
            valid = true;
        }
        //move right-up or right-down
        else if (xMove == xCurrent + 2 && yMove == yCurrent - 1 || xMove == xCurrent + 2 && yMove == yCurrent + 1){
            valid = true;
        }
        else{ valid = false;}
        return valid;
    }

    @Override
    public Colors getColor() {
        return this.color;
    }
}
