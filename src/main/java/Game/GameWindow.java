package Game;

import javax.swing.*;

public class GameWindow {
    private JFrame gameWindow;
    public GameWindow(String gameWindowName,GamePanel gamePanel){
        gameWindow=new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setTitle(gameWindowName);
        gameWindow.setSize(1920,1080);
        gameWindow.add(gamePanel);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

    }
}
