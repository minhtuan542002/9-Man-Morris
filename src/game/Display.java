import javax.swing.*;

public class Display {
    private JFrame window = null;
    public void init(){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Nine Man's Morris");

        GamePanel display = new GamePanel();
        window.add(display);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        display.setGameThread();
    }
}
