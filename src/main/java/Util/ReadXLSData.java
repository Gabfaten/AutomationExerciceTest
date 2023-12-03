package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXLSData {		
	
	public static String[][] getDataFromExcel(String sheetName) throws IOException {	
	  
	  String excelPath = ".\\src\\test\\resources\\testData\\TestData.xlsx";
	  File file = new File(excelPath);	
	  FileInputStream fis = new FileInputStream(file);
	  
	  XSSFWorkbook wb = new XSSFWorkbook(fis);
	  XSSFSheet sheet = wb.getSheet(sheetName);
	  
	  int rows = sheet.getLastRowNum() + 1;
	  int columns = sheet.getRow(0).getLastCellNum();
	    
	  System.out.println("Total rows"  + rows); 
	  System.out.println("Total column"  + columns); 
	  
	    DataFormatter format = new DataFormatter();	
	    
		Object data[][] =  new String[rows][columns];		

		 for (int i = 0; i < rows; i++) {
		        XSSFRow row = sheet.getRow(i);

		        if (row != null) {  // Check if the row is null
		            for (int k = 0; k < columns; k++) {
		                XSSFCell cell = row.getCell(k);

		                if (cell != null) {  // Check if the cell is null
		                    data[i][k] = format.formatCellValue(cell);
		                } else {
		                    // Handle the case where the cell is null (e.g., provide a default value)
		                    data[i][k] = "Cell is null";
		                }
		            }
		        } else {
		            // Handle the case where the row is null (e.g., provide a default value for the entire row)
		            Arrays.fill(data[i], "Row is null");
		        }
		    }
		
		return  (String[][]) data;	
	
	}
 
	
}
