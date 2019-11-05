import java.util.ArrayList;

public interface Observer {
    public void update(Player whitePlayer, Player blackPlayer, Player.colors color);
}