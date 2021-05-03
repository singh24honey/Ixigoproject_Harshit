package com.ixigo.utilities;

import java.io.File;
import java.io.FileInputStream;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	XSSFWorkbook wb;
	
	public ExcelDataProvider() throws Exception {
		
File src = new File("./TestData/TestData.xlsx");
		
		try {
			FileInputStream fis= new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		}catch(Exception e) {
		throw new Exception("Unable to read excel file"+ e.getMessage());
		}
	}
	
	public String getStringData(int Sheetindex,int row , int column) {
		return wb.getSheetAt(Sheetindex).getRow(row).getCell(column).getStringCellValue();
		
	}
	public String getStringData(String Sheetname,int row , int column) {
		return wb.getSheet(Sheetname).getRow(row).getCell(column).getStringCellValue();
		
	}
	
	
	public double getNumericData(int Sheetindex,int row , int column) {
		return wb.getSheetAt(Sheetindex).getRow(row).getCell(column).getNumericCellValue();

	}

}
