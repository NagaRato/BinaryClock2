package BinaryClock.Controller;

import BinaryClock.View.FrameEasyBinaryClock;
import BinaryClock.View.FrameRealBinaryClock;

import java.awt.*;
import java.io.*;
import java.util.Arrays;

public class Main {

    private boolean[] booleanArrayFromEasyClock = new boolean[0];
    private boolean[] booleanArrayFromRealClock = new boolean[0];

    public static void main(String[] args) {new Main();}

    public Main() throws HeadlessException {
        new FrameEasyBinaryClock(this);
        new FrameRealBinaryClock(this);
    }

    public void setCurrentLineFromRealClock(boolean[] booleanArray) {
        booleanArrayFromRealClock = booleanArray;
        printCurrectLine();
    }

    public void setCurrentLineFromEasyClock(boolean[] booleanArray) {
        booleanArrayFromEasyClock = booleanArray;
        printCurrectLine();
    }

    private void printCurrectLine() {
//        for (boolean b: booleanArrayFromRealClock) {
//            System.out.print(b + " ");
//        }
//        System.out.print("- ");
//        for (boolean b: booleanArrayFromEasyClock) {
//            System.out.print(b + " ");
//        }
        System.out.println("");

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("yourfile.txt");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (boolean b : booleanArrayFromRealClock) {
                objectOutputStream.writeBoolean(b);
            }
            for (boolean b : booleanArrayFromEasyClock) {
                objectOutputStream.writeBoolean(b);
            }
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
