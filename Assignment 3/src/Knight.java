public class Knight implements Figure {


    private Colors color;
    public Knight(Colors color){
        this.color = color;
    }

    //check documentation of Interface
    public boolean isValidMove(int xCurrent, int yCurrent,int xMove, int yMove, Player.colors color){
        boolean valid = false;
        //move 2down-1left or 2down-1right
        if(xMove == xCurrent + 2 && yMove == yCurrent + 1 || xMove == xCurrent + 2 && yMove == yCurrent -1){
            valid = true;
        }
        //move 2up-1right or 2up-1left
        else if(xMove == xCurrent - 2 && yMove == yCurrent - 1|| xMove == xCurrent - 2 && yMove == yCurrent + 1){
            valid = true;
        }
        //move 1up-2left or 1up-2right
        else if (xMove == xCurrent + 1 && yMove == yCurrent - 2 || xMove == xCurrent + 1 && yMove == yCurrent + 2){
            valid = true;
        }
        //move 1down-2left or 1down-2right
        else if (xMove == xCurrent -1 && yMove == yCurrent - 2 || xMove == xCurrent -1 && yMove == yCurrent + 2){
            valid = true;
        }
        return valid;
    }

    @Override
    public Colors getColor() {
        return this.color;
    }
}
