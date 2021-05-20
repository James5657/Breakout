import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;

public class ScoreBoard extends JPanel {
    private Breakout breakout;

    public ScoreBoard(Breakout out) {
        setSize(200, 25);
        setLayout(null);
        
        breakout = out;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Arial Rounded MT", Font.BOLD, 18));
        g.drawString("Score: " + breakout.getScore(), 50, 20);
    }
}