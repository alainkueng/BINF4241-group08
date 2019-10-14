import java.util.ArrayList;
 public class Player {
    enum colors {BLACK, WHITE}
    private colors color;
    private String name;
    private ArrayList<Figure> eatenPieces;

    Player(String playername, colors playercolor){
        this.color = playercolor;
        this.name = playername;
        this.eatenPieces = new ArrayList<Figure>();
    }
    
}
