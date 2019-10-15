public interface Figure {
    enum Type {PAWN, TOWER, KNIGHT, QUEEN, KING, BISHOP}
    enum Colors {BLACK, WHITE}

    public boolean isValidMove(Board gameboard);
    public void move();

}






