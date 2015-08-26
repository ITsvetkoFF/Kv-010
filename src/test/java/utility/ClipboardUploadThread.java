package utility;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Created by Tanya on 02.11.2014.
 * Refactoring by Valery on 20.08.2015.
 *
 * This class loads clipboard on a new thread.
 */
public class ClipboardUploadThread extends Thread {

    public ClipboardUploadThread(String filePath) {
        super(new Runner(filePath));
    }
}


class Runner implements Runnable {
    private String filePath;

    public Runner(String filePath) {
        this.filePath = filePath;
    }

    public void run() {
        try {
            Thread.sleep(2000);
            Robot robot = new Robot();
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}