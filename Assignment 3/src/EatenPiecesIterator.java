import java.util.ArrayList;
import java.util.Iterator;

public class EatenPiecesIterator implements Iterator {
    ArrayList<Figure> eatenPieces;
    int index = 0;

    public EatenPiecesIterator(ArrayList<Figure> eatenPieces){
        this.eatenPieces = eatenPieces;
    }


    @Override
    public boolean hasNext() {
        if(index == eatenPieces.size() || eatenPieces.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object next() {
        Figure fig = eatenPieces.get(index);
        index++;
        return fig;
    }
}
