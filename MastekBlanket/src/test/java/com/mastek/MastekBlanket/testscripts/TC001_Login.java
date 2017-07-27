package com.mastek.MastekBlanket.testscripts;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mastek.MastekBlanket.datareader.ExcelReader;
import com.mastek.MastekBlanket.pagelibrary.Login;
import com.mastek.MastekBlanket.testbase.TestBase;


public class TC001_Login extends TestBase {
	ExcelReader er=null;
	String xlFilePath=System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\Login.xlsx";
	String sheetName="Login";
	Login login;
	
	@BeforeClass
	public void setUp() throws IOException{
		init();
	}
	
	public Object[][] testData(String xlFilePath, String sheetName) throws Exception {
		Object[][] excelData=null;
		er=new ExcelReader(xlFilePath);
		int rows=er.getRowCount(sheetName);
		int columns=er.getColCount(sheetName);
		
		excelData=new Object[rows-1][columns];
		
		for(int rowCount=1;rowCount<rows;rowCount++){
			for(int colCount=0;colCount<columns;colCount++){
				excelData[rowCount-1][colCount]=er.getCellData(sheetName, colCount, rowCount);
			}
		}
		
		return excelData;
		
	}
	
	
	@DataProvider(name="loginData")
	public Object[][] userFormData() throws Exception{
		
		Object[][] data=testData(xlFilePath, sheetName);
		
		return data;
		
	}
		
	
	@Test(priority=0,description="Login with valid data",dataProvider="loginData")
	public void loginTest(String execute,String testCase, String username, String password, String error) throws InterruptedException{
		if(execute.equalsIgnoreCase("N")){
			throw new SkipException("Skipping execution for "+testCase);
		}
		login=new Login(driver);
		login.loginToApplication(username, password);
		Thread.sleep(5000);
	}

	
	
	@AfterClass
	public void quitBrowser(){
		//driver.quit();
	}
	
	
	
	
	
}
