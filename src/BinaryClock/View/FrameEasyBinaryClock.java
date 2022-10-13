package BinaryClock.View;

import BinaryClock.Controller.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;

public class FrameEasyBinaryClock extends JFrame implements ActionListener {

    private final Main main;
    private JPanel paneHour = new JPanel();
    private ArrayList<PaneDigit> digitsHour = new ArrayList<>();
    private JPanel paneMinutes = new JPanel();
    private ArrayList<PaneDigit> digitsMinute= new ArrayList<>();
    private JPanel paneSeconds = new JPanel();
    private ArrayList<PaneDigit> digitsSecond = new ArrayList<>();

    public FrameEasyBinaryClock(Main main) throws HeadlessException {
        Timer time = new Timer(500, this);
        setMinimumSize(new Dimension(600, 870));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(800, 0);
        this.main = main;

        paneSeconds.setLayout(new BoxLayout(paneSeconds, BoxLayout.PAGE_AXIS));
        paneMinutes.setLayout(new BoxLayout(paneMinutes, BoxLayout.PAGE_AXIS));
        paneHour.setLayout(new BoxLayout(paneHour, BoxLayout.PAGE_AXIS));
        paneHour.add(new JLabel());
        for (int i = 0; i < 17; i++) {
            PaneDigit digit = new PaneDigit(i);
            if (i < 5) {
                paneHour.add(digit);
                digitsHour.add(digit);
            } else if (i < 11) {
                paneMinutes.add(digit);
                digitsMinute.add(digit);
            } else {
                paneSeconds.add(digit);
                digitsSecond.add(digit);
            }
        }
        add(paneHour);
        add(paneMinutes);
        add(paneSeconds);

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
        LocalTime time = LocalTime.now();
        setTitle("Simpel " + time);
        boolean[] booleanArray = new boolean[17];
        String hours = addLeadingCharsInFront(Integer.toBinaryString(time.getHour()) + "", 5);
        String minutes = addLeadingCharsInFront(Integer.toBinaryString(time.getMinute()) + "", 6);
        String seconds = addLeadingCharsInFront(Integer.toBinaryString(time.getSecond()) + "", 6);

        for (int i = 0; i < digitsHour.size(); i++) {
            if (hours.length() > i) {
                boolean value = hours.split("")[i].equals("1");
                digitsHour.get(i).setLight(value);
                booleanArray[i] = value;
            } else {
                digitsHour.get(i).setLight(false);
                booleanArray[i] = false;
            }
        }
        for (int i = 0; i < digitsMinute.size(); i++) {
            if (minutes.length() > i) {
                boolean value = minutes.split("")[i].equals("1");
                digitsMinute.get(i).setLight(value);
                booleanArray[i + 5] = value;
            } else {
                digitsMinute.get(i).setLight(false);
                booleanArray[i + 5] = false;
            }
        }
        for (int i = 0; i < digitsSecond.size(); i++) {
            if (seconds.length() > i) {
                boolean value = seconds.split("")[i].equals("1");
                digitsSecond.get(i).setLight(value);
                booleanArray[i + 11] = value;
            } else {
                digitsSecond.get(i).setLight(false);
                booleanArray[i + 11] = false;
            }
        }
        main.setCurrentLineFromEasyClock(booleanArray);
    }
}