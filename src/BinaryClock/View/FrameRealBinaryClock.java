package BinaryClock.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Observable;

public class FrameRealBinaryClock extends JFrame implements ActionListener {
    private final ArrayList<PaneDigit> digits = new ArrayList<>();

    public FrameRealBinaryClock() {
        Timer time = new Timer(1000, this);
        setTitle("Real");
        setMinimumSize(new Dimension(600, 600));
        setLayout(new GridLayout(4,4));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(0,0);
        for (int i = 0; i < 16; i++) {
            PaneDigit pane = new PaneDigit(i);
            add(pane);
            digits.add(pane);
        }
        time.start();
        pack();
        setVisible(true);
    }

    private String addLeadingCharsInFront(String value, int numberOfLeading) {
        while (value.length() < numberOfLeading) {
            value = "0" + value;
        }
        return value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double seconds = LocalTime.now().getSecond() + LocalTime.now().getMinute()*60 + LocalTime.now().getHour()*3600;
        double b = seconds / 86400.0 * 65536.0;
        String a = addLeadingCharsInFront(Integer.toBinaryString((int) b), 16);
        setTitle("Real " + LocalTime.now() + " " + a);
        for (int i = 0; i < digits.size(); i++) {
            digits.get(i).setLight(a.split("")[i].equals("1"));
        }
    }
}