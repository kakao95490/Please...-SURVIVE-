package Game;

import Inputs.*;


import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class GamePanel extends JPanel{
    private int dx=0, dy=0;

    public GamePanel() {
        Mouse mouseInputs=new Mouse(this);
        addKeyListener(new Keyboard(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    public void setDx(int dx) {
        this.dx += dx;

    }

    public void setDy(int dy) {
        this.dy += dy;

    }

    public void setPos(int dx,int dy){
        this.dx = dx;
        this.dy = dy;

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        upptdateRectangle();
        g.fillRect(dx,dy,256,256);

    }

    private void upptdateRectangle() {

    }


}
