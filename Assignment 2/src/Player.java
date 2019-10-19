import java.util.ArrayList;
 public class Player {
    enum colors {BLACK, WHITE}
    private colors color;
    private String name;
    private ArrayList<Figure> eatenPieces;

    Player(String playerName, colors playerColor){
        this.color = playerColor;
        this.name = playerName;
        this.eatenPieces = new ArrayList<Figure>();
    }

     /**
      * @return Name of player as a String
      */
    public String getName(){
        return this.name;
    }

     /**
      * @param figure Piece which was eaten on the board
      *               Adds lost pieces on the field to an ArrayList which stores all eaten pieces
      */
    public void setEatenPieces(Figure figure){
        eatenPieces.add(figure);
        //needs to check if a figure has been eaten or anyone could add a random figure to the dumbster
    }

     /**
      * @return Color of Player as an enum of colors
      */
    public colors getColor(){
        return this.color;
    }
}
