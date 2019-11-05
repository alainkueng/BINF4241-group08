import java.util.ArrayList;

public interface Subject {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers(Player whitePlayer, Player blackPlayer, Player.colors color);
}