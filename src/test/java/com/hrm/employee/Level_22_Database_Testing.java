package com.hrm.employee;

import org.testng.annotations.Test;

import com.hrm.data.DataHelper;
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

@Epic("Regression test")
@Feature("Register and Login")
public class Level_22_Database_Testing extends BaseTest {
	WebDriver driver;
	
	DashboardPageObject dashboardPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeTest(String browserName, String url) {
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navidate to '" + url + "'");
		driver = multiBrowser(browserName);
		driver.get(url);
	}

	@Test
	public void TC_01_Verify_DB_With_UI() {
		log.info("TC_01 - Step 01: Get number of employee in UI");
		int empNumberInUI = 18;
		
		log.info("TC_01 - Step 02: Get number of employee in DB");
		dashboardPage = PageGenerator.getDashboardPage(driver);
		int empNumberInDB = dashboardPage.getEmpListInDB();
		
		log.info("TC_01 - Step 03: Compare number of employee in UI and DB");
		verifyEquals(empNumberInUI, empNumberInDB);
	}


	@AfterClass(alwaysRun = true)
	public void afterTest() {
		log.info("Pre-condition: close browser and driver");
		closeBrowserAndDriver();
	}
}
