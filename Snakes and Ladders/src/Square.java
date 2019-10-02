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

    public static void landHereOrGoHome(){

    }


    public static void findSquare(int to_move){
        Game.getSquare(to_move);
    }

}
