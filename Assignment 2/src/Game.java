import java.util.Scanner;

public class Game {
    private Board gameboard;
    private Logic logic;
    private Player white;
    private Player black;

    Game(){
        this.gameboard = new Board();
        this.logic = new Logic();
        Scanner player1 = new Scanner(System.in);
        boolean Scan_string_check_1 = false;
        String whitename = null;
        while (!Scan_string_check_1){
            System.out.println("Please enter playername for the white side");
            whitename = player1.nextLine();
            if (whitename != null && whitename.matches("^[a-zA-Z]*$")){
                Scan_string_check_1 = true;
                break;
            }
            else{System.out.println("Try Again");
        }
        }
        Scanner player2 = new Scanner(System.in);
        boolean Scan_string_check_2 = false;
        String blackname= null;
        while (!Scan_string_check_2){
            System.out.println("Please enter playername for the black side");
            blackname = player2.nextLine();
            if (blackname!= null && blackname.matches("^[a-zA-Z]*$")){
                Scan_string_check_2 = true;
                break;
            }
            else {
                System.out.println("Try Again");}

            }
        this.white = new Player(whitename, Player.colors.WHITE);
        this.black = new Player(blackname, Player.colors.BLACK);
}
}

