import java.util.ArrayList;

/** @noinspection ALL*/
public class Board {

    public Object[][][] board;
    private Object[] lastMove = new Object[3];//fig,x,y
    private boolean checkmate;
    private boolean whitecastling;
    private boolean blackcastling;

    public Board() {
        board = new Object[8][8][2];
        checkmate = false;
        whitecastling = false;
        blackcastling = false;
        initBoard();
        initFigure(Figure.Colors.BLACK, 0);
        initFigure(Figure.Colors.WHITE, 7);
    }

    /**
     * Initializes a chessboard 8x8 with the known white and black chequering.
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
     * @param color     The current color or side that should be initialized
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
        if (color == Figure.Colors.BLACK) {
            rowNumber = 1;
        } else {
            rowNumber = 6;
        }
        for (int i = 0; i < 8; i++) {
            board[rowNumber][i][1] = new Pawn(color);
        }
    }

    /**
     * creates a copy of the current game board
     *
     * @return returns a copy of the current game board
     */

    public Object[][][] getBoard() {
        Object[][][] boardCopy;
        boardCopy = board.clone();
        return boardCopy;
    }

    /**
     * @param pawn          - class pawn
     * @param xCurrent      - current x coordinate of the pawn
     * @param yCurrent      - current y coordinate of the pawn
     * @param xMove         - x coordinate where pawn is to be moved
     * @param yMove         - y coordinate where pawn is to be moved
     * @param promoteInfo   - Array of class Object: promoteInfo[1] = class of figure user wants to promote
     * @param color         - Color of player
     *
     *                      We can implement this method in our MOVE METHOD, with an if statement we can check if a pawn is being moved
     *                      and then we can call this method here to check if the pawn has to be promoted.
     *                      If the pawn has to be promoted the method also does this automatically.
     */

    public boolean promote(Class pawn, int xCurrent, int yCurrent, int xMove, int yMove, Class promoteInfo, Player.colors color) {
        boolean promoteSuccess = false;
        Pawn pawnToPromote = (Pawn) board[xCurrent][yCurrent][1];
        Figure fig = null;
        if(promoteInfo == Rook.class){
            if(color.name() == Figure.Colors.WHITE.name()){
                Rook rookW = new Rook(Figure.Colors.WHITE);
                fig = rookW;
            } else {
                Rook rookB = new Rook(Figure.Colors.BLACK);
                fig = rookB;
            }
        }
        else if(promoteInfo == Bishop.class){
            if(color.name() == Figure.Colors.WHITE.name()){
                Bishop bishopW = new Bishop(Figure.Colors.WHITE);
                fig = bishopW;
            } else {
                Bishop bishopB = new Bishop(Figure.Colors.BLACK);
                fig = bishopB;
            }

        }
        else if(promoteInfo == Knight.class){
            if(color.name() == Figure.Colors.WHITE.name()){
                Knight knightW = new Knight(Figure.Colors.WHITE);
                fig = knightW;
            } else {
                Knight knightB = new Knight(Figure.Colors.BLACK);
                fig = knightB;
            }
        }
        else if(promoteInfo == Queen.class){
            if(color.name() == Figure.Colors.WHITE.name()){
                Queen queenW = new Queen(Figure.Colors.WHITE);
                fig = queenW;
            } else {
                Queen queenB = new Queen(Figure.Colors.BLACK);
                fig = queenB;
            }
        }
        if (pawnToPromote.getColor() == Figure.Colors.BLACK && pawnToPromote.isValidMove(xCurrent,yCurrent,xMove,yMove, color) && !isOccupied(xMove,yMove) && fig != null) {
            board[xMove][yMove][1] = fig;
            board[xCurrent][yCurrent][1] = null;
            promoteSuccess = true;
        }
        else if (pawnToPromote.getColor() == Figure.Colors.WHITE && pawnToPromote.isValidMove(xCurrent,yCurrent,xMove,yMove, color) && !isOccupied(xMove,yMove) && fig != null) {
            board[xMove][yMove][1] = fig;
            board[xCurrent][yCurrent][1] = null;
            promoteSuccess = true;
        }
        return promoteSuccess;
    }


