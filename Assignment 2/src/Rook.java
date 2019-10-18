public class Rook implements Figure {


    private Colors color;
    private boolean moved;
    public Rook(Colors color){
        this.color = color;
        this.moved = false;
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
