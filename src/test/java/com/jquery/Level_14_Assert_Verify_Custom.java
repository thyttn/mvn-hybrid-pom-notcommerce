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

public class Level_14_Assert_Verify_Custom extends BaseTest{
	WebDriver driver;
	HomePageObject homePage;
	
	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = multiBrowser(browserName);
		driver.get("https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");
	}
	
	@Test
	public void TC_01_Paging() {
		homePage = GenerateManagerObject.getHomePage(driver);
		homePage.openPagingByName("1");
		verifyFalse(homePage.isPagingActiveByName("1"));
		
		homePage.openPagingByName("10");
		verifyFalse(homePage.isPagingActiveByName("10"));
		
		homePage.openPagingByName("7");
		verifyTrue(homePage.isPagingActiveByName("7"));
		
		homePage.openPagingByName("1");
		verifyFalse(homePage.isPagingActiveByName("1"));
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