    /**
     * INPUT = 0-0 for Kings' castle and 0-0-0 for queens' castle -->
     * User input has to be parsed to this 0-0:int 0 or 0-0-0: int 1
     * King has not moved
     * King not in check
     * King does not pass through check
     * No figure between king and rook
     *
     * @return TODO: Move method to implement it here
     */
    @SuppressWarnings("Duplicates")
    public boolean castleKingSide(Player.colors color) {
        boolean castled = false;
        //black king
        if(color.toString() == Figure.Colors.BLACK.toString()) {
            if (board[0][4][1].getClass() == King.class) {
                King kingToCastle = (King) board[0][4][1];
                if (!check(0, 4, kingToCastle, color) && !kingToCastle.getHasMoved() && color.equals(Player.colors.BLACK) && kingToCastle.getColor().equals(Figure.Colors.BLACK)) {
                    if (kingToCastle.getColor() == Figure.Colors.BLACK && board[0][7][1] != null && board[0][7][1].getClass() == Rook.class) {
                        Rook rook = (Rook) board[0][7][1];
                        if (rook.getColor() == Figure.Colors.BLACK && !rook.getHasMoved()) {
                            //prove that path is empty and king does not go through check
                            for (int i = 5; i < 7; i++) {
                                if (board[0][i][1] == null && !check(0, i, kingToCastle, color))
                                    castled = true;
                                else
                                    castled = false;
                                break;
                            }
                            if (castled) {
                                board[0][6][1] = kingToCastle;
                                board[0][4][1] = null;

                                board[0][5][1] = rook;
                                board[0][7][1] = null;
                                //move king to row 7, column 6
                                //move rook to row 7, column 5
                            }
                        }
                    }
                }
            }
        }
            //white king
       if (color.toString() == Figure.Colors.WHITE.toString()) {
           if (board[7][4][1].getClass() == King.class) {
               King kingToCastle = (King) board[7][4][1];
               if (!check(7, 4, kingToCastle, color) && !kingToCastle.getHasMoved() && color.equals(Player.colors.WHITE) && kingToCastle.getColor().equals(Figure.Colors.WHITE)) {
                   if (kingToCastle.getColor() == Figure.Colors.WHITE && board[7][7][1] != null && board[7][7][1].getClass() == Rook.class) {
                       Rook rook = (Rook) board[7][7][1];
                       if (rook.getColor() == Figure.Colors.WHITE && !rook.getHasMoved()) {
                           //prove that path is empty and king does not go through check
                           for (int i = 5; i < 7; i++) {
                               if (board[7][i][1] == null && !check(7, i, kingToCastle, color))
                                   castled = true;
                               else
                                   castled = false;
                               break;
                           }
                           if (castled) {
                               board[7][6][1] = kingToCastle;
                               board[7][4][1] = null;

                               board[7][5][1] = rook;
                               board[7][7][1] = null;
                               //move king to row 0, column 6
                               //move rook to row 0, column 5
                           }
                       }
                   }
               }
           }
       }

        return castled;
    }



    public boolean castleQueenSide(Player.colors color) {
        boolean castled = false;
        //black king
        if (color.toString() == Figure.Colors.BLACK.toString()) {
            if (board[0][4][1].getClass() == King.class) {
                King kingToCastle = (King) board[0][4][1];
                if (!check(0, 4, kingToCastle, color) && !kingToCastle.getHasMoved() && color.equals(Player.colors.BLACK) && kingToCastle.getColor().equals(Figure.Colors.BLACK)) {
                    Rook rook = (Rook) board[0][0][1];
                    if (rook.getColor() == Figure.Colors.BLACK && board[0][0][1] != null && board[0][0][1].getClass() == Rook.class) {
                        //prove that path is empty and king does not go through check
                        for (int i = 3; i > 0; i--) {
                            if (board[0][i][1] == null && !check(0, i, kingToCastle, color))
                                castled = true;
                            else
                                castled = false;
                            break;
                        }
                        if (castled) {
                            board[0][2][1] = kingToCastle;
                            board[0][4][1] = null;

                            board[0][3][1] = rook;
                            board[0][0][1] = null;
                        }
                    }
                }
            }
        }
        //white king
        if (color.toString() == Figure.Colors.WHITE.toString()) {
            if (board[7][4][1].getClass() == King.class) {
                King kingToCastle = (King) board[7][4][1];
                if (!check(7, 4, kingToCastle, color) && !kingToCastle.getHasMoved() && color.equals(Player.colors.WHITE) && kingToCastle.getColor().equals(Figure.Colors.WHITE)) {
                    Rook rook = (Rook) board[7][0][1];
                    if (rook.getColor() == Figure.Colors.WHITE && !rook.getHasMoved()) {
                        //prove that path is empty and king does not go through check
                        for (int i = 3; i > 0; i--) {
                            if (board[7][i][1] == null && !check(7, i, kingToCastle, color))
                                castled = true;
                            else
                                castled = false;
                            break;
                        }
                        if (castled) {
                            board[7][2][1] = kingToCastle;
                            board[7][4][1] = null;

                            board[7][3][1] = rook;
                            board[7][0][1] = null;
                        }
                    }
                }
            }
        }
        return castled;
    }

