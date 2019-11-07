import java.util.ArrayList;

public class Score implements Subject {
    private ArrayList<Observer> observerList;
//    private ArrayList<Figure> eatenPiecesWhite;
//    private ArrayList<Figure> eatenPiecesBlack;

    public Score(){
        this.observerList = new ArrayList<>();
//        this.eatenPiecesWhite = new ArrayList<>();
//        this.eatenPiecesBlack = new ArrayList<>();
    }

    public void registerObserver(Observer newObserver){
        this.observerList.add(newObserver);
    };
    public void removeObserver(Observer deleteObserver){
        this.observerList.remove(deleteObserver);
    };
    public void notifyObservers(Player whitePlayer, Player blackPlayer, Player.colors color){
        for(Observer observer : this.observerList){
/*            ArrayList<Figure> newEatenPiecesWhite = whitePlayer.getEatenPieces();
            ArrayList<Figure> newEatenPiecesBlack = blackPlayer.getEatenPieces();
            if(newEatenPiecesWhite.size() != eatenPiecesWhite.size()){
                eatenPiecesWhite = whitePlayer.getEatenPieces();
                observer.update(eatenPiecesWhite, eatenPiecesBlack, whitePlayer, blackPlayer, Player.colors.WHITE);
            }
            else if(newEatenPiecesBlack.size() != eatenPiecesBlack.size()){
                eatenPiecesBlack = blackPlayer.getEatenPieces();
                observer.update(eatenPiecesWhite, eatenPiecesBlack, whitePlayer, blackPlayer, Player.colors.BLACK);
            }*/
            observer.update(whitePlayer, blackPlayer, color);
        }
    }
}