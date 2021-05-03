package com.ixigo.utilities;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ixigo.pages.BaseClass;

public class Helper extends BaseClass {
	
	static WebDriver driver;
	
	public static  void WebDriverWait(WebDriver driver,WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
public static  void ElementToBeClickableWait(WebDriver driver,WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	public static String getCurrentDateTime() {
		DateFormat customformat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentdate = new Date();
		return customformat.format(currentdate);
		
	}
	
	public static void javaScriptExecutorSctollIntoView(WebDriver driver,WebElement ele) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
	}

}
