package com.mastek.MastekBlanket.datareader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet worksheet;
	public XSSFRow row;
	public XSSFCell cell;
	public String xlFilePath;
	

	public ExcelReader(String xlFilePath) throws Exception {
		this.xlFilePath=xlFilePath;
		fis=new FileInputStream(xlFilePath);
		workbook=new XSSFWorkbook(fis);
		fis.close();
	}

	

	public String getCellData(String sheetName,String colName, int rowNum) {	
		
		try {
			int col_Num = -1;
			worksheet=workbook.getSheet(sheetName);
			row=worksheet.getRow(0);
			
			for(int rowCount = 0;rowCount<row.getLastCellNum();rowCount++){
				if(row.getCell(rowCount).getStringCellValue().trim().equals(colName.trim())){
					col_Num=rowCount;
				}
			}
			row=worksheet.getRow(rowNum-1);
			cell = row.getCell(col_Num);			
			if(cell.getCellTypeEnum()==CellType.STRING){
				return cell.getStringCellValue();
			}else if(cell.getCellTypeEnum()==CellType.NUMERIC||cell.getCellTypeEnum()==CellType.FORMULA){
				String cellValue=String.valueOf(cell.getNumericCellValue());
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					DateFormat df=new SimpleDateFormat("dd/MM/yy");
					Date date=cell.getDateCellValue();
					cellValue=df.format(date);
				}
				return cellValue;
			}else if(cell.getCellTypeEnum()==CellType.BLANK){
				return "";
			}else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "Row "+rowNum+" OR Colomn "+colName+" does not exist in Excel";
		}
	}
		
	
	
	
	public String getCellData(String sheetName,int colNum, int rowNum) {
		
		try {
			worksheet=workbook.getSheet(sheetName);
			row=worksheet.getRow(rowNum);
			cell = row.getCell(colNum);			
			if(cell.getCellTypeEnum()==CellType.STRING){
				return cell.getStringCellValue();
			}else if(cell.getCellTypeEnum()==CellType.NUMERIC||cell.getCellTypeEnum()==CellType.FORMULA){
				String cellValue=String.valueOf(cell.getNumericCellValue());
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					DateFormat df=new SimpleDateFormat("dd/MM/yy");
					Date date=cell.getDateCellValue();
					cellValue=df.format(date);
				}
				return cellValue;
			}else if(cell.getCellTypeEnum()==CellType.BLANK){
				return "";
			}else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "Row "+rowNum+" OR Colomn "+colNum+" does not exist in Excel";
		}
		
	}

		

	public int getRowCount(String sheetName) {
			worksheet=workbook.getSheet(sheetName);
			int number=worksheet.getLastRowNum()+1;
			return number;	
	}
	
	public int getColCount(String sheetName) {
			worksheet=workbook.getSheet(sheetName);
			row=worksheet.getRow(0);
			return row.getLastCellNum();	
	}

}
