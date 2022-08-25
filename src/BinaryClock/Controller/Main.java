package BinaryClock.Controller;

import BinaryClock.View.FrameEasyBinaryClock;
import BinaryClock.View.FrameRealBinaryClock;

import java.awt.*;
import BinaryClock.View.*;

import javax.swing.*;

public class Main {
    private JFrame frameClock;

    public static void main(String[] args) {
        Main mainFrame = new Main();
    }

    public Main() throws HeadlessException {
        frameClock = new FrameEasyBinaryClock();
        frameClock = new FrameRealBinaryClock();
    }
}
