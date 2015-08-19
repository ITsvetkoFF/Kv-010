package utility;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Olya on 10/26/14.
 */
public class ExcelUtils {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
    public static void setExcelFile(String Path, String SheetName) throws Exception{
        try{
        // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
        // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e){
            throw (e);
        }
    }

    //test data provider
    public static Object[][] getTableArray(String Path, String SheetName) throws Exception {
        Object[][] tabArray = null;
        try {
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int startRow = 1;
            int startCol = 1;
            int ci,cj;
            int totalRows = ExcelWSheet.getLastRowNum();
            // you can write a function as well to get Column count
            int totalCols = getColumnsNumber();
            tabArray=new String[totalRows][totalCols - 1];
            ci=0;
            for (int i=startRow;i<=totalRows;i++, ci++) {
                cj=0;
                for (int j=startCol;j<totalCols;j++, cj++){
                    tabArray[ci][cj]=getCellData(i,j);
                    System.out.println(tabArray[ci][cj]);
                }
            }
            return tabArray;
        } catch (IOException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return tabArray;
    }

    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
    public static String getCellData(int RowNum, int ColNum){
        String result = "";
        try{
            Row = ExcelWSheet.getRow(RowNum);
            if(Row == null){
                return "";
            }
            Cell = Row.getCell(ColNum);
            if(Cell == null){
                return "";
            }
            result =  Cell.getStringCellValue();
        }catch (Exception e){
            System.out.println("Something wrong with data from Excel sheet");
            e.printStackTrace();
        }
        return result;
    }

    //This method is to write in the Excel cell, Row num and Col num are the parameters
    public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception{
        try{
            Row  = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
            ExcelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static int getColumnsNumber() throws Exception {
        int colNum = 0;
        while (!getCellData(0, colNum).isEmpty()) {
            colNum++;
        }
        return colNum;
    }
}
