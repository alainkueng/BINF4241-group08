import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
        System.out.print("Initial state:\t");
        printGame();
        Dice dice = new Dice();
        while (this.winner == null) {
            int random_number = dice.dice();
            Player current_player = players.get(this.current);
            System.out.format("%s rolls %d:\t", current_player.name, random_number);
            int to_move = checkNumber(random_number, current_player);
            printGame();
            current_player.move(to_move);


            checkLast(current_player);
            nextPlayer();
        }
    }

    // create Board with squares
    public void createBoard() {

        while (this.board_size < 2) {
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
            if (board_size < 2) {
                System.out.println("Don't be silly, have you ever played Snakes and Ladders in real life?");
            }
        }
        Square square = new FirstSquare(1, this);
        squares.add(square);
        for (int i = 2; i < board_size; i++) {
            square = new Square(i, this);
            squares.add(square); // creates Squares in list
        }
        square = new LastSquare(board_size, this);
        squares.add(square);
        if (board_size >= 6) {
            int special_number = board_size / 4;
            int snakes_number = special_number / 2;
            int ladders_number = special_number / 2;
            for (int i = 0; i < snakes_number; i++) {
                int random_position = ThreadLocalRandom.current().nextInt(2, board_size - 1);
                int random_partner = ThreadLocalRandom.current().nextInt(1, random_position - 1);
                Square square_to_transform = squares.get(random_position);
                Square new_partner = squares.get(random_partner);
                int iterations = 0;
                while (square_to_transform.partner || new_partner.partner) {
                    while (square_to_transform.partner) {
                        random_position = ThreadLocalRandom.current().nextInt(2, board_size - 1);
                        square_to_transform = squares.get(random_position);
                        random_partner = ThreadLocalRandom.current().nextInt(1, random_position);
                        new_partner = squares.get(random_partner);
                    }
                    while (iterations < board_size && new_partner.partner) {
                        random_partner = ThreadLocalRandom.current().nextInt(1, random_position );
                        new_partner = squares.get(random_partner);
                        iterations++;
                    }
                }
                Snake snake = new Snake(random_position + 1, this, random_partner + 1);
                squares.set(random_position, snake);
            }
            for (int i = 0; i < ladders_number; i++) {
                int random_position = ThreadLocalRandom.current().nextInt(1, board_size - 3);
                int random_partner = ThreadLocalRandom.current().nextInt(random_position + 1, board_size - 2);
                Square square_to_transform = squares.get(random_position);
                Square new_partner = squares.get(random_partner);
                int iterations = 0;
                while (square_to_transform.partner || new_partner.partner) {
                    while (square_to_transform.partner) {
                        random_position = ThreadLocalRandom.current().nextInt(1, board_size - 3);
                        square_to_transform = squares.get(random_position);
                        random_partner = ThreadLocalRandom.current().nextInt(random_position + 1, board_size - 2);
                        new_partner = squares.get(random_partner);
                    }
                    while (iterations < board_size && new_partner.partner) {
                        random_partner = ThreadLocalRandom.current().nextInt(random_position + 1, board_size - 2);
                        new_partner = squares.get(random_partner);
                        iterations++;
                    }
                }
                Ladder ladder = new Ladder(random_position + 1, this, random_partner + 1);
                squares.set(random_position, ladder);
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
        while(numPlayer < 1 || numPlayer > 4){
            System.out.println("This game is for 1 to 4 players, please adjust: ");
            while(!numPlayers.hasNextInt()){
                System.out.println("Wrong input, please enter number of players: ");
            }
            numPlayer = numPlayers.nextInt();
        }
        for (int i = 1; i <= numPlayer; i++) {
            Player user = new Player(squares.get(0));
            user.setName(i, this.players);
            while (user.getName().equals("") && players.isEmpty()) {
                System.out.println("Please input a Name");
                user.setName(i, this.players);

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
            System.out.print("Final state:\t");
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




        public void printGame() {
            for (int i = 0; i < squares.size(); i++) {
                Square current_square = squares.get(i);
                List current_player_list = squares.get(i).player_list;
                if (current_player_list.size() != 0) {
                    System.out.print("[" + (i + 1));
                    for (int j = 0; j < current_player_list.size(); j++) {
                            System.out.print("<" + current_square.player_list.get(j).name + ">");
                    }
                    System.out.print("]");
                    continue;
                }
                else if(current_square.getClass() == Snake.class){
                    Snake snake = (Snake) squares.get(i);
                    System.out.print("[" + snake.new_position + "<-" + (i + 1) + "]");
                    continue;
                }
                else if(current_square.getClass() == Ladder.class){
                    Ladder ladder = (Ladder) squares.get(i);
                    System.out.print("[" + (i+1) + "->" + ladder.new_position + "]" );
                    continue;
                }
                    System.out.print("[" + (i + 1) + "]");
            }
            System.out.println("");
        }
    }




