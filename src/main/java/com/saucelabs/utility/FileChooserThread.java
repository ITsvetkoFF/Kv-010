package com.saucelabs.utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by ykadytc on 24.10.2014.
 * Refactoring by Vadym on 08/20/15.
 *
 * This class has a path to the file and simply writes this path where the cursor is on the screen.
 * But the path to file need to be English, your keyboard layout should be English too, when you run the program.
 * Be careful - CapsLock should be turned off and other options on keyboard.
 */
class FileChooserThread implements Runnable {

    private String filePath;

    public FileChooserThread(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method run in another Thread and just type path where cursor is on the screen.
     */
    @Override
    public void run() {
        try {
            Robot robot = new Robot(); // input simulation class
            for (char c : filePath.toCharArray()) {
                // special characters
                if (c == ':') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_SEMICOLON);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else if (c == '\\') {
                    robot.keyPress(KeyEvent.VK_BACK_SLASH);
                } else if (c == '/') {
                    robot.keyPress(KeyEvent.VK_SLASH);
                } else if (c == '.') {
                    robot.keyPress(KeyEvent.VK_PERIOD);
                } else if (c == ' ') {
                    robot.keyPress(KeyEvent.VK_SPACE);
                } else {
                    KeyStroke key = KeyStroke.getKeyStroke("pressed " + Character.toUpperCase(c));
                    if (key != null) {
                        // should only have to worry about case with standard characters
                        if (Character.isUpperCase(c)) {
                            robot.keyPress(KeyEvent.VK_SHIFT);
                        }
                        robot.keyPress(key.getKeyCode());
                        robot.keyRelease(key.getKeyCode());
                        if (Character.isUpperCase(c)) {
                            robot.keyRelease(KeyEvent.VK_SHIFT);
                        }
                    }
                }
            }
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (InterruptedException | AWTException e) {
            e.printStackTrace();
        }
    }
}