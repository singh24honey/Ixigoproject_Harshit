package com.ixigo.pages;


import java.util.List;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ixigo.utilities.Helper;



public class IxigoFlightSearchPage extends BaseClass{

	WebDriver driver;
	
	
	@FindBy(xpath="//div[@class='orgn u-ib u-v-align-bottom u-text-left']//input")
	private WebElement FromCity;
	
	@FindBy(xpath="//div[@class='orgn u-ib u-v-align-bottom u-text-left']//input")
	private WebElement DestinationCity;
	
	@FindBy(xpath="//span[contains(text(),'Round Trip')]")
	private WebElement RoundTripSelection;
	
	@FindBy(xpath="//td[starts-with(@class,'rd-day-body') and not(contains(@class,'disabled')) and not(contains(@class,'prev-month')) and not(contains(@class,'next-month'))  ] ")
	private List<WebElement> SelectAllDates;
	
	@FindBy(xpath="//div[@class='rd-date']/div[1]//td[starts-with(@class,'rd-day-body') and not(contains(@class,'disabled')) and not(contains(@class,'prev-month')) and not(contains(@class,'next-month'))]/div[1]")
	private List<WebElement> SelectInsideCurrentMonth;
	
	@FindBy(xpath="//div[@class='rd-date']/div[2]//td[starts-with(@class,'rd-day-body') and not(contains(@class,'disabled')) and not(contains(@class,'prev-month')) and not(contains(@class,'next-month'))]/div[1] ")
	private List<WebElement> SelectInNextMonth;
	
	@FindBy(xpath="//div[@class='rd-date']/div[2]//div[@class='rd-month-label']")
	private WebElement NextMonthText;
	
	@FindBy(xpath="//div[@class='rd-container flight-ret-cal extra-bottom rd-container-attachment arrow-animation']//div[@class='rd-date']/div[2]//div[@class='rd-month-label']")
	private WebElement ReturnNextMonthText;
	
	@FindBy(xpath="//div[@class='rd-date']/div[1]//div[@class='rd-month-label']")
	private WebElement CurrentMonthText;
	
	@FindBy(xpath="//div[@class='rd-container flight-ret-cal extra-bottom rd-container-attachment arrow-animation']//div[@class='rd-date']/div[1]//div[@class='rd-month-label']")
	private WebElement ReturnCurrentMonthText;
	
	@FindBy(xpath="//div[@class='rd-container flight-ret-cal extra-bottom rd-container-attachment arrow-animation']//div[@class='rd-date']/div/button[@class='ixi-icon-arrow rd-next']")
	private WebElement ReturnNextMonthButton;
	
	@FindBy(xpath="//button[@class='ixi-icon-arrow rd-next']")
	private WebElement NextMonthButton;
	
	@FindBy(xpath="//div[@class='u-box-result']/div[1]//span")
	private List<WebElement> PassengerSelection;
	
	@FindBy(xpath="//div[@class='search u-ib u-v-align-bottom']//div[@class='u-ripple']")
	private WebElement SearchButton;
	
	
	public IxigoFlightSearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void ixigohomepaevalidation() {
		String currenturl =driver.getCurrentUrl();
		String title = driver.getTitle();
		Assert.assertEquals("https://www.ixigo.com/", currenturl);
		Assert.assertEquals("ixigo - Flights, IRCTC Train Booking, Bus Booking, Air Tickets & Hotels",title);
	}
	
	//for clicking the round trip section
	public void roundTripSelection() {
		try {
		RoundTripSelection.click();
		}catch(TimeoutException e) {
			Helper.WebDriverWait(driver, RoundTripSelection);
			RoundTripSelection.click();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	//for selecting the From city using actions class
	public WebElement fromCitySelection() {
		Helper.WebDriverWait(driver,FromCity);
		Actions action = new Actions(driver);
		action.moveToElement(FromCity).click()
		//We are fetching the data from the excel file by using getStringData method
		.sendKeys(excel.getStringData("IxigoTestCase1", 1, 0)).pause(2000).sendKeys(Keys.ENTER).build().perform();
		
		return FromCity;
}
	//for selecting the destination city using actions class
	public WebElement destinationCitySelection() {
		Actions action = new Actions(driver);
		action.sendKeys(excel.getStringData(0, 1, 1)).pause(3000).sendKeys(Keys.ENTER).build().perform();
		return DestinationCity;
}
	//for selecting the data , the date will be given in the test case while calling the below method
	//date should be in the format dd-Month-yy(example - 02-May-21 or 25-November-21)
	public void CalenderDateSelection(String date) throws InterruptedException {
		Helper.WebDriverWait(driver, CurrentMonthText);
		//splitting the date on the basis of "-"
		 String[] datearr=date.split("-");
		 
		 //storing the date,month and year in different variable name
		 int dategiven= Integer.parseInt(datearr[0]);
		 String monthgiven= datearr[1];
		 int yeargiven= Integer.parseInt(datearr[2]);
		 
			if(!CurrentMonthText.getText().contains(monthgiven)) {
			while(!NextMonthText.getText().contains(monthgiven)) {
				System.out.println("Inside if loop");
				NextMonthButton.click();
			}
	
			}
		
			 if(CurrentMonthText.getText().contains(monthgiven)) {
		for(int i=0;i<SelectInsideCurrentMonth.size();i++) {
			if(Integer.parseInt(SelectInsideCurrentMonth.get(i).getText())==dategiven) {
				SelectInsideCurrentMonth.get(i).click();
				break;
			}
		}
	}
			 if(NextMonthText.getText().contains(monthgiven)) {
			for(int i=0;i<SelectInNextMonth.size();i++) {
				if(Integer.parseInt(SelectInNextMonth.get(i).getText())==dategiven) {
					SelectInNextMonth.get(i).click();
					break;
				}	
			}
			
		}
	}
	public void ReturnDateSelection(String date) throws InterruptedException {
		
		Helper.WebDriverWait(driver, ReturnCurrentMonthText);
		//splitting the date on the basis of "-"
		 String[] datearr=date.split("-");
		 
		 //storing the date,month and year in different variable name
		 int dategiven= Integer.parseInt(datearr[0]);
		 String monthgiven= datearr[1];
		 int yeargiven= Integer.parseInt(datearr[2]);
		 
		 if(!ReturnCurrentMonthText.getText().contains(monthgiven)) {	
			while(!ReturnNextMonthText.getText().contains(monthgiven)) {
				ReturnNextMonthButton.click();
				
			}
	
		 }
		
		if(ReturnCurrentMonthText.getText().contains(monthgiven)) {
		for(int i=0;i<SelectInsideCurrentMonth.size();i++) {
			try {
			if(Integer.parseInt(SelectInsideCurrentMonth.get(i).getText())==dategiven) {
				SelectInsideCurrentMonth.get(i).click();
				break;
			}
			}catch(NumberFormatException e) {
				continue;
			}
			}
		}
	
		 if(ReturnNextMonthText.getText().contains(monthgiven)) {
			for(int i=0;i<SelectInNextMonth.size();i++) {
				try {
				if(Integer.parseInt(SelectInNextMonth.get(i).getText())==dategiven) {
					SelectInNextMonth.get(i).click();
					break;
				}
				}catch(NumberFormatException e) {
					continue;
				}
			}
			
		}
	}
	
	public void SelectPassenger(String numberofadults) {
		for(WebElement sel:PassengerSelection) {
			if(sel.getText().equals(numberofadults)) {
				sel.click();
			}
		}
	}
	
	public void SearchButtonClick() {
		SearchButton.click();
	}

}
