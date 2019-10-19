public class Pawn implements Figure {


    Colors color;
    public Pawn(Colors color){
        this.color = color;
    }

    //check documentation of Interface
    public boolean isValidMove(int xCurrent, int yCurrent, int xMove, int yMove){
        boolean valid = false;
        //check if its the first move and player wants to move forward
        if (yCurrent == 1 && yMove == 3 && xCurrent == xMove || yCurrent == 6 && yMove == 4 && xCurrent == xMove) {
            valid = true;
        }
        // pawn is not in initial position and wants to move forward}
        else if(yCurrent == 1 && yMove == yCurrent + 1 && xCurrent == xMove || yCurrent == 6 && yMove == yCurrent - 1 && xCurrent == xMove){
            valid = true;
        }
        // player wants to move diagonal to eat opponent
        else if(yMove == yCurrent + 1 && xMove == xCurrent - 1 || yMove == yCurrent + 1 && xMove == xCurrent + 1 || yMove == yCurrent - 1 && xMove == xCurrent - 1 || yMove == yCurrent - 1 && xMove == xCurrent + 1){
            valid = true;
        } else {
            valid = false;
        }
        return valid;
    }

    @Override
    public Colors getColor() {
        return this.color;
    }
}
