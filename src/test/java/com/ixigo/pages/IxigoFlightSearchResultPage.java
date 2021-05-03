package com.ixigo.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.ixigo.utilities.Helper;

public class IxigoFlightSearchResultPage extends BaseClass {
	
WebDriver driver;
	
	
	@FindBy(xpath="//div[@class='fltr-col-1 u-ib']//div[contains(text(),'Stops')]")
	private WebElement StopsLabel;
	
	@FindBy(xpath="//div[@class='more-fltrs u-link']")
	private WebElement MoreFilterButton;
	
	@FindBy(xpath="//div[@class='less-fltrs u-link']")
	private WebElement LessFilterButton;
	
	@FindBy(xpath="//div[@class='fltr-cntnt']//span[@class='label u-ib u-pos-rel u-v-align-top']")
	private List<WebElement> StopsFieldValidation;
	
	@FindBy(xpath="//div[contains(text(),'Departure from DEL')]")
	private WebElement DepartureFieldValidation;
	
	@FindBy(xpath="//div[@class='fltr-col-2 u-ib']/div[1]//div[@class='tmng-btn']")
	private List<WebElement> DepartureFieldValidation1;
	
	@FindBy(xpath="//div[contains(text(),'Airlines')]")
	private WebElement AirlineLable;
	
	@FindBy(xpath="//div[@class='arln-nm']")
	private List<WebElement> AirlineNames;
	
	@FindBy(xpath="//div[@class='fltr-col-1 u-ib']//span[@class='checkbox-list']/div[1]/span[1]")
	private WebElement NonStopCheckbox;
	
	@FindBy(xpath="//div[@class='price']")
	private List<WebElement> PriceList;
	
	@FindBy(xpath="//div[@class='airline-text']")
	private List<WebElement> FlightNumberList;
	
	@FindBy(xpath="//div[@class='time-group']/div[1]")
	private List<WebElement> DepartureTimeList;
	
	public IxigoFlightSearchResultPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement StopsLabel() {
		
		return StopsLabel;
	}
	
	public void MoreFilterbutton() {
		MoreFilterButton.click();
	}
	
	public void LessFilterButton() throws InterruptedException {
		
		Helper.javaScriptExecutorSctollIntoView(driver,LessFilterButton);
		
	}
	
	public void StopsFieldValidation() {
		List<String> stopcheckboxes= new ArrayList<String>();
		stopcheckboxes.add("Non stop");
		stopcheckboxes.add("1 stop");
		stopcheckboxes.add("1+ stops");
		List<String> stopcheckboxesresult= new ArrayList<String>();
		for(WebElement we:StopsFieldValidation) {
			stopcheckboxesresult.add(we.getText());
		}
		int count=0;
		if(stopcheckboxes.equals(stopcheckboxesresult)) {
			count=count+1;
		}
		Assert.assertEquals(1,count);
	}
	
	public void DepartureValidation() {
		String name=DepartureFieldValidation.getText();
		Assert.assertEquals("Departure from DEL", name);
		List<String> List1= new ArrayList<String>();
		List1.add("00:00 - 06:00" +"\n"+"Early Morning");
		List1.add("06:00 - 12:00" +"\n"+"Morning");
		List1.add("12:00 - 18:00" +"\n"+"Mid Day");
		List1.add("18:00 - 24:00" +"\n"+"Night");
		
		List<String> List2= new ArrayList<String>();
		for(WebElement we:DepartureFieldValidation1) {
			List2.add(we.getText());
		}
		
		int count=0;
		if(List2.equals(List1)) {
			count=count+1;
		}
		Assert.assertEquals(1,count);

	}
	
	public void AirLineFieldValidation() throws Exception {
		
	
		
		List<String> list1= new ArrayList<String>();
		
		for(WebElement we:AirlineNames) {
			list1.add(we.getText());
		}
		List<String> list2= new ArrayList<String>(list1);
		
		Collections.sort(list2);
		
		
		if(!list1.equals(list2)) {
			throw new Exception("Both the List are not same");
		}
		
	}
	public void NonStopCheckboxselect() {
		if(!NonStopCheckbox.isSelected()) {
			NonStopCheckbox.click();
		}
		else {
			System.out.println("Non Stop already selected");
		}
	}
	
	public void PriceLessThan7000() {
		for(int i=0;i<PriceList.size()-1;i++) {
			if(Integer.valueOf((PriceList.get(i).getText()))<7000) {
				System.out.println("Airline number is : "+FlightNumberList.get(i).getText()+" ,"+"Departure time is "+DepartureTimeList.get(i).getText()
						+" "+ "And fare is "+PriceList.get(i).getText());
			}
		}
	}

}
