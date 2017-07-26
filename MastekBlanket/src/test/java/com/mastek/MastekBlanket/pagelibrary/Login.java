package com.mastek.MastekBlanket.pagelibrary;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	
	WebDriver driver;
	
	static Logger log=Logger.getLogger(Login.class.getName());
	
	
	By username=By.id("txtUsername");
	
	By btnLogin=By.id("btnLogin");
	
	By password=By.id("txtPassword");
	
	public Login(WebDriver driver){
		this.driver=driver;
	}
	
	/**
	 * This method will enter username on login page
	 * @param uname
	 */
	public void enterUsername(String username) {
		log.info("Entering Username");
		driver.findElement(this.username).sendKeys(username);
		
	}

	/**
	 * This method will enter password on login page
	 * @param pass
	 */
	public void enterPassword(String password) {
		log.info("Entering Password");
		driver.findElement(this.password).sendKeys(password);
	}
	
	/**
	 * This method will click on login button
	 */
	public void clickLogin() {
		log.info("Clocking Login Button");
		driver.findElement(this.btnLogin).click();
	}
	
	/**
	 * This Method will Login to Application
	 */
	public void loginToApplication() throws InterruptedException{
		enterUsername("admin");
		enterPassword("admin");
		clickLogin();
		Thread.sleep(5000);
	}
}
