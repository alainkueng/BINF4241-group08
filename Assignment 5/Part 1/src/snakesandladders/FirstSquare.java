package snakesandladders;

class FirstSquare extends Square {

    public FirstSquare(int number, Game game_obj) {
        super(number, game_obj);
    }

    @Override
    public void leave(Player player_leaves) {
        for(int i = 0; i < this.player_list.size(); i++){
            if(this.player_list.get(i) == player_leaves){
                this.player_list.remove(i);
            }
        }
    }

    @Override
    public boolean isOccupied() {
        return false;
    }
}
