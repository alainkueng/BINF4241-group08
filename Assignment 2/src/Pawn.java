public class Pawn implements Figure {


    private Colors color;
    private boolean movedTwo;

    public Pawn(Colors color) {
        this.color = color;
        this.movedTwo = false;
    }

    //check documentation of Interface
    public boolean isValidMove(int yCurrent, int xCurrent,int yMove, int xMove) {
        boolean valid = false;
        //check if its the first move and player wants to move forward
        if (yCurrent == 1 && yMove == 3 && xCurrent == xMove || yCurrent == 6 && yMove == 4 && xCurrent == xMove) {
            valid = true;

        }
        // pawn is not in initial position and wants to move forward}
        else if (yCurrent == 1 && yMove == yCurrent + 1 && xCurrent == xMove || yCurrent == 6 && yMove == yCurrent - 1 && xCurrent == xMove) {
            valid = true;
        }
        // player wants to move diagonal to eat opponent
        else if (yMove == yCurrent + 1 && xMove == xCurrent - 1 || yMove == yCurrent + 1 && xMove == xCurrent + 1 || yMove == yCurrent - 1 && xMove == xCurrent - 1 || yMove == yCurrent - 1 && xMove == xCurrent + 1) {
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
    public void setMovedTwo () {
        this.movedTwo = true;
    }
    public boolean getMovedTwo () {
        return this.movedTwo;
    }
}

