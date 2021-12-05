package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import pageUINopCommerce.HomePageUI;


public class Level_03_Register_Login_POM{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email,password;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		email = "mark" + getRandom() + "@gmail.com";
		password = "123123";
	}
	
	@Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/");
		homePage  = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		registerPage.clickToGender();
		registerPage.enterToFirstNameTextbox("Mark");
		registerPage.enterToLastNameTextbox("Zugker");
		registerPage.enterToEmailTextbox(email);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.verifySuccess());
	}

	@Test
	public void TC_02_Login() {
		registerPage.clickToLogoutLink();
		homePage = new HomePageObject(driver);
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		loginPage.enterToEmailTextbox(email);
		loginPage.enterToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isHomePageSlider());
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	public int getRandom() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	HomePageObject homePage;
	LoginPageObject  loginPage;
	RegisterPageObject registerPage;
	
}
