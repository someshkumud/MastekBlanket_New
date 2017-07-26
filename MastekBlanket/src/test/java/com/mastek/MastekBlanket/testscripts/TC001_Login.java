package com.mastek.MastekBlanket.testscripts;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mastek.MastekBlanket.pagelibrary.Login;
import com.mastek.MastekBlanket.testbase.TestBase;


public class TC001_Login extends TestBase {

	Login login;
	
	@BeforeClass
	public void setUp() throws IOException{
		init();
	}
	
	
	@Test(priority=0,enabled=true,description="Login with valid data")
	public void login() throws InterruptedException{
		login=new Login(driver);
		login.loginToApplication();
		Thread.sleep(5000);
	}

	
	
	@AfterClass
	public void quitBrowser(){
		//closeBrowser();
	}
	
	
	
	
	
}
