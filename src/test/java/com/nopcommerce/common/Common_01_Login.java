package com.nopcommerce.common;

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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

@Feature("Register and Login")
public class Common_01_Login extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email,password;
	public static Set<Cookie> loginPageCookie;
	
	HomePageObject homePage;
	LoginPageObject  loginPage;
	RegisterPageObject registerPage;
	MyAccountPagesObject myAccountPage;
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName, String url) {
		driver = multiBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		
		email = getRandomEmail();
		password = "123123";
	
		homePage  = GenerateManagerObject.getHomePage(driver);
		
		log.info("Common_01 - Step 02 - Click to register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Common_01 - Step 03 - Click to gender");
		registerPage.clickToGender();
		
		log.info("Common_01 - Step 04 - Input to firstname");
		registerPage.enterToFirstNameTextbox("Mark");
		
		log.info("Common_01 - Step 05 - Input to lastname");
		registerPage.enterToLastNameTextbox("Zugker");
		
		log.info("Common_01 - Step 06 - Input to email");
		registerPage.enterToEmailTextbox(email);
		System.out.println("email:" + email);
		
		log.info("Common_01 - Step 07 - Input to password");
		registerPage.enterToPasswordTextbox(password);
		
		log.info("Common_01 - Step 08 - Input to confirm");
		registerPage.enterToConfirmPasswordTextbox(password);
		
		log.info("Common_01 - Step 09 - Click to register button");
		registerPage.clickToRegisterButton();
		
		log.info("Common_01 - Step 10 - Verify register successfully");
		Assert.assertTrue(registerPage.verifySuccess());
	
		log.info("Common_01 - Step 12 - Click to logout  link");
		homePage = registerPage.clickToLogoutLink();
		
		log.info("Common_01 - Step 13 - Click to login  link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Common_01 - Step 14 - Input to email");
		loginPage.enterToEmailTextbox(email);
		
		log.info("Common_01 - Step 15 - Input to password");
		loginPage.enterToPasswordTextbox(password);
		
		log.info("Common_01 - Step 16 - Click to login button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("Common_01 - Step 17 - Verify login successfully");
		Assert.assertTrue(homePage.isHomePageSlider());
		
		log.info("Common_01 - Step 18 - Get all cookies");
		loginPageCookie = homePage.getAllCookies(driver);
		System.out.println("Cookies: " + loginPageCookie);
		
		log.info("Pre-condition: close browser and driver");
		closeBrowserAndDriver();				
	}
	

}
