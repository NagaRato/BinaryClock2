package BinaryClock.View;

import javax.swing.*;
import java.awt.*;

public class PaneDigit extends JPanel {
    private boolean isOn;

    public PaneDigit(int id) {
        setMinimumSize(new Dimension(100, 100));
        add(new JLabel(id + ""));
        isOn = false;
        repaint();
    }

    protected void paintComponent(Graphics graph)
    {
        super.paintComponent(graph);
        Graphics2D g = (Graphics2D) graph;
        if (isOn) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.LIGHT_GRAY);
        }
        g.setStroke(new BasicStroke(3));
        int diameter = 90;
        g.fillOval(getWidth()/2-diameter/2, getHeight()/2-diameter/2, diameter, diameter);
    }

    public void setLight(boolean b) {
        isOn = b;
        repaint();
    }
}
