public interface Figure {
    enum Type {PAWN, TOWER, KNIGHT, QUEEN, KING, BISHOP}
    enum Colors {BLACK, WHITE}

    public boolean isValidMove(Board gameboard,int x_current, int y_current, int x_move, int y_move, Player player);
    public void move();

}






