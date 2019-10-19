public class Board {

    public Object[][][] board;

    public Board() {
        board = new Object[8][8][2];
        initBoard();
        initFigure(Figure.Colors.BLACK, 0);
        initFigure(Figure.Colors.WHITE, 7);
    }

    /**
     * Initalizes a chessboard 8x8 with the known white and black chequering.
     */
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

    /**
     * @param color The current color or side that should be initialized
     * @param rowNumber The edge of the board, either on the black or the white side
     *                  Initializes the figures in the right order on the chessboard.
     */
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

    /**
     * creates a copy of the current game board
     * @return returns a copy of the current game board
     */

    public Object[][][] getBoard(){
        Object[][][] boardCopy;
        boardCopy = board.clone();
        return boardCopy;
    }

    /**
     *
     * @param pawnToPromote - the pawn that has reached the promotion area
     * @param xCurrent - current x coordinate of the pawn
     * @param yCurrent - current y coordinate of the pawn
     *
     *  We can implement this method in our MOVE METHOD, with an if statement we can check if a pawn is being moved
     *  and then we can call this method here to check if the pawn has to be promoted.
     *  If the pawn has to be promoted the method also does this automatically.
     */
    public void promote(Pawn pawnToPromote, int xCurrent, int yCurrent){
        if(pawnToPromote.getColor() == Figure.Colors.BLACK && yCurrent == 7)
            board[xCurrent][yCurrent][1] = new Queen(Figure.Colors.BLACK);
        else if(pawnToPromote.getColor() == Figure.Colors.WHITE && yCurrent == 0)
            board[xCurrent][yCurrent][1] = new Queen(Figure.Colors.WHITE);
    }

    /**
     * INPUT = 0-0 for Kings' castle and 0-0-0 for queens' castle -->
     * User input has to be parsed to this 0-0:int 0 or 0-0-0: int 1
     * King has not moved
     * King not in check
     * King does not pass through check
     * No figure between king and rook
     * @return
     * TODO: Move method to implement it here
     */
    public void castle(King kingToCastle, int input) {
        boolean blockedPath = false;
        if (input == 0) {
            if (!kingToCastle.getHasMoved() && !check(0,4,kingToCastle)) {
                if (kingToCastle.getColor() == Figure.Colors.BLACK && board[0][7][1] != null && board[0][7][1].getClass() == Rook.class) {
                    Rook rook = (Rook) board[0][7][1];
                    if (rook.getColor() == Figure.Colors.BLACK && !rook.getHasMoved()) {
                        //prove that path is empty and king does not go through check
                        for (int i = 5; i < 7; i++) {
                            if (board[0][i][1] == null && !check(0, i, kingToCastle))
                                continue;
                            else
                                blockedPath = true;
                            break;
                        }
                        if (!blockedPath) {
                            //move king to row 0, column 6
                            //move rook to row 0, column 5
                        }
                    }
                }
            }
            else if(!kingToCastle.getHasMoved() && !check(7,4,kingToCastle)){
                if(kingToCastle.getColor() == Figure.Colors.WHITE && board[7][7][1] != null && board[0][7][1].getClass() == Rook.class) {
                    Rook rook = (Rook) board[7][7][1];
                    if (rook.getColor() == Figure.Colors.WHITE && !rook.getHasMoved()) {
                        //prove that path is empty and king does not go through check
                        for (int i = 5; i < 7; i++) {
                            if (board[7][i][1] == null && check(7, i, kingToCastle))
                                continue;
                            else
                                blockedPath = true;
                            break;
                        }
                        if (!blockedPath) {
                            //move king to row 7, column 6
                            //move rook to row 7, column 5
                        }
                    }
                }
            }
        }
        else if (input == 1){
            if (!kingToCastle.getHasMoved() && !check(0,4,kingToCastle)) {
                if (kingToCastle.getColor() == Figure.Colors.BLACK && board[0][0][1] != null && board[0][0][1].getClass() == Rook.class) {
                    Rook rook = (Rook) board[0][0][1];
                    if (rook.getColor() == Figure.Colors.BLACK && !rook.getHasMoved()) {
                        //prove that path is empty and king does not go through check
                        for (int i = 3; i > 1; i--) {
                            if (board[0][i][1] == null && check(0, i, kingToCastle))
                                continue;
                            else
                                blockedPath = true;
                            break;
                        }
                        if (!blockedPath) {
                            //move king to row 0, column 2
                            //move rook to row 0, column 3
                        }
                    }
                }
            }
            else if(!kingToCastle.getHasMoved() && !check(7,4,kingToCastle)) {
                if (kingToCastle.getColor() == Figure.Colors.WHITE && board[7][0][1] != null && board[7][0][1].getClass() == Rook.class) {
                    Rook rook = (Rook) board[7][0][1];
                    if (rook.getColor() == Figure.Colors.WHITE && !rook.getHasMoved()) {
                        //prove that path is empty and king does not go through check
                        for (int i = 3; i > 1; i--) {
                            if (board[7][i][1] == null && !check(7, i, kingToCastle))
                                continue;
                            else
                                blockedPath = true;
                            break;
                        }
                        if (!blockedPath) {
                            //move king to row 7, column 2
                            //move rook to row 7, column 3
                        }
                    }
                }
            }
        }
    }

    public boolean passant(){
        return true;
    }

    /**
     * Method checks if all pieces of the opposition could land on (x,y) with a valid move
     * @param x - x coordinate
     * @param y - y coordinate
     * @param figure - We use figure and not King because we use this method to check all figures in the castle method
     * @return true if Figure(King) in (x,y) is in check, false if not
     */
    public boolean check(int x, int y, Figure figure){
        //where is the king
        //for every figure on board check on possible moves that could hit king!
        //
        //if okay return true
        boolean check = false;
        //iterate through whole board
        for(int row = 0;row < 8; row++){
            for(int col = 0; col < 8; col++){
                //for every figure or the opposition check if they can land on (x,y)
                if(board[row][col][1] != null) {
                    if (board[row][col][1] != null) {
                        Figure currentFig = (Figure) board[row][col][1];
                        if (figure.getColor() != figure.getColor())
                            check = currentFig.isValidMove(col, row, y, x);
                        if (check)
                            break;
                    }
                }
            }
        }
        return check;
    }

    /**
     * Method takes the king at coordinates (x,y) and uses the check Method to see if the king can move to a safe position
     * If not, then check mate evaluates to true
     * @param x -
     * @param y -
     * @param king -
     * @return true if king cant move to a safe place, else false
     */
    public boolean checkMate(int x, int y, King king){
        boolean checkMate = false;
        if(check(x,y,king)){
            if(x + 1 < 8 && y + 1 < 8){
                if (check(x+1,y,king) && board[y][x+1][1] != null || check(x-1,y,king) && board[y][x-1][1] != null
                        || check(x,y+1,king) && board[y+1][x][1] != null || check(x,y-1,king) && board[y-1][x][1] != null
                        || check(x+1,y+1,king) && board[y+1][x+1][1] != null
                        || check(x+1,y-1,king) && board[y-1][x+1][1] != null
                        || check(x-1,y+1,king) && board[y+1][x-1][1] != null
                        || check(x-1,y-1,king) && board[y+1][x-1][1] != null){
                    checkMate = true;
                }
            }
        }
        return checkMate;
    }
}

