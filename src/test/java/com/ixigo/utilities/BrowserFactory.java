package com.ixigo.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ixigo.pages.BaseClass;

public class BrowserFactory extends BaseClass {
	
	public static WebDriver driver;
	
	public static WebDriver startApplication(String Browsername , String Appurl) {
		
		if(Browsername.equals("Chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			
			driver = new ChromeDriver();
		}
		else if(Browsername.equals("IE")) {
			
			System.out.println("We have not configured any other browser other than chrome");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(Appurl);
		return driver;
	}

}
