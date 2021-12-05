package com.liveguru.user;

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
import pageObjectLiveGuru.UserHomePageObject;
import pageObjectLiveGuru.UserLoginPageObject;
import pageObjectLiveGuru.UserRegisterPageObject;

public class Level_04_Login_Multiple_Browser_POM extends BaseTest{
	private WebDriver driver;
	String email,password;
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName,String urlLink) {
		driver = multiBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/");
	}
	
	@Test
	public void TC_01_EmptyEmailAndPassword() {
		homePage = new UserHomePageObject(driver);
		homePage.openLoginPage();
		loginPage = new UserLoginPageObject(driver);
		loginPage.enterEmailTxt("");
		loginPage.enterPasswordTxt("");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmptyEmailErrorMessage(), "This is a required field.");
		Assert.assertEquals(loginPage.getEmptyPasswordErrorMessage(), "This is a required field.");
	}
	@Test
	public void TC_02_InvalidEmail() {
		loginPage.refreshLoginPage(driver);
		loginPage.enterEmailTxt("123@123");
		loginPage.enterPasswordTxt("123123");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getInvalidEmailErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	@Test
	public void TC_03_InvalidPassword() {
		loginPage.refreshLoginPage(driver);
		loginPage.enterEmailTxt("test@gmail.com");
		loginPage.enterPasswordTxt("123");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getInvalidPasswordErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test(enabled =  false)
	public void TC_04_IncorrectEmail() {
		loginPage.refreshLoginPage(driver);
		loginPage.enterEmailTxt(getRandomEmail());
		loginPage.enterEmailTxt("123123");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getIncorrectEmailOrPassword(),"Invalid login or password.");
	}
	@Test
	public void TC_05_IncorrectPassword() {
		loginPage.refreshLoginPage(driver);
		loginPage.enterEmailTxt("automation_13@gmail.com");
		loginPage.enterPasswordTxt("123456");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getIncorrectEmailOrPassword(),"Invalid login or password.");
	}

	@Test
	public void TC_06_CorrectEmailAndPassword() {
		loginPage.refreshLoginPage(driver);
		loginPage.enterEmailTxt("automation_13@gmail.com");
		loginPage.enterPasswordTxt("123123");
		loginPage.clickToLoginButton();
		myAccountPage = new UserRegisterPageObject(driver);
		
		//Assert.assertEquals(myAccountPage.getSuccessLoginMessage(), "MY DASHBOARD");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	public String getRandomEmail() {
		Random rand = new Random();
		String randomEmail = "automation" + rand.nextInt(99999) + "@gmail.com";
		return randomEmail;
	}
	
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
	UserRegisterPageObject myAccountPage;
}
