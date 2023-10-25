import java.util.Random;

public class Food {
    GamePanel gp;
    boolean isEat = false;
    int apple_x;
    int apple_y;
    public Food(GamePanel gp) {
        this.gp = gp;
    }
    public void locateApple() {
        boolean check = true;
        while (check) {
            check = false;
            Random random = new Random();
            int r = random.nextInt((gp.B_WIDTH / gp.DOT_SIZE) - 1);
            apple_x = ((r * gp.DOT_SIZE));
            r = random.nextInt((gp.B_HEIGHT / gp.DOT_SIZE) - 1);
            apple_y = ((r * gp.DOT_SIZE));

            for (int z = 0; z < gp.dots; z++) {
                if ((gp.snake.x[z] == apple_x) && (gp.snake.y[z] == apple_y)) {
                    check = true;
                    break;
                }
            }
        }
    }

    public void checkApple() {
        if ((gp.snake.x[0] == apple_x) && (gp.snake.y[0] == apple_y)) {
            gp.dots++;
            if (gp.dots % 4 == 0 && gp.DELAY > 50) {
                gp.DELAY -= 20;
                gp.timer.setDelay(gp.DELAY);
            }
            gp.eat_sound.Start();
            isEat = true;
            locateApple();
        }
    }
}
