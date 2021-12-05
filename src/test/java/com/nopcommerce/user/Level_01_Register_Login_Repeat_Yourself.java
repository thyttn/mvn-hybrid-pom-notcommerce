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


public class Level_01_Register_Login_Repeat_Yourself {
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
		driver.findElement(By.cssSelector(".ico-register")).click();
		driver.findElement(By.cssSelector(".male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(comfirmPassword);
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		driver.findElement(By.className("ico-logout")).click();
	}

	@Test
	public void TC_02_Login() {
		driver.findElement(By.className("ico-login")).click();
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.cssSelector(".login-button")).click();
	}

	@Test
	public void TC_03_MyAccount() {
		driver.findElement(By.cssSelector(".ico-account")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "My account - Customer info");
		Assert.assertTrue(driver.findElement(By.id("gender-male")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value") ,firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value") ,lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value") ,email);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value") ,companyName);
		
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
