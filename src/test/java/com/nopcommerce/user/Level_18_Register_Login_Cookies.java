package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login;

import commons.BaseTest;
import generatorManager.GenerateManagerObject;
import generatorManager.HomePageObject;
import generatorManager.LoginPageObject;
import generatorManager.MyAccountPagesObject;
import generatorManager.RegisterPageObject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

@Epic("Regression test")
@Feature("Register and Login")
public class Level_18_Register_Login_Cookies extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email,password;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeTest(String browserName, String url) {
		driver = multiBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		
		email = getRandomEmail();
		password = "123123";
		
		homePage  = GenerateManagerObject.getHomePage(driver);
		
		log.info("Common_01 - Step 01 - Click to login  link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Common_01 - Step 02 - Set all cookies");
		loginPage.setAllCookies(driver, Common_01_Login.loginPageCookie);
		loginPage.sleepInSecond(5);
		loginPage.getRefreshPage(driver);
		
		homePage = GenerateManagerObject.getHomePage(driver);
		log.info("Common_01 - Step 03 - Verify login successfully");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}
	
	@Test
	public void TC_01_() {
		
	}

	@Test
	public void TC_02_() {}

	@AfterClass(alwaysRun = true)
	public void afterTest() {
		log.info("Pre-condition: close browser and driver");
		closeBrowserAndDriver();
	}
	
	HomePageObject homePage;
	LoginPageObject  loginPage;
	RegisterPageObject registerPage;
	MyAccountPagesObject myAccountPage;
}
