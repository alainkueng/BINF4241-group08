public class Board {
    public Square[][] board;

    public Board(){
        board = new Square[8][8];
        initBoard();
    }

    private void initBoard(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (i % 2 != 0 && j % 2 != 0){
                    Square new_square = new Square(Square.Colors.WHITE);
                    board[i][j] = new_square;
                }
                else if (i % 2 != 0 && j % 2 == 0){
                    Square new_square = new Square(Square.Colors.BLACK);
                    board[i][j] = new_square;
                }
                else if (i % 2 == 0 && j % 2 != 0){
                    Square new_square = new Square(Square.Colors.BLACK);
                    board[i][j] = new_square;
                }
                else{
                    Square new_square = new Square(Square.Colors.WHITE);
                    board[i][j] = new_square;
                }
            }
        }
    }
    private void initFigure(){
    }
}
