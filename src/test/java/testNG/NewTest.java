package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NewTest extends BaseTest{
	WebDriver driver;
	
	//@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(false);
	}
  @Test(alwaysRun = true)
  public void f() {
	  System.out.println("testing");
  }
  
  

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("Pre-condition: close browser and driver");
		log.info("Pre-condition: close browser and driver");
		closeBrowserAndDriver();
	}
}
