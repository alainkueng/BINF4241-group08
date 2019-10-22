public class Pawn implements Figure {


    private Colors color;
    private boolean movedTwo;

    public Pawn(Colors color) {
        this.color = color;
        this.movedTwo = false;
    }

    //check documentation of Interface
    public boolean isValidMove(int xCurrent, int yCurrent,int xMove, int yMove, Player.colors color) {
        boolean valid = false;
        //check if its the first move and player wants to move forward
        String check = color.toString();
        if (check == "WHITE"){
            if (xCurrent == 6 && xMove == 4 && yCurrent == yMove) {//start could jump 2
                valid = true;
            }
            else if (xCurrent-1 == xMove && yCurrent == yMove){//Pawn wants to move forward
                valid = true;
            }
            else if (xCurrent-1 == xMove && (yCurrent -1 == yMove || xCurrent+1 ==yMove)){//eat diagonal
                valid = true;
            }
        }
        if (check == "BLACK"){
            if(xCurrent == 1 && xMove == 3 && yCurrent == yMove){//start could jump 2
                valid = true;
               }
            else if(yCurrent + 1 == xMove && yCurrent == yMove){ //Pawn wants to move forward
                valid = true;
            }
            else if (yCurrent+1 == xMove && (yCurrent -1 == yMove || yCurrent+1 ==yMove)){//eat diagonal
                valid = true;
           }

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

