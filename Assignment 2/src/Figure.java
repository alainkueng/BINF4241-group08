public class Figure {
    enum Type {PAWN, TOWER, KNIGHT, QUEEN, KING, BISHOP}
    enum Colors {BLACK, WHITE}
    Type name;
    Colors color;
    //        Figure fig = new Figure(Figure.Type.KING, Figure.Colors.BLACK);
    public Figure(Type name, Colors color){
        this.name = name;
        this.color = color;
    }



}






