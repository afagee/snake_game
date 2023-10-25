public class Snake {
    GamePanel gp;
    int[] x, y;
    boolean leftDirection = false;
    boolean rightDirection = true;
    boolean upDirection = false;
    boolean downDirection = false;
    boolean bite = false;

    public Snake(GamePanel gp) {
        this.gp = gp;
        this.x = new int[gp.ALL_DOTS];
        this.y = new int[gp.ALL_DOTS];
    }

    public void move() {
        for (int z = gp.dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        if (leftDirection) {
            x[0] -= gp.DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += gp.DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= gp.DOT_SIZE;
        }

        if (downDirection) {
            y[0] += gp.DOT_SIZE;
        }
        if (y[0] == gp.B_HEIGHT) {
            y[0] = 0;
        }

        if (y[0] < 0) {
            y[0] = gp.B_HEIGHT - gp.DOT_SIZE;
        }

        if (x[0] == gp.B_WIDTH) {
            x[0] = 0;
        }

        if (x[0] < 0) {
            x[0] = gp.B_WIDTH - gp.DOT_SIZE;
        }
    }

    public void checkCollision() {

        for (int z = gp.dots; z > 1; z--) {

            if ((x[0] == x[z]) && (y[0] == y[z])) {
                gp.inGame = false;
                gp.game_over_sound.Start();
                break;
            }
        }

        if (!gp.inGame) {
            gp.timer.stop();
        }
    }
}
