import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Manager manager = new Manager();
        manager.readFile(args[0]);
        //manager.readFile("C:\\Users\\tomer\\Desktop\\levels_dir");
        manager.runGame();
    }
}
