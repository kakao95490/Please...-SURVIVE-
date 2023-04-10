package Game;

import javax.swing.*;

public class GameWindow {
    private JFrame gameWindow;
    public GameWindow(String gameWindowName,GamePanel gamePanel){
        gameWindow=new JFrame();

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setTitle(gameWindowName);
        gameWindow.add(gamePanel);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setResizable(false);
        gameWindow.pack();
        gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameWindow.setVisible(true);


    }
}
