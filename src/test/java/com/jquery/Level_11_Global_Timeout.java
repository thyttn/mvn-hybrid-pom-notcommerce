package com.jquery;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;

public class Level_11_Global_Timeout extends BaseTest{
	WebDriver driver;
	WebDriverWait waitExplicit;
	List <WebElement> elements;
	long longTimeout = 10;
	long shortTimeout = 3;
	
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName, String url) {
		driver = multiBrowser(browserName);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	@Test
	public void TC_01_Element_In_Dom_And_UI() {
		waitForElementVisible(driver, "//a[contains(text(),'Tạo tài khoản mới')]");
		driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();
		Assert.assertTrue(isElementDisplayed(driver, "//input[@name='lastname']"));		
	}
	@Test
	public void TC_02_Element_In_Dom_And_Not_In_UI() {
		Assert.assertTrue(isElementInVisible("//input[@name='reg_email_confirmation__']"));
	}
	@Test
	public void TC_03_Element_Not_In_Dom_And_Not_UI() {
		waitForElementVisible(driver, "//img[@class='_8idr img']");
		driver.findElement(By.xpath("//img[@class='_8idr img']")).click();
		Assert.assertTrue(isElementInVisible("//div[@id='reg_box']"));
	}

	public boolean waitForElementVisible(WebDriver driver, String locator) {
		try {
			overideTimeout(driver, shortTimeout);
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			overideTimeout(driver, longTimeout);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		try {
				return element.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean waitForElementInVisible(WebDriver driver, String locator) {
		try {
			overideTimeout(driver, shortTimeout);
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
			overideTimeout(driver, longTimeout);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isElementInVisible(String locator) {
		overideTimeout(driver, shortTimeout);
		elements = driver.findElements(By.xpath(locator));
		overideTimeout(driver, longTimeout);
		
		if(elements.size() == 0) {
			System.out.println("Element is not in DOM");
			return true;
		}else if(elements.size() > 0 && !elements.get(0).isDisplayed()){
			System.out.println("Element is in DOm but not visible in UI");
			return true;
		}else {
			System.out.println("elemment in DOM and visible");
			return false;
		}
	}
	
	public void overideTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
