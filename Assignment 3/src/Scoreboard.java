import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scoreboard implements Observer {
    private Map <Player.colors, Integer> scoreList = new HashMap<Player.colors, Integer>();
    private ArrayList<Figure> eatenPiecesWhite;
    private ArrayList<Figure> eatenPiecesBlack;
    public Scoreboard(){
        scoreList.put(Player.colors.BLACK, 0);
        scoreList.put(Player.colors.WHITE, 0);
        eatenPiecesWhite = new ArrayList<>();
        eatenPiecesBlack = new ArrayList<>();
    }
    public void update(Player whitePlayer, Player blackPlayer, Player.colors color){
        ArrayList<Figure> newEatenPiecesWhite = whitePlayer.getEatenPieces();
        ArrayList<Figure> newEatenPiecesBlack = blackPlayer.getEatenPieces();
        if(didScore(this.eatenPiecesWhite, newEatenPiecesWhite) || didScore(this.eatenPiecesBlack, newEatenPiecesBlack)){
            ArrayList<Figure> piecesList;
            if(whitePlayer.getColor() == color){
                piecesList = newEatenPiecesWhite;
                this.eatenPiecesWhite.add(newEatenPiecesWhite.get(newEatenPiecesWhite.size()-1));
            }
            else{
                piecesList = newEatenPiecesBlack;
                this.eatenPiecesBlack.add(newEatenPiecesBlack.get(newEatenPiecesBlack.size()-1));
            }
            Figure eatenPiece = piecesList.get(piecesList.size()-1);
            if(eatenPiece.getClass() == Queen.class){
                this.scoreList.put(color, this.scoreList.get(color) + 5);
            }
            else{
                this.scoreList.put(color, this.scoreList.get(color) + 1);
            }
        }
        printScoreboard(whitePlayer, blackPlayer);
/*        Figure eatenPiece = null;
        if(color == Player.colors.BLACK){
            eatenPiece = (Figure) eatenPiecesBlack.get(eatenPiecesBlack.size()-1);
        }
        else{
            eatenPiece = (Figure) eatenPiecesWhite.get(eatenPiecesWhite.size()-1);
        }

        if(eatenPiece.getClass() == Queen.class){
            scoreList.put(color, scoreList.get(color) + 5);
        }
        else{
            scoreList.put(color, scoreList.get(color) + 1);
        }
        printScoreboard(whitePlayer, blackPlayer);*/
    }
    private boolean didScore(ArrayList savedList, ArrayList newList){
        return savedList.size() != newList.size();
    }

    private void printScoreboard(Player whitePlayer, Player blackPlayer){
        System.out.format("\n\n%s's score: %d\n", whitePlayer.getName(), scoreList.get(Player.colors.WHITE));
        System.out.format("%s's score: %d\n", blackPlayer.getName(), scoreList.get(Player.colors.BLACK));
    }
}