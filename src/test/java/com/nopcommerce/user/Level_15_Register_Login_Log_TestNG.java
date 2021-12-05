package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import generatorManager.MyAccountPagesObject;
import generatorManager.GenerateManagerObject;
import generatorManager.HomePageObject;
import generatorManager.LoginPageObject;
import generatorManager.RegisterPageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjectLiveGuru.UserRegisterPageObject;
import pageUINopCommerce.HomePageUI;


public class Level_15_Register_Login_Log_TestNG extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email,password;
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName, String url) {
		log.info("Precondition - Step 01 - Open browser '" + browserName + "'");
		driver = multiBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		
		email = getRandomEmail();
		password = "123123";
	}
	
	@Test
	public void TC_01_Register() {
		log.info("Register - Step 01 - click to register link");
		homePage  = GenerateManagerObject.getHomePage(driver);
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register - Step 02 - enter infor");
		registerPage.clickToGender();
		registerPage.enterToFirstNameTextbox("Mark");
		registerPage.enterToLastNameTextbox("Zugker");
		registerPage.enterToEmailTextbox(email);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		
		log.info("Register - Step 03 - click to register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register - Step 04 - verify success");
		verifyTrue(registerPage.verifySuccess());
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	HomePageObject homePage;
	LoginPageObject  loginPage;
	RegisterPageObject registerPage;
	MyAccountPagesObject myAccountPage;
}
