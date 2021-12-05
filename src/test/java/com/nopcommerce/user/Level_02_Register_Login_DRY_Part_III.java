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


public class Level_02_Register_Login_DRY_Part_III extends BasePage{
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
	}
	
	@Test
	public void TC_01_Register() {
		getFindElement(driver, "//a[@class='ico-register']").click();
		getFindElement(driver, "//input[@id='gender-male']").click();
		getFindElement(driver, "//input[@id='FirstName']").sendKeys(firstName);
		getFindElement(driver, "//input[@id='LastName']").sendKeys(lastName);
		
		select = new Select(getFindElement(driver, "//select[@name='DateOfBirthDay']"));
		select.selectByVisibleText(day);
		
		select = new Select(getFindElement(driver, "//select[@name='DateOfBirthMonth']"));
		select.selectByVisibleText(month);
		
		select = new Select(getFindElement(driver, "//select[@name='DateOfBirthYear']"));
		select.selectByVisibleText(year);
		
		getFindElement(driver, "//input[@id='Email']").sendKeys(email);
		getFindElement(driver, "//input[@id='Company']").sendKeys(companyName);
		getFindElement(driver, "//input[@id='Password']").sendKeys(password);
		getFindElement(driver, "//input[@id='ConfirmPassword']").sendKeys(comfirmPassword);
		getFindElement(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(getFindElement(driver, "//div[@class='result']").getText(), "Your registration completed");
		getFindElement(driver, "//a[@class='ico-logout']").click();
	}

	@Test
	public void TC_02_Login() {
		getFindElement(driver, "//a[@class='ico-login']").click();
		getFindElement(driver, "//input[@id='Email']").sendKeys(email);
		getFindElement(driver, "//input[@id='Password']").sendKeys(password);
		getFindElement(driver, "//button[@class='button-1 login-button']").click();
	}

	@Test
	public void TC_03_MyAccount() {
		getFindElement(driver, "//a[@class='ico-account']").click();
		
		Assert.assertEquals(getFindElement(driver, "//div[@class='page-title']//h1").getText(), "My account - Customer info");
		Assert.assertTrue(getFindElement(driver, "//input[@id='gender-male']").isSelected());
		Assert.assertEquals(getFindElement(driver, "//input[@id='FirstName']").getAttribute("value") ,firstName);
		Assert.assertEquals(getFindElement(driver, "//input[@id='LastName']").getAttribute("value") ,lastName);
		
		select = new Select(getFindElement(driver, "//select[@name='DateOfBirthDay']"));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(getFindElement(driver, "//select[@name='DateOfBirthMonth']"));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(getFindElement(driver, "//select[@name='DateOfBirthYear']"));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(getFindElement(driver, "//input[@id='Email']").getAttribute("value") ,email);
		Assert.assertEquals(getFindElement(driver, "//input[@id='Company']").getAttribute("value") ,companyName);
		
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
