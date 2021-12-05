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

public class Level_23_Browser_Headless extends BaseTest {
	WebDriver driver;
	String employeeID, statusValue, firstName, lastName, userName, userPassword;

	LoginPageObject loginPage;
	AddEmployeePageObject addEmployeePage;
	DashboardPageObject dashboardPage;
	EmployeeListPageObject employeeListPage;
	MyInfoPageObject personalDetailsPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeTest(String browserName, String url) {
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navidate to '" + url + "'");
		driver = multiBrowserHeadless(browserName, url);
		driver.get(url);
		loginPage = PageGenerator.getLoginPage(driver);

		statusValue = "Enabled";
		firstName = "Warren";
		lastName = "Buffet" + (new Random()).nextInt();
		userName = firstName + " " + lastName;
		userPassword = "12341234";

		log.info("Pre-condition - Step 02: Login with Admin role");
		loginPage.loginSystem(driver, "Admin", "admin123");

		dashboardPage = PageGenerator.getDashboardPage(driver);
	}

	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add_New_01 - Step 01: Open Employee List page.");
		dashboardPage.openSubMenu(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Add_New_01 - Step 02: Click to Add button");
		employeeListPage.clickToButtonById(driver, "btnAdd");

		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		log.info("Add_New_01 - Step 03: Enter valid info to Firstname textbox");
		addEmployeePage.enterToTextboxById(driver, firstName, "firstName");

		log.info("Add_New_01 - Step 04: Enter valid info to Lastname textbox");
		addEmployeePage.enterToTextboxById(driver, lastName, "lastName");

		log.info("Add_New_01 - Step 05: Get value of Employee ID");
		employeeID = addEmployeePage.getValueOfTextboxById(driver, "employeeId");

		log.info("Add_New_01 - Step 06: Click to Create Login Details checkbox");
		addEmployeePage.clickToButtonById(driver, "chkLogin");

		log.info("Add_New_01 - Step 07: Enter valid info into 'UserName' textbox");
		addEmployeePage.enterToTextboxById(driver, userName, "user_name");

		log.info("Add_New_01 - Step 08: Enter valid info into 'Password' textbox");
		addEmployeePage.enterToTextboxById(driver, userPassword, "user_password");

		log.info("Add_New_01 - Step 09: Enter valid info into 'Confirm password' textbox");
		addEmployeePage.enterToTextboxById(driver, userPassword, "re_password");

		log.info("Add_New_01 - Step 10: select 'Enabled' value in 'Status' dropdown");
		addEmployeePage.selectValueInDropdownById(driver, "status", statusValue);

		log.info("Add_New_01 - Step 11 Click to 'Save' button");
		addEmployeePage.clickToButtonById(driver, "btnSave");
		personalDetailsPage = PageGenerator.getMyInfoPage(driver);

		log.info("Add_New_01 - Step 12: Open Employee list ");
		personalDetailsPage.openMainMenu(driver,"Employee List");
		
		verifyTrue(employeeListPage.isJQueryAJaxLoadedSuccess(driver));
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		verifyTrue(employeeListPage.isJQueryAJaxLoadedSuccess(driver));

		log.info("Add_New_01 - Step 13: Enter valid value infor into 'Employee name' textbox to search");
		
		employeeListPage.enterToTextboxById(driver, userName, "empsearch_employee_name_empName");
		verifyTrue(employeeListPage.isJQueryAJaxLoadedSuccess(driver));

		log.info("Add_New_01 - Step 14: Click to 'Search' button");
		employeeListPage.clickToButtonById(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAJaxLoadedSuccess(driver));

		log.info("Add_New_01 - Step 15: Verrify Employee Information displayed at 'Result Table'");
		verifyEquals(employeeListPage.verifyResultSearchByTableIdAndColumnName(driver, "resultTable", "Id"), employeeID);
		verifyEquals(employeeListPage.verifyResultSearchByTableIdAndColumnName(driver, "resultTable", "First (& Middle) Name"), firstName);
		verifyEquals(employeeListPage.verifyResultSearchByTableIdAndColumnName(driver, "resultTable", "Last Name"), lastName);
	}

	@AfterClass(alwaysRun = true)
	public void afterTest() {
		log.info("Pre-condition: close browser and driver");
		closeBrowserAndDriver();
	}
}
