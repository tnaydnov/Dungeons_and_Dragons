import java.util.Scanner;

public class CLI {
    private Manager manager;
    private Board board;


    public CLI(Manager manager) {
        this.manager = manager;
        manager.setCli(this);
    }

    public void printBoard() {
        for (int i = 0; i <board.getWidth() ; i++) {
            System.out.println();
            for (int j = 0; j < board.getLength(); j++) {
                System.out.print(board.getTile(i,j).getChar());
            }
        }
        System.out.println();
    }
    public boolean legalInput(String input) {
        if (!this.manager.playerExists()) {
            return input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7");
        }
        else {
            return input.equals("w") || input.equals("s") || input.equals("a") || input.equals("d") || input.equals("e") || input.equals("q");
        }
    }

    public void acceptInput(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!this.legalInput(input)) {
            input = sc.nextLine();
        }

        char c = input.charAt(0);
        this.manager.acceptInput(c);
    }

    public void print(String msg){
        System.out.println(msg);
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public char choose_player(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!(legalInput(input))){
            System.out.println(input);
            input = sc.nextLine();
        }
        return input.charAt(0);
    }
}
