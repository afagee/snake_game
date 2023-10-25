

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GetKey extends KeyAdapter {
    GamePanel gp;
    public GetKey(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        boolean move = false;
        if ((key == KeyEvent.VK_LEFT) && (!gp.snake.rightDirection)) {
            gp.snake.leftDirection = true;
            gp.snake.upDirection = false;
            gp.snake.downDirection = false;
            move = true;
        }

        if ((key == KeyEvent.VK_RIGHT) && (!gp.snake.leftDirection)) {
            gp.snake.rightDirection = true;
            gp.snake.upDirection = false;
            gp.snake.downDirection = false;
            move = true;
        }

        if ((key == KeyEvent.VK_UP) && (!gp.snake.downDirection)) {
            gp.snake.upDirection = true;
            gp.snake.rightDirection = false;
            gp.snake.leftDirection = false;
            move = true;
        }

        if ((key == KeyEvent.VK_DOWN) && (!gp.snake.upDirection)) {
            gp.snake.downDirection = true;
            gp.snake.rightDirection = false;
            gp.snake.leftDirection = false;
            move = true;
        }

        if (move) {
            gp.loadImages();
        }
    }
}
