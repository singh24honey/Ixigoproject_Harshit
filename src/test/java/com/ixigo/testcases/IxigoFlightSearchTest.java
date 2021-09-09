package com.ixigo.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ixigo.pages.BaseClass;
import com.ixigo.pages.IxigoFlightSearchPage;
import com.ixigo.pages.IxigoFlightSearchResultPage;
import com.ixigo.utilities.Helper;



public class IxigoFlightSearchTest extends BaseClass {
	
	@Test
	public void Ixigo() throws Exception {
		
		System.out.println("This is just to check branching in GIT");
		Logger=report.createTest("Checking the Ixigo searh engine");
		IxigoFlightSearchPage ixigo= new IxigoFlightSearchPage(driver);
		
		ixigo.ixigohomepaevalidation();
		
		ixigo.roundTripSelection();
		
		ixigo.fromCitySelection();
		
		ixigo.destinationCitySelection();
		
		ixigo.CalenderDateSelection("03-May-21");
		
		ixigo.ReturnDateSelection("10-May-21");
		
		ixigo.SelectPassenger("2");
		
		ixigo.SearchButtonClick();
		
		IxigoFlightSearchResultPage searchresult= new IxigoFlightSearchResultPage(driver);
		
		Helper.WebDriverWait(driver, searchresult.StopsLabel());
		
		String stops=searchresult.StopsLabel().getText();
		
		Assert.assertEquals(stops, "Stops");
		
		searchresult.MoreFilterbutton();
		
		searchresult.StopsFieldValidation();
		
		searchresult.DepartureValidation();
		
		searchresult.AirLineFieldValidation();
		
		searchresult.LessFilterButton();
		
		searchresult.NonStopCheckboxselect();
		
		searchresult.PriceLessThan7000();
		
		Logger.pass("Pass");
	}

}
