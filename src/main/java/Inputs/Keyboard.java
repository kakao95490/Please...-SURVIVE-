package Inputs;
import Game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private GamePanel gamePanel;
    public Keyboard(GamePanel gamePanel){
        this.gamePanel=gamePanel;

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                gamePanel.setDx(-5);
                break;
            case KeyEvent.VK_RIGHT:
                gamePanel.setDx(5);
                break;
            case KeyEvent.VK_UP:
                gamePanel.setDy(-5);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.setDy(5);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
