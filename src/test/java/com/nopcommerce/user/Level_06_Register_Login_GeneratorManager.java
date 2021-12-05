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
import generatorManager.GenerateManagerObject;
import generatorManager.HomePageObject;
import generatorManager.LoginPageObject;
import generatorManager.RegisterPageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageUINopCommerce.HomePageUI;


public class Level_06_Register_Login_GeneratorManager extends BaseTest{
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
		homePage  = GenerateManagerObject.getHomePage(driver);
		System.out.println("Homepage object ID "+ homePage.hashCode());
		registerPage = homePage.clickToRegisterLink();
		
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
		homePage = registerPage.clickToLogoutLink();
		System.out.println("Homepage object ID "+ homePage.hashCode());
		loginPage = homePage.clickToLoginLink();
		
		loginPage.enterToEmailTextbox(email);
		loginPage.enterToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		
		System.out.println("Homepage object ID "+ homePage.hashCode());
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
