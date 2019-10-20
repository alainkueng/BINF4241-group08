import java.util.Collection;

public interface Figure {
    enum Colors {BLACK, WHITE}

    /**
     * @param yCurrent - current x coordinate of figure
     * @param xCurrent - current y coordinate of figure
     * @param yMove - x coordinate where figure has to be moved
     * @param xMove - y coordinate where figure has to be moved
     * @return boolean to check if move of the figure is even valid
     */
    public boolean isValidMove(int yCurrent, int xCurrent, int yMove, int xMove);
    public Colors getColor();
}






