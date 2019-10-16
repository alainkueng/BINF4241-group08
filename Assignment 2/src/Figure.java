import java.util.Collection;

public interface Figure {
    enum Colors {BLACK, WHITE}


    public boolean isValidMove(Board gameboard, int xCurrent, int yCurrent, int xMove, int yMove);
}






