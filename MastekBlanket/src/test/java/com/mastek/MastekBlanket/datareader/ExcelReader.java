package com.mastek.MastekBlanket.datareader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	public String path;
	FileInputStream fInput;
	XSSFWorkbook workbook;
	XSSFSheet worksheet;
	XSSFRow row;
	XSSFCell cell;
	
	public ExcelReader(String path) {
		this.path=path;
		
		try {
			fInput=new FileInputStream(path);
			workbook=new XSSFWorkbook(fInput);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public String getCellData(String sheet,String colName, int rowNum) {
		
		int col_Num=0;
		int index = workbook.getSheetIndex(sheet);
		worksheet=workbook.getSheetAt(index);
		
		row=worksheet.getRow(0);
		
		for(int rowCount = 0;rowCount<row.getLastCellNum();rowCount++){
			if(row.getCell(rowCount).getStringCellValue().equalsIgnoreCase(colName)){
				col_Num=rowCount;
			}
		}
		row=worksheet.getRow(rowNum-1);
		cell = row.getCell(col_Num);
		if(cell.getCellType()==Cell.CELL_TYPE_STRING){
			return cell.getStringCellValue();
		}else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
			return String.valueOf(cell.getNumericCellValue());
		}else if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
			return String.valueOf(cell.getBooleanCellValue());
		}else if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
			return "";
		}
		
		return null;
		
	}
	
	public static void main(String[] args) {
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\Login.xlsx";
		ExcelReader obj=new ExcelReader(path);
		System.out.println(obj.getCellData("Login", "username", 3));
		
	}

}
