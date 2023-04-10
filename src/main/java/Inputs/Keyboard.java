package Inputs;
import Game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.Directions.*;

public class Keyboard implements KeyListener {
    private final GamePanel gamePanel;
    public Keyboard(GamePanel gamePanel){
        this.gamePanel=gamePanel;

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q -> gamePanel.player.setDirection(LEFT);
            case KeyEvent.VK_D -> gamePanel.player.setDirection(RIGHT);
            case KeyEvent.VK_Z -> gamePanel.player.setDirection(UP);
            case KeyEvent.VK_S -> gamePanel.player.setDirection(DOWN);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q, KeyEvent.VK_D, KeyEvent.VK_Z, KeyEvent.VK_S -> gamePanel.player.setMoving(false);
        }

    }

}
