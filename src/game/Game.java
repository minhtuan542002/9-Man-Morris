
public class Game {
    private Board board =null;
    private Display display =null;

    public Game(){
        board = new Board();
        display = new Display();

    }

    public void run(){
        display.init();
    }
}
