package utility;

/**
 * Created by ykadytc on 11.11.2014.
 */

import java.io.File;

public class FileSystem {
    private File[] listOfFiles;

    public FileSystem(String folderName) {
        this.getListOfFiles(folderName);
    }

    // get list of files from "folderName"
    private void getListOfFiles(String folderName) {
        File folder = new File(folderName);
        this.listOfFiles = folder.listFiles();
        return;
    }

    //looks for a file by name in folder list
    public Boolean isImageFilePresentInFolder(String fileName) {
        for (int i = 0; i < listOfFiles.length; i++) {
            if (fileName.equals(listOfFiles[i].getName())) {
                return true;
            }
        }
        return false;
    }

    // builds file system
    public static void main(String[] args) {
        FileSystem fs = new FileSystem(Constant.Path_ImagesLocalFolder);

        for (int i = 0; i < fs.listOfFiles.length; i++) {
            if (fs.listOfFiles[i].isFile()) {
                System.out.println("File " + fs.listOfFiles[i].getName());
            } else if (fs.listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + fs.listOfFiles[i].getName());
            }
        }
    }
}
