public class Bishop implements Figure{


    private Colors color;
    public Bishop(Colors color){
        this.color = color;
    }

    //check documentation of Interface
    public boolean isValidMove(int yCurrent, int xCurrent, int yMove, int xMove, Player.colors color){
        boolean valid = false;
        if(xMove > 8 || yMove > 8){
            valid = false;
        }
        //move all diagonal ways
        else if(Math.abs(xMove - xCurrent) == Math.abs(yMove - yCurrent)){
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
