public class Board {

    public Object[][][] board;

    public Board() {
        board = new Object[8][8][2];
        initBoard();
        initFigure(Figure.Colors.BLACK, 0);
        initFigure(Figure.Colors.WHITE, 7);
    }

    private void initBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    Square new_square = new Square(Square.Colors.WHITE);
                    board[i][j][0] = new_square;
                } else if (i % 2 != 0 && j % 2 == 0) {
                    Square new_square = new Square(Square.Colors.BLACK);
                    Object[] content = new Object[2];
                    content[0] = new_square;
                    board[i][j][0] = new_square;
                } else if (i % 2 == 0 && j % 2 != 0) {
                    Square new_square = new Square(Square.Colors.BLACK);
                    Object[] content = new Object[2];
                    content[0] = new_square;
                    board[i][j][0] = new_square;
                } else {
                    Square new_square = new Square(Square.Colors.WHITE);
                    Object[] content = new Object[2];
                    content[0] = new_square;
                    board[i][j][0] = new_square;
                }
            }
        }
    }

    private void initFigure(Figure.Colors color, int rowNumber) {
        board[rowNumber][0][1] = new Rook(color);
        board[rowNumber][1][1] = new Knight(color);
        board[rowNumber][2][1] = new Bishop(color);
        board[rowNumber][3][1] = new Queen(color);
        board[rowNumber][4][1] = new King(color);
        board[rowNumber][5][1] = new Bishop(color);
        board[rowNumber][6][1] = new Knight(color);
        board[rowNumber][7][1] = new Rook(color);
        if(color == Figure.Colors.BLACK) {
            rowNumber = 1;
        }
        else{
            rowNumber = 6;
        }
        for(int i = 0; i < 8; i++){
            board[rowNumber][i][1] = new Pawn(color);
        }
    }

    public Object[][][] getBoard(){
        Object[][][] copyboard = new Object[8][8][2];
        return copyboard;

    }

    //    public boolean isValidMove(int x_move, int y_move, Figure board[][], Player player){
//        if(x_move > 8 || y_move > 8){
//            return false;
//        }
//        else if(board[x_move][y_move] != null){
//            //check if there is a white or black figure
//            if(board[x_move][y_move].color == Figure.Colors.BLACK && player.getColor() == board[x_move][y_move].color){
//
//            } else {
//
//            }
//        }
//
//    }
    public boolean isPromoted(){
        return true;
    }

    public boolean promote(){
        return true;
    }

    public boolean castle(){
        return true;
    }

    public boolean passant(){
        return true;
    }

    public boolean check(){
        //where is the king
        //for every figure on board check on possible moves that could hit king!
        //
        //if okay return true

        return true;
    }


    public boolean checkMate(){
        return true;
    }
}

