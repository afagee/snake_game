import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static jdk.nashorn.internal.objects.Global.print;

public class GetMouse implements MouseListener {
    GamePanel gp;
    public static int mouseX, mouseY;
    public GetMouse(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (gp.game_start) {
            if (mouseX >= 400 && mouseX <= 500 && mouseY >= 363 && mouseY <= 404) {
                gp.game_start = false;
                gp.inGame = true;
                gp.game_start_sound.Stop();
            }
            if (mouseX >= 400 && mouseX <= 500 && mouseY >= 465 && mouseY <= 502) {
                System.exit(0);
            }
        } else if (!gp.inGame) {
            if (mouseX >= 400 && mouseX <= 500 && mouseY >= 363 && mouseY <= 404) {
                gp.initGame();
                gp.inGame = true;
            }
            if (mouseX >= 400 && mouseX <= 500 && mouseY >= 465 && mouseY <= 502) {
                System.exit(0);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
