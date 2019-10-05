public class Square {
        Player player;
        int position;

        public Square(int number){
            player = null;
            position = number;
            if (position == 1){
                new FirstSquare();
            }
        }

    public void enter(Player player) {
        this.player = player;
    }

    public void leave(Player player_leaves){
            if(player_leaves.name == this.player.name){
                this.player = null;
            }
            else{
                System.out.println("You want to move a player which is not on this field");
            }
    }

    public void moveAndLand(int to_move){
            findSquare(to_move);
    }

    private Square findSquare(int to_move){
            Square new_Square = Game.getSquare(to_move);
            if (new_Square.isOccupied()){
                return findSquare(1);
            }
            else{
                return new_Square;
            }
    }

        public boolean isOccupied() {
            if (this.player == null) {
                return true;
            } else {
                return false;
            }
        }
}