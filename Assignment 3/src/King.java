public class King implements Figure {


    private Colors color;
    private boolean moved;
    public King(Colors color){
        this.color = color;
        moved = false;
    }

    //check documentation of Interface
    public boolean isValidMove(int yCurrent, int xCurrent, int yMove, int xMove, Player.colors color){
        boolean valid;
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

    @Override
    public Colors getColor() {
        return this.color;
    }
    //set moved to true
    public void kingHasMoved(){
        this.moved = true;
    }

    //returns true if king has moved
    public boolean getHasMoved(){
        return this.moved;
    }
}