    /**
     * * Capturing pawn has to be in Rank 5
     * Captured pawn must be in adjacent square and just have moved two squares
     * TODO: add eaten piece to the player garbage list
     *
     * @param pawn          - pawn Class
     * @param yLastMove   - y coordinate of last move of opponent
     * @param xLastMove   - x coordinate of last move of opponent
     * @param xPawn       - x coordinate of this pawn
     * @param yPawn       - y coordinate of this pawn
     * @param xPawnMove   - x coordinate where pawn wants to move
     * @param yPawnMove   - y coordinate where pawn wants to move
     * @return true if passant was done succesfully
     */
    @SuppressWarnings("Duplicates") //pawn, xC,yC ,xM,yM
    public boolean passant(Class pawn, int xPawn, int yPawn, int xPawnMove, int yPawnMove, int xLastMove, int yLastMove, Player.colors color) {
        boolean passant;
        Pawn pawnPassant = (Pawn) board[xPawn][yPawn][1];
        String lastClass = lastMove[0].toString();
        if (!(pawnPassant.getMovedTwo()) && lastClass.equals(pawn.toString())) {
            //if white pawn, yPawn must be 5 and check if yPawn and yLastMoved are the same || if black pawn : yPawn must be 2
            if (pawnPassant.getColor().equals(Figure.Colors.WHITE) && xPawn == 3 && xLastMove == xPawn) {
                //pawn wants to move diagonal to the rigth, figure to eat has to be to the right
                if (pawnPassant.isValidMove(xPawn, yPawn, xPawnMove, yPawnMove, color) && yPawnMove == yPawn + 1 && yLastMove == yPawn + 1) {
                    board[xPawnMove][yPawnMove][1] = pawnPassant;
                    //delete pawn at past location
                    board[xPawn][yPawn][1] = null;
                    //delete piece that the pawn ate
                    board[xLastMove][yLastMove][1] = null;
                    passant = true;
                    //pawn wants to move diagonal to the left, figure to eat has to be to the left
                } else if (pawnPassant.isValidMove(xPawn, yPawn, xPawnMove, yPawnMove, color) && yPawnMove == yPawn - 1 && yLastMove == yPawn - 1) {
                    board[xPawnMove][yPawnMove][1] = pawnPassant;
                    //delete pawn at past location
                    board[xPawn][yPawn][1] = null;
                    //delete piece that the pawn ate
                    board[xLastMove][yLastMove][1] = null;
                    passant = true;
                } else {
                    passant = false;
                }
                //same for black pawn
            } else if (pawnPassant.getColor().equals(Figure.Colors.BLACK) && xPawn == 4 && xLastMove == xPawn) {
                if (pawnPassant.isValidMove(xPawn, yPawn, xPawnMove, yPawnMove, color) && yPawnMove == yPawn + 1 && yLastMove == yPawn + 1) {
                    board[xPawnMove][yPawnMove][1] = pawnPassant;
                    //delete pawn at past location
                    board[xPawn][yPawn][1] = null;
                    //delete piece that the pawn ate
                    board[xLastMove][yLastMove][1] = null;
                    passant = true;

                    //pawn wants to move diagonal to the left, figure to eat has to be to the left
                } else if (pawnPassant.isValidMove(xPawn, yPawn, xPawnMove, yPawnMove, color) && yPawnMove == yPawn - 1 && yLastMove == yPawn - 1) {
                    board[xPawnMove][yPawnMove][1] = pawnPassant;
                    //delete pawn at past location
                    board[xPawn][yPawn][1] = null;
                    //delete piece that the pawn ate
                    board[xLastMove][yLastMove][1] = null;
                    passant = true;
                } else {
                    passant = false;
                }
            } else {
                passant = false;
            }
        } else {
            passant = false;
        }
        return passant;
    }

