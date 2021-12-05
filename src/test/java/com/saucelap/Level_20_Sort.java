package com.saucelap;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login;

import commons.BaseTest;
import pageObject.SourceLab.InventoryObject;
import pageObject.SourceLab.LoginPageObject;
import pageObject.SourceLab.PageGenerator;

public class Level_20_Sort extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	InventoryObject inventoryPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeTest(String browserName, String url) {
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navidate to '" + url + "'");
		driver = multiBrowser(browserName);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		loginPage = PageGenerator.getLoginPage(driver);
	}

	@Test
	public void Sort_01() {
		log.info("Sort_01 - Step 01: Enter to Username textbox");
		loginPage.enterToUserNameTxt("standard_user");

		log.info("Sort_01 - Step 02: Enter to Password textbox");
		loginPage.enterToPasswordTxt("secret_sauce");

		log.info("Sort_01 - Step 03: Click to Login button");
		inventoryPage = loginPage.clickToLoginButton();

		log.info("Sort_01 - SORT BY NAME ascending");
		log.info("Sort_01 - Step 04: Choose sort by Name A to Z");
		inventoryPage.selectSortType(driver, "Name (A to Z)");

		log.info("Sort_01 - Step 05: Verify list product is sorted by name ascending");
		verifyTrue(inventoryPage.isSortByNameAscending(driver));

		log.info("Sort_01 - SORT BY NAME descending");
		log.info("Sort_01 - Step 06: Choose sort by Name Z to A");

		inventoryPage.selectSortType(driver, "Name (Z to A)");

		log.info("Sort_01 - Step 07: Verify list product is sorted by name descending");
		verifyTrue(inventoryPage.isSortByNameDescending(driver));

	}

	@Test
	public void Sort_02() {
		log.info("Sort_02 - SORT BY PRICE ascending");

		log.info("Sort_02 - Step 01: Choose sort by price low to high");
		inventoryPage.selectSortType(driver, "Price (low to high)");

		log.info("Sort_02 - Step 02: Verify list product is sorted by price ascending");
		verifyTrue(inventoryPage.isSortByPriceAscending(driver));
		
		log.info("Sort_02 - Step 03: Choose sort by price high to low");
		inventoryPage.selectSortType(driver, "Price (high to low)");
		
		log.info("Sort_02 - Step 04: Verify list product is sorted by price descending");
		verifyTrue(inventoryPage.isSortByPriceDescending(driver));
	}

	@AfterClass(alwaysRun = true)
	public void afterTest() {
		log.info("Pre-condition: close browser and driver");
		closeBrowserAndDriver();
	}

}
