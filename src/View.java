
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dmitry
 */
public class View extends JComponent implements MouseListener{
    
    private JFrame window;
    
    private final int WIDTH = 640, HEIGHT = 480;
    
    private final int x = WIDTH/2, y = HEIGHT/2, width = 100, height = 10, radius = 100;
    private final double restingAngle = Math.toRadians(25);
    private final double maxAngle = Math.toRadians(160);
    private double angle = restingAngle;
    private final double rate = 0.1;    
    
    private  boolean isFading = true;
    private double fadeValue = 255;
    private Color fadeColor = new Color(0, 0, 0, (int)fadeValue);
    
    public View()
    {
        window = new JFrame("Switch");
        window.add(this);
        
        window.setVisible(true);
        
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        
        window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.pack();
        
        this.addMouseListener(this);
    }
    
    public boolean isFading()
    {
        return isFading;
    }
    
    public void fade()
    {
        fadeValue -= 0.5;
        if (fadeValue < 0)
        {
            fadeValue = 0;
            isFading = false;
        }
        fadeColor = new Color(fadeColor.getRed(), fadeColor.getGreen(), fadeColor.getBlue(), (int)fadeValue);
    }
    
    public void move()
    {
        angle -= Math.toRadians(rate);
        if (angle < restingAngle)
        {
            angle = restingAngle;
        }
    }
    
    public boolean isPressed()
    {
        return angle > restingAngle;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        if (isFading)
        {
            g.setColor(fadeColor);
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }
        else
        {
           //g.clearRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.RED);
            g.drawLine(x, y, x+(int)(radius*Math.cos(angle)), y-(int)(radius*Math.sin(angle)));
            g.fillOval(x+(int)(radius*Math.cos(angle)-2), y-(int)(radius*Math.sin(angle))-2, 4, 4);

            g.setColor(Color.BLACK);
            g.fillRect(x-width/2, y-height/2, width, height);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() >= x-radius+Math.cos(angle)*radius&& e.getX() <= x+radius+Math.cos(angle)*radius)
        {
            if (e.getY() >= y-radius && e.getY() <= y)
            {
                angle = maxAngle;
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
