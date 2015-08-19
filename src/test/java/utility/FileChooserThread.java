package utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by ykadytc on 24.10.2014.
 */
public class FileChooserThread extends Thread {
    public FileChooserThread(String filePath) {
        super(new FileRunner(filePath));
    }
}

class FileRunner implements Runnable {
        private String filePath;

        public FileRunner(String filePath) {
            this.filePath = filePath;
        }

        public void run() {
            try {
                Thread.sleep(2000);
                Robot robot = new Robot(); // input simulation class
                for (char c : filePath.toCharArray()){
                    if (c == ':') {
                        robot.keyPress(KeyEvent.VK_SHIFT);
                        robot.keyPress(KeyEvent.VK_SEMICOLON);
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                    }
                    else if (c == '\\') {
                        robot.keyPress(KeyEvent.VK_BACK_SLASH);
                    }
                    else if (c == '/') {
                        robot.keyPress(KeyEvent.VK_SLASH);
                    }
                    else if (c == '.') {
                        robot.keyPress(KeyEvent.VK_PERIOD);
                    }
                    else if (c == ' ') {
                        robot.keyPress(KeyEvent.VK_SPACE);
                    }
                    else {
                        KeyStroke key = KeyStroke.getKeyStroke("pressed " + Character.toUpperCase(c));
                        if (null != key) {
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
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }