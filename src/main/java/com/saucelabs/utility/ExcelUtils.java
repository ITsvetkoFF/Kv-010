package com.saucelabs.utility;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Olya on 10/26/14.
 * Refactoring by Vadym on 08/20/15.
 *
 * This class can read from Excel file. Also we can read and write in cell in our Excel file
 */
public class ExcelUtils {
    private static XSSFSheet excelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell cell;
    private static XSSFRow row;

    /**
     * This method is to set the File path and to open the Excel file, Pass Excel path and Sheet name as Arguments to this method
     *
     * @param path to file Excel
     * @param sheetName is name in book ExcelBook
     * @throws Exception
     */
    public static void setExcelFile(String path, String sheetName) throws Exception {
        // Open the Excel file
        FileInputStream ExcelFile = new FileInputStream(path);
        // Access the required test data sheet
        ExcelWBook = new XSSFWorkbook(ExcelFile);
        excelWSheet = ExcelWBook.getSheet(sheetName);
    }

    /**
     * Test data provider
     *
     * @param path to file Excel
     * @param sheetName is name in book ExcelBook
     * @return multi array with data drom Excel file
     * @throws Exception if getCellData throws Exception
     */
    public static Object[][] getTableArray(String path, String sheetName) throws Exception {
        Object[][] tabArray = null;
        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = ExcelWBook.getSheet(sheetName);
            int startRow = 1;
            int startCol = 1;
            int ci, cj;
            int totalRows = excelWSheet.getLastRowNum();
            // you can write a function as well to get Column count
            int totalCols = getLastColumnNum();
            tabArray = new String[totalRows][totalCols - 1];
            ci = 0;
            for (int i = startRow; i <= totalRows; i++, ci++) {
                cj = 0;
                for (int j = startCol; j < totalCols; j++, cj++) {
                    tabArray[ci][cj] = getCellData(i, j);
                    System.out.println(tabArray[ci][cj]);
                }
            }
            return tabArray;
        } catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return tabArray;
    }

    /**
     * This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
     *
     * @param rowNum is from row we want to read the data
     * @param colNum is from column we want to read the data
     * @return data from this cell with coordinate (rowNum and colNum)
     * @throws Exception if ExcelBook or sheet are not initialized by method setExcelFile
     */
    public static String getCellData(int rowNum, int colNum) throws Exception{
        String result = "";
            if(ExcelWBook == null || excelWSheet == null){
                throw new Exception("You need setExcelFile for begin!");
            }
            row = excelWSheet.getRow(rowNum);
            if (row == null) {
                return "";
            }
            cell = row.getCell(colNum);
            if (cell == null) {
                return "";
            }
            result = cell.getStringCellValue();
        return result;
    }

    /**
     * This method is to write in the Excel cell, Row num and Col num are the parameters
     *
     * @param data is what we want to put in cell in our Excel file
     * @param RowNum is where we can put the data, which row
     * @param ColNum is where we can put the data, which column
     */
    public static void setCellData(String data, int RowNum, int ColNum) {
        try {
            if(ExcelWBook == null || excelWSheet == null){
                throw new Exception("You need setExcelFile for begin!");
            }
            row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(data);
            } else {
                cell.setCellValue(data);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
            ExcelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Just count how many column with data in our Excel file, need for other methods.
     *
     * @return number of last column in Excel file
     * @throws Exception from method getCellData
     */
    private static int getLastColumnNum() throws Exception {
        int colNum = 0;
        while (!getCellData(0, colNum).isEmpty()) {
            colNum++;
        }
        return colNum;
    }
}
