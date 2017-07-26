package com.mastek.MastekBlanket.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBase {
	
	public static Properties Repository=new Properties();
	public File file;
	public FileInputStream fileInput;
	public WebDriver driver;
	
	Properties properties;
	
	public void init() throws IOException{
		loadPropertiesFile();
		selectBrowser(Repository.getProperty("browser"));
		driver.get(Repository.getProperty("url"));

	}

	public void loadPropertiesFile() throws IOException{
		file=new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\mastek\\MastekBlanket\\config\\config.properties");
		fileInput=new FileInputStream(file);
		Repository.load(fileInput);
	}
	
		
	public WebDriver selectBrowser(String browser) {
		
		if(browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("IE")){
			driver=new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		
		return driver;
		
	}

}
