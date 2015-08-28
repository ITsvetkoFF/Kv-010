package utility;

/**
 * Created by ykadytc on 11.11.2014.
 * Refactoring by Valery on 20.08.2015.
 *
 * This class builds file system.
 */

import java.io.File;

public class FileSystem {
    private File[] listOfFiles;

    public FileSystem(String folderName) {
        this.getListOfFiles(folderName);
    }

    /**
     * This method get list of files from "folderName".
     *
     * @param folderName is the name of folder from you need take a files list.
     */
    private void getListOfFiles(String folderName) {
        File folder = new File(folderName);
        this.listOfFiles = folder.listFiles();
    }

    /**
     * This method looks for a file by name in folder list.
     *
     * @param fileName is the name of the file.
     * @return true if the sought-for file is in folder.
     */
    public Boolean isImageFilePresentInFolder(String fileName) {
        for (int i = 0; i < listOfFiles.length; i++) {
            if (fileName.equals(listOfFiles[i].getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method identifies files and directories in the specified directory and outputs result.
     *
     * Path_ImagesLocalFolder is the specified directory.
     */
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