    /**
     * Method checks if all pieces of the opposition could land on (x,y) with a valid move
     *
     * @param x      - x coordinate
     * @param y      - y coordinate
     * @param figure - We use figure and not King because we use this method to check all figures in the castle method
     * @return true if Figure(King) in (x,y) is in check, false if not
     */
    public boolean check(int x, int y, Figure figure, Player.colors color) {
        boolean check = false;
        //iterate through whole board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                //for every figure or the opposition check if they can land on (x,y)
                if (board[row][col][1] != null) {
                    if (board[row][col][1] != null) {
                        Figure currentFig = (Figure) board[row][col][1];
                        if (figure.getColor() != currentFig.getColor())
                            check = currentFig.isValidMove(row, col, x, y, color) && isPathFree(row,col,x,y);
                        if (check)
                            break;
                    }
                }
            }
        }
        return check;
    }
    public boolean checkNextToKing(int x, int y, King king, Player.colors color){
        boolean checkMate = false;
        check(x, y, king, color);
        if (x + 1 < 8 && y + 1 < 8) {
            if (check(x + 1, y, king, color) && board[y][x + 1][1] != null || check(x - 1, y, king, color) && board[y][x - 1][1] != null
                    || check(x, y + 1, king, color) && board[y + 1][x][1] != null || check(x, y - 1, king, color) && board[y - 1][x][1] != null
                    || check(x + 1, y + 1, king, color) && board[y + 1][x + 1][1] != null
                    || check(x + 1, y - 1, king, color) && board[y - 1][x + 1][1] != null
                    || check(x - 1, y + 1, king, color) && board[y + 1][x - 1][1] != null
                    || check(x - 1, y - 1, king, color) && board[y + 1][x - 1][1] != null) {
                checkMate = true;
            }
        }
        return checkMate;
    }

    /**
     * Method takes the king at coordinates (x,y) and uses the check Method to see if the king can move to a safe position
     * If not, then check mate evaluates to true
     *
     * @param x    -
     * @param y    -
     * @param king -
     * @return true if king cant move to a safe place, else false
     */
    @SuppressWarnings("Duplicates")
    public boolean checkMate(int x, int y, King king, Player.colors color) {//what if someone move in the way

        boolean check = false;
        boolean notBlocked = false;
        boolean checkMate = false;
        //iterate through whole board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                //for every figure or the opposition check if they can land on (x,y)
                if (board[row][col][1] != null) {
                    if (board[row][col][1] != null) {
                        Figure currentFig = (Figure) board[row][col][1];
                        if (king.getColor() != currentFig.getColor())
                            check = currentFig.isValidMove(col, row, x, y, color) && isPathFree(col, row, x, y);
                        if (check) {
                            boolean canBlock = false;
                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    //for every figure of the same color check if they can land on (x,y)
                                    if (board[i][j][1] != null) {
                                        if (board[i][j][1] != null) {
                                            Figure sameColorFig = (Figure) board[row][col][1];
                                            if (king.getColor() == sameColorFig.getColor() && king.getClass() != sameColorFig.getClass())
                                                for (int z = col; z < x; z++)
                                                    notBlocked = isPathFreeModified(row,col,x,y,sameColorFig,color);
//                                            check = currentFig.isValidMove(col, row, y, x, color);
                                        }
                                    }
                                }
                            }
                            if(notBlocked && checkNextToKing(x,y,king,color))
                                checkMate = false;
                            else
                                checkMate = true;
                        }

                    }
                }
            }
        }
        return checkMate;
    }

    public boolean isPathFree(int xCurrent, int yCurrent, int xMove, int yMove) {
        boolean freePath = true;

        Figure fig = (Figure) board[xCurrent][yCurrent][1];
        if(fig == null){
            return freePath = false;
        }
        else if(fig.getClass() == Knight.class){
            return freePath;
        }
        //straight down
        else if (yCurrent == yMove && xCurrent < xMove) {
            xCurrent++;
            for (int i = xCurrent; i < xMove; i++) {
                if (board[i][yCurrent][1] != null) {
                    freePath = false;
                    break;
                }
            }
        }
        //straight up
        else if (yCurrent == yMove && xCurrent > xMove) {
            xCurrent--;
            for (int i = xCurrent; i > xMove; i--) {
                if (board[i][yCurrent][1] != null) {
                    freePath = false;
                    break;
                }
            }
        }
        //left
        else if (yCurrent > yMove && xCurrent == xMove) {
            yCurrent--;
            for (int i = yCurrent; i > yMove; i--) {
                if (board[xCurrent][i][1] != null) {
                    freePath = false;
                    break;
                }
            }
        }
        //right
        else if (yCurrent < yMove && xCurrent == xMove) {
            yCurrent++;
            for (int i = yCurrent; i < yMove; i++) {
                if (board[xCurrent][i][1] != null) {
                    freePath = false;
                    break;
                }
            }
        }
        //left down
        else if (yMove < yCurrent && xMove > xCurrent) {
            if(xCurrent + 1 == xMove && yCurrent - 1 == yMove){
                freePath = true;
                return freePath;
            }

            int j = yCurrent;
            j--;
            xCurrent++;
            for (int i = xCurrent; i < xMove; i++) {
                if (board[i][j][1] != null) {
                    freePath = false;
                    break;
                }
                j++;
                if(j == yMove){
                    break;
                }
            }
        }
        //left up
        else if (yMove < yCurrent && xMove < xCurrent) {
            if(xCurrent - 1 == xMove && yCurrent - 1 == yMove){
                freePath = true;
                return freePath;
            }
            int j = yCurrent;
            xCurrent--;
            j--;
            for (int i = xCurrent; i > xMove; i--) {
                if (board[i][j][1] != null) {
                    freePath = false;
                    break;
                }
                j--;
                if(j == yMove){
                    break;
                }
            }
        }
        //right down
        else if (yMove > yCurrent && xMove > xCurrent) {
            if(xCurrent + 1 == xMove && yCurrent + 1 == yMove){
                freePath = true;
                return freePath;
            }
            int j = yCurrent;
            j++;
            xCurrent++;
            for (int i = xCurrent; i < xMove; i++) {
                if (board[i][j][1] != null) {
                    freePath = false;
                    break;
                }
                j++;
                if(j == yMove){
                    break;
                }
            }
        }
        //right up
        else if (yMove > yCurrent && xMove < xCurrent) {
            if(xCurrent - 1 == xMove && yCurrent + 1 == yMove){
                freePath = true;
                return freePath;
            }

            int j = yCurrent;
            j++;
            xCurrent--;
            for (int i = xCurrent; i > xMove; i--) {
                if (board[i][j][1] != null) {
                    freePath = false;
                    break;
                }
                j++;
                if(j == yMove){
                    break;
                }
            }
        }
        return freePath;

    }
    public boolean move(ArrayList<Object> moveInput){//0:object, 1: xCurrent, 2:yCurrent, 3:xNew, 4:yNew, 5: capture, 6:castlingKing, 7:castlingQueen, 8:enPassant, 9:promotion, 10: PromotionFig,11: player.color, 12: currentPlayer
        //this method is not done
        //will move be checkmate, if made.

        if (moveInput.get(0).equals(false)){
            return false;
        }

        Player currentPlayer = (Player)moveInput.get(12);
        int[] enemyKingCoordinates = findKing(currentPlayer.getColorReversed());
        int enemyKingX = enemyKingCoordinates[0];
        int enemyKingY = enemyKingCoordinates[1];

        boolean validMove = true;
        if((Boolean)moveInput.get(6)){//check and do castleKing
            if (((Player.colors)moveInput.get(11)).toString()  == "WHITE" && !whitecastling){
                validMove = castleKingSide((Player.colors)moveInput.get(11));
                if(validMove){
                    int[] i =findKing((Player.colors)moveInput.get(11));
                    ((King)board[i[0]][i[1]][1]).kingHasMoved();
                    whitecastling = true;
                }
            }


            else if (((Player.colors)moveInput.get(11)).toString() == "BLACK" && !blackcastling){
                validMove = castleKingSide((Player.colors)moveInput.get(11));
                if(validMove){
                    int[] i =findKing((Player.colors)moveInput.get(11));
                    ((King)board[i[0]][i[1]][1]).kingHasMoved();
                    blackcastling = true;
                }
            }
            else if(!(((Player.colors)moveInput.get(11)).toString() == "BLACK" && !blackcastling)||!(((Player.colors)moveInput.get(11)).toString()  == "WHITE" && !whitecastling)){
                validMove = false;
            }
        }

        else if((Boolean)moveInput.get(7)){//check and do castleQueen
            if (((Player.colors)moveInput.get(11)).toString()  == "WHITE" && !whitecastling){
                validMove = castleQueenSide(((Player.colors)moveInput.get(11)));
                if(validMove){
                    int[] i =findKing((Player.colors)moveInput.get(11));
                    ((King)board[i[0]][i[1]][1]).kingHasMoved();
                    whitecastling = true;

                }
            }
            else if (((Player.colors)moveInput.get(11)).toString() == "BLACK" && !blackcastling){
                validMove = castleQueenSide((Player.colors)moveInput.get(11));
                if(validMove){
                    int[] i =findKing((Player.colors)moveInput.get(11));
                    ((King)board[i[0]][i[1]][1]).kingHasMoved();
                    blackcastling = true;
                }
            }
            else if(!(((Player.colors)moveInput.get(11)).toString() == "BLACK" && !blackcastling)||!(((Player.colors)moveInput.get(11)).toString()  == "WHITE" && !whitecastling)){
                validMove = false;
            }
        }
        else if((Boolean)moveInput.get(8)) {//check and do enpassant //change (1) to class input not figure since its only a class given
            validMove = passant((Class)moveInput.get(0), (Integer)moveInput.get(1), (Integer)moveInput.get(2), (Integer)moveInput.get(3), (Integer)moveInput.get(4), (Integer) lastMove[1],(Integer) lastMove[2], (Player.colors)moveInput.get(11));
        }
        else if((Boolean)moveInput.get(9)){//check and do promotion //this needs color too //change to class
              validMove = promote((Class)moveInput.get(0), (Integer)moveInput.get(1), (Integer)moveInput.get(2), (Integer)moveInput.get(3), (Integer)moveInput.get(4), (Class) moveInput.get(10), (Player.colors)moveInput.get(11));
        }
        else if((Boolean) moveInput.get(5)){//case if capture move //this needs color too
            validMove = captureMove((Class)moveInput.get(0), (Integer)moveInput.get(1), (Integer)moveInput.get(2), (Integer)moveInput.get(3), (Integer)moveInput.get(4), (Player.colors)moveInput.get(11), currentPlayer);
        }
        else if ((!(Boolean)(moveInput.get(6)) && !(Boolean)moveInput.get(7) && !(Boolean)moveInput.get(8) && !(Boolean)moveInput.get(9) && !(Boolean)moveInput.get(5))) {//case if normal move //this needs color too
            validMove = normalMove((Class)moveInput.get(0), (Integer)moveInput.get(1), (Integer)moveInput.get(2), (Integer)moveInput.get(3), (Integer)moveInput.get(4), (Player.colors)moveInput.get(11));
        }
        if(check(enemyKingX,enemyKingY,(Figure) this.board[enemyKingX][enemyKingY][1], currentPlayer.getColorReversed())){
            System.out.printf("King is in check\n");
        }


        //implement checkmate (why do i need to input king and where?), when moveOn from game is invalid this gets returned anyway?

        if(!(Boolean)moveInput.get(6) && !(Boolean)moveInput.get(7) && !(Boolean)moveInput.get(8) && !(Boolean)moveInput.get(9)){//change attribute lastmove object newx, newy when there is no special move
            lastMove[0] = moveInput.get(0);//Object this should only change if there was no castling etc.
            lastMove[1] = moveInput.get(3);//x
            lastMove[2] = moveInput.get(4);//y
        }
        if((Boolean)moveInput.get(6) || (Boolean)moveInput.get(7) || (Boolean)moveInput.get(8) || (Boolean)moveInput.get(9)){//when there is a castling, promotion or enPassant set lastmove to 0
            lastMove[0] = null;
            lastMove[1] = null;
            lastMove[2] = null;
       }
        return validMove;
    }

    /**
     *
     * @param newX - x position where figure is to be moved
     * @param newY - y position where figure is to be moved
     * @return - true or false depending if there is a figure in [newX,newY]: False = NOT occupied
     */
    public boolean isOccupied( int newX, int newY){
        if (this.board[newX][newY][1] == null){
            return false;
        }
        return true;
    }

    private boolean normalMove(Class newObject, int xCurrent, int yCurrent, int xNew, int yNew, Player.colors color){// here add outputs that say whats wrong, like there is someone on that field and you didnt say capture
        boolean checkMove = true;
        int[] kingCoordinates = findKing(color);
        int kingX = kingCoordinates[0];
        int kingY = kingCoordinates[1];

        if(!(((Figure)this.board[xCurrent][yCurrent][1]).isValidMove(xCurrent,yCurrent,xNew, yNew, color))){
            checkMove = false;
        }
        if(!(newObject == Knight.class)){//when its not a knight do this
            if(!isPathFree(xCurrent, yCurrent, xNew, yNew)) {// if the path is not free do this
               checkMove = false;
            }
        }
        if(isOccupied(xNew, yNew)){
            String playerColor = color.toString();
            String figureColor = ((Figure)this.board[xNew][yNew][1]).getColor().toString();
            if(playerColor == figureColor){
                System.out.println("Do you want to capture your own piece? Please try to capture an enemy or if you have less courage: an empty field.");
            }
            checkMove = false;
        }
        if (checkMove){
            Figure fig = (Figure) this.board[xCurrent][yCurrent][1];
            this.board[xNew][yNew][1] = this.board[xCurrent][yCurrent][1];//add to new position
            this.board[xCurrent][yCurrent][1] = null;//delete Object from current
            if(newObject == King.class) {
                if (check(xNew, yNew, (King) board[xNew][yNew][1], color)) {
                    this.board[xNew][yNew][1] = null;
                    this.board[xCurrent][yCurrent][1] = fig;
                    checkMove = false;
                    System.out.println("Invalid move with your King, your King would be in check");
                }
            } else {
                if(check(kingX,kingY,(King) board[kingX][kingY][1],color)){
                    this.board[xNew][yNew][1] = null;
                    this.board[xCurrent][yCurrent][1] = fig;
                    checkMove = false;
                    System.out.println("Invalid move, your King would be in check");

                }
            }
        }
        if(checkMove && newObject == King.class){
            ((King)board[xNew][yNew][1]).kingHasMoved();
        }
        if(checkMove && newObject == Rook.class){
            ((Rook)board[xNew][yNew][1]).RookHasMoved();}
        return checkMove;
    }

    public ArrayList<Integer> getFigure(int givenColumn, int xCoordinate, int yCoordinate, Player.colors color, Class figureType, boolean passant){
        ArrayList<Integer> foundFigures = new ArrayList<Integer>();
        int j = 0;
        int k = 7;
        for(int m = 0; m < 8; m++) {
            if (givenColumn != -1) {
                j = givenColumn;
                k = givenColumn;
            }
            for (int n = j; n <= k; n++) {
                if (foundFigures.size() == 3) {
                    break;
                }
                Figure currentFigure = (Figure) board[m][n][1];
                if (currentFigure != null
                        && currentFigure.getClass() == figureType
                        && currentFigure.getColor().toString() == color.toString()){
                    boolean validMove = currentFigure.isValidMove(m,n,xCoordinate,yCoordinate,color);
                    if(validMove&& isPathFree(m, n, xCoordinate, yCoordinate)|| currentFigure.getClass() == Knight.class) {
                        if (currentFigure.getClass() != Pawn.class) {
                            if (validMove) {
                                if (foundFigures.size() < 2) {
                                    foundFigures.add(m);
                                    foundFigures.add(n);
                                } else {
                                    foundFigures.add(m);
                                    System.out.format("There is at least another %s which could do the same move, please clarify\n", currentFigure.getClass().toString());
                                    break;
                                }
                            }
                        }
                        else {
                            boolean capture = isValidPawnCapture(m, n, xCoordinate, yCoordinate, color, passant);
                            boolean diagonal = (m - xCoordinate == 1 || m - xCoordinate == -1) && (n - yCoordinate == 1 || n - yCoordinate == -1);
                            if (!capture && diagonal) {
                                continue;
                            } else if (foundFigures.size() < 2 && capture && diagonal) {
                                foundFigures.add(m);
                                foundFigures.add(n);
                            } else if (foundFigures.size() == 2 && capture && diagonal) {
                                foundFigures.add(m);
                                System.out.format("There is at least another %s which could do the same move, please clarify\n", currentFigure.getClass().toString());
                                break;
                            } else if (validMove) {
                                if (foundFigures.size() < 2) {
                                    foundFigures.add(m);
                                    foundFigures.add(n);
                                } else if (foundFigures.size() == 2) {
                                    foundFigures.add(m);
                                    System.out.format("There is at least another %s which could do the same move, please clarify\n", currentFigure.getClass().toString());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return foundFigures;
    }

    private boolean captureMove(Class newObject, int xCurrent, int yCurrent, int xNew, int yNew, Player.colors currentColor, Player currentPlayer){
        boolean moveCheck = true;
        int[] kingsCoordinates = findKing(currentColor);
        int kingX = kingsCoordinates[0];
        int kingY = kingsCoordinates[1];
        if(!(((Figure)this.board[xCurrent][yCurrent][1]).isValidMove(xCurrent,yCurrent,xNew, yNew, currentColor))){
            moveCheck = false;
        }
        Figure figure = (Figure)this.board[xNew][yNew][1];
        if (figure != null) {
            String playerColor = currentColor.name();
            String figureColor = figure.getColor().name();
            if(isOccupied(xNew, yNew)) {
                if (playerColor == figureColor) {
                    System.out.println("Do you want to capture your own piece? Please try to capture an enemy or if you have less courage: an empty field.");
                }
            }
            if (!isOccupied(xNew, yNew) || figureColor == playerColor) {
                moveCheck = false;
            }
        }
        if(figure == null){
            moveCheck = false;
        }
        if(!(newObject == Knight.class)){//when its not a knight do this
            if(!isPathFree(xCurrent, yCurrent, xNew, yNew)) {// if the path is not free do this
            moveCheck = false;
            }
        }

        if(moveCheck) {//here add the add to dumpster list in Player
            Figure fig = (Figure) this.board[xCurrent][yCurrent][1];
            Figure eaten = (Figure) this.board[xNew][yCurrent][1];
            this.board[xNew][yNew][1] = fig;
            this.board[xCurrent][yCurrent][1] = null;
            boolean eat = true;
            //check if current player move would check mate himself
            if (newObject == King.class) {
                if (check(xNew, yNew, (King) board[xNew][yNew][1], currentColor)) {
                    moveCheck = false;
                    eat = false;
                    //revert capture
                    board[xNew][yNew][1] = eaten;
                    board[xCurrent][yCurrent][1] = fig;
                    System.out.println("Can't capture this figure with your King without sacrificing him\nPlease try another move");
                }
            } else {
                    if (check(kingX, kingY, (King) board[kingX][kingY][1], currentColor)) {
                        moveCheck = false;
                        eat = false;
                        //revert capture
                        board[xNew][yNew][1] = eaten;
                        board[xCurrent][yCurrent][1] = fig;
                        System.out.println("Can't capture this figure without sacrificing your king\nPlease input another move");
                    }

                }
                if (eat) {
                    currentPlayer.setEatenPieces(eaten);//add to eatenpieces
                }
            }
        if(moveCheck && newObject == King.class){
            ((King)board[xCurrent][yCurrent][1]).kingHasMoved();
        }
        if(moveCheck && newObject == Rook.class){
            ((Rook)board[xCurrent][yCurrent][1]).RookHasMoved();}
        return moveCheck;
    }

    @SuppressWarnings("Duplicates")
    private boolean isValidPawnCapture(int xCurrent, int yCurrent, int xNew, int yNew, Player.colors currentColor, boolean passant) {
        boolean capture = false;
        Figure figure = (Figure) this.board[xNew][yNew][1];
        if (figure != null) {
            if (isOccupied(xNew, yNew) && (figure.getColor().name() != currentColor.name())) {
                capture = true;
            }
        }
        else if (passant) {
            capture = true;
        }
        return capture;
    }
    @SuppressWarnings("Duplicates")
    public boolean isPathFreeModified(int xCurrent, int yCurrent, int xMove, int yMove, Figure figureToBlock, Player.colors color) {
        boolean canBlock = true;
        //straight down
        if (yCurrent == yMove && xCurrent < xMove) {
            xCurrent++;
            for (int i = xCurrent; i < xMove; i++) {
                if (board[i][yCurrent][1] == null && figureToBlock.isValidMove(yCurrent,i,yMove,xMove,color)) {
                    canBlock = false;
                    break;
                }
            }
        }
        //straight up
        else if (yCurrent == yMove && xCurrent > xMove) {
            xCurrent--;
            for (int i = xCurrent; i > xMove; i--) {
                if (board[i][yCurrent][1] == null && figureToBlock.isValidMove(yCurrent,i,yMove,xMove,color)) {
                    canBlock = false;
                    break;
                }
            }
        }
        //left
        else if (yCurrent > yMove && xCurrent == xMove) {
            yCurrent--;
            for (int i = yCurrent; i > yMove; i--) {
                if (board[xCurrent][i][1] == null && figureToBlock.isValidMove(i,xCurrent,yMove,xMove,color)) {
                    canBlock = true;
                    break;
                }
            }
        }
        //right
        else if (yCurrent < yMove && xCurrent == xMove) {
            yCurrent++;
            for (int i = yCurrent; i < yMove; i++) {
                if (board[xCurrent][i][1] == null && figureToBlock.isValidMove(i,xCurrent,yMove,xMove,color)) {
                    canBlock = true;
                    break;
                }
            }
        }
        //left down
        else if (yMove < yCurrent && xMove > xCurrent) {
            int j = yCurrent;
            j--;
            xCurrent++;
            for (int i = xCurrent; i < xMove; i++) {
                if (board[i][j][1] == null && figureToBlock.isValidMove(j,i,yMove,xMove,color)) {
                    canBlock = true;
                    break;
                }
                j++;
                if(j == yMove){
                    break;
                }
            }
        }
        //left up
        else if (yMove < yCurrent && xMove < xCurrent) {
            int j = yCurrent;
            j--;
            xCurrent--;
            for (int i = xCurrent; i > xMove; i--) {
                if (board[i][j][1] == null && figureToBlock.isValidMove(j,i,yMove,xMove,color)) {
                    canBlock = true;
                    break;
                }
                j--;
                if(j == yMove){
                    break;
                }
            }
        }
        //right down
        else if (yMove > yCurrent && xMove > xCurrent) {
            int j = yCurrent;
            j++;
            xCurrent++;
            for (int i = xCurrent; i < xMove; i++) {
                if (board[i][j][1] == null && figureToBlock.isValidMove(j,i,yMove,xMove,color)) {
                    canBlock = true;
                    break;
                }
                j++;
                if(j == yMove){
                    break;
                }
            }
        }
        //right up
        else if (yMove > yCurrent && xMove < xCurrent) {
            int j = yCurrent;
            j++;
            xCurrent--;
            for (int i = xCurrent; i > xMove; i--) {
                if (board[i][j][1] == null && figureToBlock.isValidMove(j,i,yMove,xMove,color)) {
                    canBlock = true;
                    break;
                }
                j++;
                if(j == yMove){
                    break;
                }
            }
        }
        return canBlock;

    }


    public int[] findKing(Player.colors color){
        int[] kingCoordinates = new int[2];
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                if(board[x][y][1] != null && board[x][y][1].getClass() == King.class){
                    King king = (King) board[x][y][1];
                    if(king.getColor().toString() == color.toString()) {
                        kingCoordinates[0] = x;
                        kingCoordinates[1] = y;
                        return kingCoordinates;
                    }
                }
            }
        }
        return kingCoordinates;
    }
    public boolean getCheckmate(){
        return this.checkmate;
    }
}