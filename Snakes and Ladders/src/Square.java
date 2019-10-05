public class Square {
        Player player;
        int position;

        public Square(int number){
            player = null;
            position = number;
        }

    public void enter(Player player) {
        this.player = player;
    }

    public void leave(Player player_leaves){
            this.player = null;
            //if (){}
    }

    public void move(int to_move){
            Square next = findSquare(to_move);
            landHereOrGoHome();
    }

    private static Square findSquare(int to_move){
            return Game.getSquare(to_move);
    }


    public Square landHereOrGoHome(){
            boolean occupied = false;
            occupied = isOccupied();
        if (occupied){
            return Square FirstSquare;
        }
        else{
            return this;
        }

    }

        public boolean isOccupied(){
            if(this.player == null){
                return true;
            }
            else{
        }
}
