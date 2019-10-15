public interface Figure {
    enum Type {PAWN, TOWER, KNIGHT, QUEEN, KING, BISHOP}
    enum Colors {BLACK, WHITE}

    public void isValidMove();
    public void move();

}






