package com.jquery;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectJquery.GenerateManagerObject;
import pageObjectJquery.HomePageObject;

public class Level_09_DataTable extends BaseTest{
	WebDriver driver;
	HomePageObject homePage;
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName, String url) {
		driver = multiBrowser(browserName);
		driver.get(url);
	}
	
	@Test
	public void TC_01_Paging() {
		homePage = GenerateManagerObject.getHomePage(driver);
		homePage.openPagingByName("1");
		Assert.assertTrue(homePage.isPagingActiveByName("1"));
		
		homePage.openPagingByName("10");
		Assert.assertTrue(homePage.isPagingActiveByName("10"));
		
		homePage.openPagingByName("7");
		Assert.assertTrue(homePage.isPagingActiveByName("7"));
		
		homePage.openPagingByName("1");
		Assert.assertTrue(homePage.isPagingActiveByName("1"));
	}

	@Test
	public void TC_02_Search_In_Table() {
		homePage.enterToTableTextSearch("Afghanistan");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isSearchingByCountry("384187","Afghanistan","407124","791312"));
	}
	
	@Test
	public void TC_03_Edit_Remove_Row() {
		homePage.removeRow("Albania","remove");
		homePage.sleepInSecond(3);
		
		homePage.removeRow("Algeria","remove");
		homePage.sleepInSecond(3);
	}
	

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
