public class Rook implements Figure {


    private Colors color;
    private boolean moved;
    public Rook(Colors color){
        this.color = color;
        this.moved = false;
    }

    //check documentation of Interface
    @SuppressWarnings("Duplicates")
    public boolean isValidMove(int xCurrent, int yCurrent,int xMove, int yMove, Player.colors color){
        boolean valid;
        //move up or down
        if(xCurrent == xMove && yCurrent > yMove || xCurrent == xMove && yCurrent < yMove){
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

    //set moved to true
    public void RookHasMoved(){
        this.moved = true;
    }

    //returns true if king has moved
    public boolean getHasMoved(){
        return this.moved;
    }
}
