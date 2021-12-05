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


public class Level_02_Register_Login_DRY_Part_I {
	BasePage basePage;
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	
	String firstName = "selenium", 
			lastName = "19",
			day = "10",
			month ="February",
			year = "2000",
			email = "selenium"+ getRandom() +"@hotmail.com",
			companyName = "nopcommerce",
			password = "123456",
			comfirmPassword ="123456";			
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		driver.get("https://demo.nopcommerce.com/");
		basePage = new BasePage();
	}
	
	@Test
	public void TC_01_Register() {
		basePage.getFindElement(driver, "//a[@class='ico-register']").click();
		basePage.getFindElement(driver, "//input[@id='gender-male']").click();
		basePage.getFindElement(driver, "//input[@id='FirstName']").sendKeys(firstName);
		basePage.getFindElement(driver, "//input[@id='LastName']").sendKeys(lastName);
		
		select = new Select(basePage.getFindElement(driver, "//select[@name='DateOfBirthDay']"));
		select.selectByVisibleText(day);
		
		select = new Select(basePage.getFindElement(driver, "//select[@name='DateOfBirthMonth']"));
		select.selectByVisibleText(month);
		
		select = new Select(basePage.getFindElement(driver, "//select[@name='DateOfBirthYear']"));
		select.selectByVisibleText(year);
		
		basePage.getFindElement(driver, "//input[@id='Email']").sendKeys(email);
		basePage.getFindElement(driver, "//input[@id='Company']").sendKeys(companyName);
		basePage.getFindElement(driver, "//input[@id='Password']").sendKeys(password);
		basePage.getFindElement(driver, "//input[@id='ConfirmPassword']").sendKeys(comfirmPassword);
		basePage.getFindElement(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(basePage.getFindElement(driver, "//div[@class='result']").getText(), "Your registration completed");
		basePage.getFindElement(driver, "//a[@class='ico-logout']").click();
	}

	@Test
	public void TC_02_Login() {
		basePage.getFindElement(driver, "//a[@class='ico-login']").click();
		basePage.getFindElement(driver, "//input[@id='Email']").sendKeys(email);
		basePage.getFindElement(driver, "//input[@id='Password']").sendKeys(password);
		basePage.getFindElement(driver, "//button[@class='button-1 login-button']").click();
	}

	@Test
	public void TC_03_MyAccount() {
		basePage.getFindElement(driver, "//a[@class='ico-account']").click();
		
		Assert.assertEquals(basePage.getFindElement(driver, "//div[@class='page-title']//h1").getText(), "My account - Customer info");
		Assert.assertTrue(basePage.getFindElement(driver, "//input[@id='gender-male']").isSelected());
		Assert.assertEquals(basePage.getFindElement(driver, "//input[@id='FirstName']").getAttribute("value") ,firstName);
		Assert.assertEquals(basePage.getFindElement(driver, "//input[@id='LastName']").getAttribute("value") ,lastName);
		
		select = new Select(basePage.getFindElement(driver, "//select[@name='DateOfBirthDay']"));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(basePage.getFindElement(driver, "//select[@name='DateOfBirthMonth']"));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(basePage.getFindElement(driver, "//select[@name='DateOfBirthYear']"));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(basePage.getFindElement(driver, "//input[@id='Email']").getAttribute("value") ,email);
		Assert.assertEquals(basePage.getFindElement(driver, "//input[@id='Company']").getAttribute("value") ,companyName);
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	public int getRandom() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}
