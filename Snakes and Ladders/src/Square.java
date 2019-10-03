public class Square {
        Player player;

        public Square(){
            player = null;
        }

    public void enter(Player player) {
        this.player = player;
    }

    public void leave(){
        this.player = null;
    }

    public void move(int to_move){
            Square next = findSquare(to_move);
            landHereOrGoHome();
    }

    private static Square findSquare(int to_move){
            return Game.getSquare(to_move);
    }


    public Square landHereOrGoHome(){
        if (isOccupied()){
            return Square FirstSquare;
        }
        else{
            return null;
        }
    }
}
