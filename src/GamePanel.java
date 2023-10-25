import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    final int B_WIDTH = 900;
    final int B_HEIGHT = 900;
    final int DOT_SIZE = 25;
    final int ALL_DOTS = 1000;
    int dots;
    int DELAY = 150;
    boolean inGame = false, game_start = true;
    Timer timer;
    Image ghost, food_image, head_0, head_1, blood, back_ground;
    Snake snake = new Snake(this);
    Food food = new Food(this);
    Sound game_start_sound = new Sound(this, "src/sound/gameMusic.wav");
    Sound eat_sound = new Sound(this, "src/sound/TakeItems.wav");
    Sound game_over_sound = new Sound(this, "src/sound/gameOver.wav");
    public GamePanel(){
        addKeyListener(new GetKey(this));
        addMouseListener(new GetMouse(this));
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    public void initGame() {
        game_start_sound.Loop();
        dots = 1;
        snake.x[0] = 0;
        snake.y[0] = 0;
        food.locateApple();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void loadImages() {
        ImageIcon ii = new ImageIcon("src/resources/bg.png");
        back_ground = ii.getImage();

        ii = new ImageIcon("src/resources/ghost.png");
        ghost = ii.getImage();

        ii = new ImageIcon("src/resources/apple.png");
        food_image = ii.getImage();

        ii = new ImageIcon("src/resources/blood.png");
        blood = ii.getImage();

        ImageIcon iih_0, iih_1;
        iih_0 = null;
        iih_1 = null;
        if (snake.leftDirection) {
            iih_0 = new ImageIcon("src/resources/head_left_0.png");
            iih_1 = new ImageIcon("src/resources/head_left_1.png");
        }
        if (snake.rightDirection) {
            iih_0 = new ImageIcon("src/resources/head_right_0.png");
            iih_1 = new ImageIcon("src/resources/head_right_1.png");
        }
        if (snake.upDirection) {
            iih_0 = new ImageIcon("src/resources/head_up_0.png");
            iih_1 = new ImageIcon("src/resources/head_up_1.png");
        }
        if (snake.downDirection) {
            iih_0 = new ImageIcon("src/resources/head_down_0.png");
            iih_1 = new ImageIcon("src/resources/head_down_1.png");
        }
        if (iih_0 != null) {
            head_0 = iih_0.getImage();
        }
        if (iih_1 != null) {
            head_1 = iih_1.getImage();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        g.drawImage(back_ground, 0, 0,this);

        if (inGame) {
            String msg = "Scores: " + dots;
            Font small = new Font("Helvetica", Font.BOLD, 20);
            FontMetrics metr = getFontMetrics(small);

            g.setColor(Color.BLACK);
            g.setFont(small);
            g.drawString(msg, 0, 20);

            g.drawImage(food_image, food.apple_x, food.apple_y, DOT_SIZE, DOT_SIZE,this);

            if (food.isEat) {
                g.drawImage(blood, snake.x[0] - 13, snake.y[0] - 13, 2 * DOT_SIZE, 2 * DOT_SIZE, this);
                food.isEat = false;
            }
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    if (snake.bite) {
                        g.drawImage(head_0, snake.x[z], snake.y[z], DOT_SIZE, DOT_SIZE, this);
                    } else {
                        g.drawImage(head_1, snake.x[z], snake.y[z], DOT_SIZE, DOT_SIZE, this);
                    }
                    snake.bite=!snake.bite;
                } else {
                    g.drawImage(ghost, snake.x[z], snake.y[z], DOT_SIZE, DOT_SIZE, this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            if (game_start) {
                gameStart(g);
            } else {
                gameOver(g);
            }
        }
    }
    private void gameStart(Graphics g) {
        String theSnake = "The Snake";
        String play = "Play";
        String quit = "Quit";

        g.setColor(new Color(0,0,0,200));
        g.fillRect(0,0, B_WIDTH, B_HEIGHT);

        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.setColor(Color.WHITE);
        g.drawString(theSnake, getXTextCenter(theSnake, g), 300);


        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(play, getXTextCenter(play, g), 400);


        g.drawString(quit, getXTextCenter(quit, g), 500);
    }
    private void gameOver(Graphics g) {
        String game_over = "Game Over";
        String play = "Play Again";
        String quit = "Quit";

        g.setColor(new Color(0,0,0,200));
        g.fillRect(0,0, B_WIDTH, B_HEIGHT);

        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.setColor(Color.RED);
        g.drawString(game_over, getXTextCenter(game_over, g), 300);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(play, getXTextCenter(play, g), 400);


        g.drawString(quit, getXTextCenter(quit, g), 500);
    }

    public int  getXTextCenter(String text, Graphics g)
    {
        FontMetrics metrics = g.getFontMetrics();
        int x = (B_WIDTH - metrics.stringWidth(text)) / 2;
        return x;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            food.checkApple();
            snake.checkCollision();
            snake.move();
        }
        repaint();
    }
}
