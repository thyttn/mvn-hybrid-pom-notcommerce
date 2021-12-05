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
import io.github.bonigarcia.wdm.WebDriverManager;
import pageFactoryNopcommerce.*;
import pageUINopCommerce.HomePageUI;

public class Level_05_Register_Login_PageObject_FactoryObject extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email,password;
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName, String url) {
		driver = multiBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		
		email = getRandomEmail();
		password = "123123";
	}
	
	@Test
	public void TC_01_Register() {
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
	
	HomePageObject homePage;
	LoginPageObject  loginPage;
	RegisterPageObject registerPage;
	
}
