import java.util.List;

public class Board {

    public Object[][][] board;
    public Figure.Type[] figureFirstRow;

    public Board(){
        board = new Object[8][8][2];
        Figure.Type[] figureFirstRow = new Figure.Type[]
                {Figure.Type.TOWER, Figure.Type.KNIGHT, Figure.Type.BISHOP, Figure.Type.KING, Figure.Type.QUEEN,
                Figure.Type.BISHOP, Figure.Type.KNIGHT, Figure.Type.TOWER};
        initBoard();
        initFigure(Figure.Colors.BLACK);
        initFigure(Figure.Colors.WHITE);
    }

    private void initBoard(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (i % 2 != 0 && j % 2 != 0){
                    Square new_square = new Square(Square.Colors.WHITE);
                    board[i][j][0] = new_square;
                }
                else if (i % 2 != 0 && j % 2 == 0){
                    Square new_square = new Square(Square.Colors.BLACK);
                    Object[] content = new Object[2];
                    content[0] = new_square;
                    board[i][j][0] = new_square;
                }
                else if (i % 2 == 0 && j % 2 != 0){
                    Square new_square = new Square(Square.Colors.BLACK);
                    Object[] content = new Object[2];
                    content[0] = new_square;
                    board[i][j][0] = new_square;
                }
                else{
                    Square new_square = new Square(Square.Colors.WHITE);
                    Object[] content = new Object[2];
                    content[0] = new_square;
                    board[i][j][0] = new_square;
                }
            }
        }
    }
    private void initFigure(Figure.Colors color){
        if(color == Figure.Colors.BLACK){

        }

    }
    /*
    private void initFigure(Figure.Colors color){
        if(color == Figure.Colors.BLACK){
            for(int i = 0; i < 8; i++){
                board[0][i][1] = new Figure(figureFirstRow[i], color);
            }
            for(int i = 0; i < 8; i++){
                board[1][i][1] = new Figure(Figure.Type.PAWN, color);
            }
        }
        else{
            for(int i = 0; i < 8; i++){
                board[7][i][1] = new Figure(figureFirstRow[i], color);
            }
            for(int i = 0; i < 8; i++){
                board[6][i][1] = new Figure(Figure.Type.PAWN, color);
            }
        }
    }*/
}
