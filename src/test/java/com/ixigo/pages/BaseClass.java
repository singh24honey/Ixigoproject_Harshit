package com.ixigo.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.ixigo.utilities.BrowserFactory;
import com.ixigo.utilities.ConfigDataProvider;
import com.ixigo.utilities.ExcelDataProvider;




public class BaseClass {

	public WebDriver driver;
	public  static ExcelDataProvider excel;
	public  ConfigDataProvider config;
	public static ExtentReports report;
	public static ExtentTest Logger;
	
	
	@BeforeSuite
	
	public void setup() throws Exception {
		excel = new ExcelDataProvider();
		config= new ConfigDataProvider();
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/Ixigo.html"));
		report = new ExtentReports();
		report.attachReporter(extent);
	}
	
	@BeforeClass
	
	public void startApplication() {
		
		driver = BrowserFactory.startApplication(config.getBrowser(), config.getUrl());
		
	}
	
	@AfterClass
	
	public void closeApplication() {
		driver.close();
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardownMethod(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			Logger.fail("Test Failed " +result.getThrowable().getMessage());
			System.out.println("adding code for feature 1");
			System.out.println("adding code");
			System.out.println("adding in the develop branch");
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			Logger.pass("Test Passed " );
			System.out.println("adding in the develop branch");
			System.out.println("adding in the develop branch");
			System.out.println("adding in the develop branch");
			System.out.println("adding in the develop branch");
		}
		report.flush();
	}
}
