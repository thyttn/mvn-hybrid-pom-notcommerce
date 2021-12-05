package com.liveguru.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectJquery.GenerateManagerObject;
import pageObjectJquery.HomePageObject;
import pageObjectLiveGuru.AdminCustomerPageObject;
import pageObjectLiveGuru.AdminLoginPageObject;
import pageObjectLiveGuru.GenerateObject;
import pageObjectLiveGuru.UserDashboardPageObject;
import pageObjectLiveGuru.UserHomePageObject;
import pageObjectLiveGuru.UserLoginPageObject;
import pageObjectLiveGuru.UserRegisterPageObject;

public class Level_10_DataTable extends BaseTest{
	WebDriver driver;
	UserHomePageObject userHomePage;
	UserRegisterPageObject userRegisterPage;
	UserLoginPageObject userLoginPage;
	UserDashboardPageObject userDashboardPage;
	AdminLoginPageObject adminLoginPage;
	AdminCustomerPageObject adminCustomerPage;
	
	private String firstName = "user";
	private String lastName = "02";
	private String email = getRandomEmail();
	private String password = "123123";
	private String confirmPassword = "123123";
	private String adminUserName = "user01";
	private String adminPassword = "guru99com";
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName, String url) {
		driver = multiBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		userHomePage = GenerateObject.getUserHomePage(driver);
	}
	
	@Test
	public void TC_01_Register_Account() {
		userLoginPage = userHomePage.openLoginPage();
		userRegisterPage = userLoginPage.openRegisterPage();
		
		userRegisterPage.enterToFirstName(firstName);
		userRegisterPage.enterToLastName(lastName);
		userRegisterPage.enterToEmail(email);
		userRegisterPage.enterToPassword(password);
		userRegisterPage.enterToConfirmPassword(confirmPassword);
		userDashboardPage = userRegisterPage.clickToLoginButton();
		
		Assert.assertTrue(userDashboardPage.isCreateAccountSuccessMessageDisplayed());
	}
	
	@Test
	public void TC_02_Admin_Check_Register_Success() {
		driver.get("http://live.demoguru99.com/index.php/backendlogin/customer");
		adminLoginPage = GenerateObject.getAdminLoginPage(driver);
		adminLoginPage.enterToUserName(adminUserName);
		adminLoginPage.enterToPassword(adminPassword);
		adminCustomerPage = adminLoginPage.clickToLoginButton();
		
		adminCustomerPage.closePopup();
		adminCustomerPage.sleepInSecond(2);
		
		adminCustomerPage.enterToTextboxAtColumnName("email","user03@gmail.com");
		Assert.assertTrue(adminCustomerPage.isResultSearchDisplayed("user 03","user03@gmail.com"));
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
