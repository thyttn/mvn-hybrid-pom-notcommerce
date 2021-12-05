package browserCapabilities;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login;

import commons.BaseTest;
import commons.GlobalConstants;
import generatorManager.GenerateManagerObject;
import generatorManager.HomePageObject;
import generatorManager.MyAccountPagesObject;
import generatorManager.RegisterPageObject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObject.hrm.AddEmployeePageObject;
import pageObject.hrm.DashboardPageObject;
import pageObject.hrm.EmployeeListPageObject;
import pageObject.hrm.LoginPageObject;
import pageObject.hrm.MyInfoPageObject;
import pageObject.hrm.PageGenerator;
import pageObject.hrm.MyInfoPageObject;
import pageUIs.hrm.CommonPageUI;
import pageUIs.hrm.EmployeeListPageUI;

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
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class Level_23_Show_Browser_Log extends BaseTest {
	WebDriver driver;
	String employeeID, statusValue, firstName, lastName, userName, userPassword;

	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeTest(String browserName, String url) {
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navidate to '" + url + "'");
		driver = multiBrowser(browserName, url);
		driver.get(url);
		loginPage = PageGenerator.getLoginPage(driver);
	}

	@Test
	public void TC_01_Login() {
		log.info("TC_01_Login - Step 01: Login with Admin role");
		loginPage.loginSystem(driver, "Admin", "admin123");
		showBrowserConsoleLog(driver);

		dashboardPage = PageGenerator.getDashboardPage(driver);
	}

	@AfterClass(alwaysRun = true)
	public void afterTest() {
		log.info("Pre-condition: close browser and driver");
		closeBrowserAndDriver();
	}
}
