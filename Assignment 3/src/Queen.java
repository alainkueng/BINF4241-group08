public class Queen implements Figure {


    private Colors color;
    public Queen(Colors color) {
        this.color = color;
    }

    //check documentation of Interface
    @SuppressWarnings("Duplicates")
    public boolean isValidMove(int xCurrent, int yCurrent,int xMove, int yMove, Player.colors color) {
        boolean valid = false;

        //move all diagonal ways
        if(Math.abs(xMove - xCurrent) == Math.abs(yMove - yCurrent)){
            valid = true;
        }
        //move down or up
        else if(xCurrent == xMove && yCurrent < yMove || xCurrent == xMove && yCurrent > yMove){
            valid = true;
        }
        //move left or right
        else if(xCurrent > xMove && yCurrent == yMove || xCurrent < xMove && yCurrent == yMove){
            valid = true;
        }
        else {valid = false;}
        return valid;
    }

    @Override
    public Colors getColor() {
        return this.color;
    }
}
