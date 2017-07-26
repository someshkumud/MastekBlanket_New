package com.mastek.MastekBlanket.customlistener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.mastek.MastekBlanket.testbase.TestBase;


public class Listener extends TestBase implements ITestListener {


	public void onStart(ITestResult result) {
		Reporter.log("Execution started for "+result.getMethod().getMethodName()+" at: "+result.getStartMillis());
		
	}

	public void onTestFailure(ITestResult result) {
		if(!result.isSuccess()){
			String userDirectory=System.getProperty("user.dir");
			String location="\\src\\test\\java\\com\\mastek\\MastekBlanket\\screenshots";
			String failureImageFileName=userDirectory+location+new SimpleDateFormat("mm-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+"-"+result.getMethod().getMethodName()+".png";
			File scrFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(failureImageFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Reporter.log("<a href=\""+failureImageFileName+"\"><img src=\"file:///"+failureImageFileName+"\" alt=\"\""+ "height='100' width='100'/>"+"<br />");
			Reporter.setCurrentTestResult(null);
			Reporter.log(result.getName()+"---Test Method Failed\n");
			
		}
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
