import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Breakout extends JPanel {
    // instance variables
    private int width, height;
    private int ballSpeed, paddleSpeed;
    private int bricksBroken;
    private int score;
    private boolean isGameOver;
    private Ball ball; // gives access to ball anywhere in the class
    private Paddle paddle;
    private Color brickColor;
    private ArrayList<Brick> bricks;

    // constructor takes in two parameters for the size of the JPanel
    // uses the methods from the JPanel class to create a visible
    // panel for the other objects of the game to appear on
    public Breakout(int w, int h) {
        width = w;
        height = h;
        bricksBroken = 0;
        paddleSpeed = 3;
        ballSpeed = 1;

        setSize(width, height);
        setLocation(200, 50); // x = 200, y = 50
        setBackground(Color.RED);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        ball = new Ball(100, 100, this); // makes an instance of the class
        paddle = new Paddle(50, 465, this);
        // brick = new Brick(0, 0, this);
        // brick2 = new Brick(40, 0, this);

        // listen for the key pressing
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }
            public void keyPressed(KeyEvent e) {
                paddle.keyPressed(e);
            }
            
            public void keyReleased(KeyEvent e) {
                paddle.keyReleased(e);
            }
        });

        // must have to make sure breakout is focused
        setFocusable(true);
        createGrid();
    }

    /**
     * Displays graphics on the JPanel
     *
     * @param g is the declaration of the Graphics class, where all the methods
     *          needed to get graphics onto the JPanel
     */

    @Override
    public void paint(Graphics g) {
        // need to invoke paint in order to see the JPanel
        super.paint(g);
        paddle.paint(g);
        ball.paint(g); // draws the ball on the panel
        if (bricks.size() == 0) {
            endGame();
        }
        if (isGameOver) {
            String endText = bricks.size() == 0 ? "Game over! You win!" : "Game over! You lose.";
            g.setFont(new Font("Arial Rounded MT", Font.BOLD, 25));
            g.drawString(endText, 75, 150);
        }
        for (int x = bricks.size() - 1; x >= 0; x--) {
            bricks.get(x).paint(g);
            if (bricks.get(x).collision()) {
                bricksBroken++;
                score += 10;
                ball.setYA(ballSpeed);
                bricks.remove(x);
                if (bricksBroken % 10 == 0) ballSpeed++;
                if (bricksBroken % 15 == 0) paddleSpeed++;
            }
        }
    }

    /**
     * This method will get movement in the game
     */
    public void move() {
        if (!isGameOver) {
        paddle.movePaddle();
        ball.moveBall();
        }
    }

    /* -------------------- The Brick Grid -------------------- */
    public ArrayList<Brick> createGrid() {
        bricks = new ArrayList<Brick>();
        for (int y = 0; y <= 75; y += 25) {
            for (int x = 0; x <= 360; x += 40) {
                if (y == 0) {
                    brickColor = Color.CYAN;
                }
                else if (y == 25) {
                    brickColor = Color.MAGENTA;
                }
                else if (y == 50) {
                    brickColor = Color.ORANGE;
                }
                else {
                    brickColor = Color.PINK;
                }
                bricks.add(new Brick(x, y, brickColor, this));
            }
        }
        return bricks;
    }

    /* ------------------- Getters and Setters -------------------- */
    public int getBallSpeed() {
        return ballSpeed;
    }

    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public int getScore() {
        return score;
    }

    public void endGame() {
        isGameOver = true;
    }

}