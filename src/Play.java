import javax.swing.*;
import java.awt.*;

public class Play extends JFrame {
    public Play() {
        add(new GamePanel());
        setResizable(false);
        pack();
        setTitle("Snake Game");
        setLocationRelativeTo(null);// giữa màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Play();
            ex.setVisible(true);
        });
    }
}