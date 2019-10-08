import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;



public class Game {

    private Player winner;
    public int current;
    public int board_width;
    public int board_height;
    public int board_size;
    public int numPlayer;
    public List<Player> players;
    public List<Square> squares;


    //Constructor
    Game() {

        this.players = new ArrayList<Player>(4);
        this.winner = null;
        this.current = 0;
        this.squares = new ArrayList<Square>();
        createBoard();
        addPlayer(); // anzahl spieler vorher herausfinden wie auf Blatt beschrieben?
        System.out.print("Initial state: ");
        printGame();
        Dice dice = new Dice();
        while (this.winner == null) {
            int random_number = dice.dice();
            Player current_player = players.get(this.current);
            System.out.format("%s rolls %d: ", current_player.name, random_number);
            int to_move = checkNumber(random_number, current_player);
            current_player.move(to_move);
            printGame();

            checkLast(current_player);
            nextPlayer();
        }
    }

    // create Board with squares
    public void createBoard() {

        System.out.println("Please input height: ");
        Scanner s = new Scanner(System.in);

        while (!s.hasNextInt()) {
            System.out.println("Input is not a number, please input height: ");
            s.nextLine();
        }
        int h_size = s.nextInt();
        this.board_height = h_size;  // for board_size

        System.out.println("Please input width: ");
        while (!s.hasNextInt()) {
            System.out.println("Input is not a number, please input width: ");
            s.nextLine();
        }
        int w_size = s.nextInt();
        this.board_width = w_size;
        board_size = this.board_height * this.board_width;
        Square square = new FirstSquare(1, this);
        squares.add(square);
        for (int i = 2; i < board_size; i++) {
            square = new Square(i, this);
            squares.add(square); // creates Squares in list
        }
        square = new LastSquare(board_size, this);
        squares.add(square);
         if (board_size>=6){
             int numsnakesladders = Math.round(board_size/4);
             int snakes = Math.round(numsnakesladders/2);
             int ladders = Math.round(numsnakesladders/2);
             for (int i=0;i < snakes; i++){//start
                 int randomint = ThreadLocalRandom.current().nextInt(2,board_size-1);;
                 while (squares.get(randomint).partner){
                     randomint = ThreadLocalRandom.current().nextInt(2,board_size-1);
                 int position = squares.get(randomint).position;
                 Snake snake = new Snake(position, this, 0); //new snake
                 squares.set(position-1, snake);//delete out of list
                 snake.settrue(snake);
                 while (snake.new_position==0){//check if there is a free position
                     int random = ThreadLocalRandom.current().nextInt(1, snake.position-1);
                     if (squares.get(random).partner == false) {
                         squares.get(random).partner = true;
                         snake.new_position = random;

                     }
                     }
                 }
             }
             for (int i = 0;i < ladders;i++){//start
                 int randmint = ThreadLocalRandom.current().nextInt(1, board_size-3);;
                 while(squares.get(randmint).partner){
                     randmint = ThreadLocalRandom.current().nextInt(1, board_size-3);}//könnte man ändern zum liste/kein partner konflikt zu verhindern
                 int position = squares.get(randmint).position;
                 Ladder ladder = new Ladder(position, this, 0);//new ladder
                 squares.set(position-1, ladder);//delete out of list
                 ladder.settrue(ladder);
                 while (ladder.new_position == 0){ //check if there is a free position
                     int rando = ThreadLocalRandom.current().nextInt(ladder.position-1, board_size-2);
                     if (squares.get(rando).partner == false) {
                         squares.get(rando).partner = true;
                         ladder.new_position = rando;
                                 }

                         }
                     }
                 }
                 }






    //add players to the board + muss noch jedem player den ersten Square zuteilen
    public void addPlayer() {

        Scanner numPlayers = new Scanner(System.in);
        System.out.println("Number of players?");
        while(!numPlayers.hasNextInt()){
            System.out.println("Wrong input, please enter number of players: ");
            numPlayers.nextLine();
        }
        numPlayer = numPlayers.nextInt();
        for (int i = 1; i <= numPlayer; i++) {
            Player user = new Player(squares.get(0));
            user.setName(i);
            while (user.getName().equals("") && players.isEmpty()) {
                System.out.println("Please input a Name");
                user.setName(i);

            }
            if (numPlayer < 2) {
                System.out.println("One is the loneliest number, but sure play by yourself");
            }
            players.add(user);
            user.square.enter(user);
        }
        System.out.println("\nGame starting now!");

    }


    public Square getSquare(int square_number) {
        return squares.get(square_number - 1);
    }

    //keeps track of whose turn it is
    public void nextPlayer() {
        this.current = (this.current += 1) % numPlayer;
    }

    public void checkLast(Player current_player) {
        if (current_player.square.last) {
            this.winner = current_player;
            System.out.print("Final state: ");
            printGame();
            System.out.format("%s wins!", current_player.name);
        }
    }

    public int checkNumber(int to_move, Player current_player) {
        int current_position = current_player.square.position;

        if (to_move + current_position > board_size) {
            int offset = (current_position + to_move) % board_size;
            to_move = (board_size - offset) - current_position;

        }
        return to_move;

    }


//    public void addSnakesAndLadders(){
//        int i = 0;
//        int j = 0;
//        Snake snake = new Snake(i, );
//        Ladder ladder = new Ladder(j,);
//    }

        public void printGame() {
            for (int i = 0; i < squares.size(); i++) {
                //square 1
                if (squares.get(i).player_list.size() != 0) {
                    System.out.print("[" + (i + 1));
                    for (int j = 0; j < squares.get(i).player_list.size(); j++) {
                            System.out.print("<" + squares.get(i).player_list.get(j).name + ">");
                    }
                    System.out.print("]");
                } else{
                    System.out.print("[" + (i + 1) + "]");
                }
            }
            System.out.println("");
        }
            
            
//            else if(!squares.get(i).isOccupied() && !squares.get(i).hasSnake && !squares.get(i).hasLadder){
//                System.out.println(" [" + i + "] ");
//
//                //is empty but has a snake
//            } else if (!squares.get(i).isOccupied() && squares.get(i).hasSnake){
//                System.out.println(" [" + i + " <- " + "Snake get position" + "]");
//            }
//            // is empty but has ladder
//            else if(!squares.get(i).isOccupied() && squares.get(i).hasLadder){
//                System.out.println(" [" + i + " -> " + "Ladder.getnewPosition" + "]");
//            }
//
//            else if(squares.get(i).isOccupied()){
//                System.out.println(" [" + i + "< " + squares.get(i).player_list.get(i).getName() + " >" + "]");
//            }
//
    }




