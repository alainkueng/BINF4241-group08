public class Bishop implements Figure{


    Colors color;
    public Bishop(Colors color){
        this.color = color;
    }

    //check documentation of Interface
    public boolean isValidMove(int xCurrent, int yCurrent, int xMove, int yMove){
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
}
