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
    public String getName(){
        return name;
    }
    public void setEatenPieces(Figure figure){
        eatenPieces.add(figure);
        //needs to check if a figure has been eaten or anyone could add a random figure to the dumbster
    }
}
