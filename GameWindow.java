import javax.swing.JFrame;

public class GameWindow extends JFrame {
    // Declartion of instance variables/objects
    private int width, height;
    private static String title;

    // constructor is first thing called when a new Paddle is created
    // the constructor is where any instance variables are given values
    // initializes instance variables
    public GameWindow(String t, int w, int h) throws InterruptedException {
        title = t;
        width = w;
        height = h;
        
        // methods to make a JFrame/window
        setSize(width, height);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // gives absolute positioning
        setResizable(false);

        // Construct a Breakout panel
        Breakout out = new Breakout(400, 500);
        ScoreBoard scoreOut = new ScoreBoard(out);

        add(out);
        add(scoreOut);

        setVisible(true);
        // this while loop will be the game loop that will run
        while (true) {
            out.move();
            out.repaint();
            scoreOut.repaint();
            Thread.sleep(8);
        }
    }
}