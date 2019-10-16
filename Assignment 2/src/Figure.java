import java.util.Collection;

public interface Figure {
    enum Colors {BLACK, WHITE}

    /**
     * @param xCurrent - current x coordinate of figure
     * @param yCurrent - current y coordinate of figure
     * @param xMove - x coordinate where figure has to be moved
     * @param yMove - y coordinate where figure has to be moved
     * @return boolean to check if move of the figure is even valid
     */
    public boolean isValidMove(int xCurrent, int yCurrent, int xMove, int yMove);
}






