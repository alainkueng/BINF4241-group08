class FirstSquare extends Square {

    public FirstSquare(int number, Game game_obj) {
        super(number, game_obj);
    }

    @Override
    public boolean isOccupied() {
        return false;
    }
}
