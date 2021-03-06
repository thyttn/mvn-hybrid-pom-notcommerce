package com.nopcommerce.user;

import org.testng.annotations.Test;


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
public class Level_17_Register_Login_Screenshot_AllureReport extends BaseTest{
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
	
	@Feature("User 01 register")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void TC_01_Register() {
		homePage  = GenerateManagerObject.getHomePage(driver);
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
		loginPage = homePage.clickToLoginLink();
		
		loginPage.enterToEmailTextbox(email);
		loginPage.enterToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		
		Assert.assertTrue(homePage.isHomePageSlider());
	}
	
	@Test
	public void TC_03_MyAccount() {
		myAccountPage = homePage.clickToMyAccountButton();
		
		//CustomerInfor -> Addresses
		myAccountPage.openGeneralPage("Addresses");
		
		//Addresses ->  Reward Points
		myAccountPage.openGeneralPage("Reward points");
		
		//Reward  Points -> Orders 
		myAccountPage.openGeneralPage("Orders");
		
		//Orders  -> CustomerInfor
		myAccountPage.openGeneralPage("Customer info");
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
