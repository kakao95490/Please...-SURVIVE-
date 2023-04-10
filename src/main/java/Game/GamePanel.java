package Game;

import Inputs.*;
import caractere.Player;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;

public class GamePanel extends JPanel {
    private final static Dimension screenSize = new Dimension(1920, 1080);
    public Player player;

    public GamePanel() {
        Mouse mouseInputs = new Mouse(this);
        addKeyListener(new Keyboard(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        this.setPanelSize(screenSize);
        this.player = new Player();
    }


    private void setPanelSize(Dimension size) {
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.player.reload(g);

    }
}