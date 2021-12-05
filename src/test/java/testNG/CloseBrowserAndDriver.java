package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;

import commons.BaseTest;
import generatorManager.GenerateManagerObject;
import generatorManager.HomePageObject;
import generatorManager.LoginPageObject;
import generatorManager.MyAccountPagesObject;
import generatorManager.RegisterPageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class CloseBrowserAndDriver extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email,password;
	
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
		//Assert.assertTrue(false);
	}
	
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
		//Assert.assertFalse(registerPage.verifySuccess());
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

	@AfterTest(alwaysRun = true)
	public void afterClass() {
		System.out.println("Pre-condition: close browser and driver");
		log.info("Pre-condition: close browser and driver");
		closeBrowserAndDriver();
	}
}
