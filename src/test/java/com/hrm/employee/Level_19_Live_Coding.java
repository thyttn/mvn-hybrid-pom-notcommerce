package com.hrm.employee;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login;
//import com.relevantcodes.extentreports.LogStatus;

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
public class Level_19_Live_Coding extends BaseTest {
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
		driver = multiBrowser(browserName);
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

	@Test
	public void Employee_02_Upload_Avatar() {
		log.info("Upload_Employee_02 - Step 01: Logout hrm");
		employeeListPage.logoutHRM(driver);
		
		log.info("Upload_Employee_02 - Step 02: Login to the system by user account");
		dashboardPage = loginPage.loginSystem(driver, userName, userPassword);
		
		log.info("Upload_Employee_02 - Step 03: Go to Personal detail page");
		dashboardPage.openMainMenu(driver, "My Info");
		personalDetailsPage = PageGenerator.getMyInfoPage(driver);
		
		verifyTrue(personalDetailsPage.isJQueryAJaxLoadedSuccess(driver));
		log.info("Upload_Employee_02 - Step 04: Click to Avatar link");
		personalDetailsPage.clickToAvatarImage();
		
		log.info("Upload_Employee_02 - Step 05: Enter path to the avatar image");
		personalDetailsPage.uploadFileToHRM(driver, "photofile", GlobalConstants.FILE_PATH + "avatar.jpg");
		
		log.info("Upload_Employee_02 - Step 06: Click to 'Upload' button");
		personalDetailsPage.clickToButtonById(driver, "btnSave");
		
		log.info("Upload_Employee_02 - Step 07: Verify uploaded success message is displayed");
		verifyTrue(personalDetailsPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));
		
		log.info("Upload_Employee_02 - Step 08: Verify new uploaded avatar is displayed");
		verifyTrue(personalDetailsPage.isUploadedAvatarDisplayed());
	}

	@Test
	public void Employee_03_Personal_Details() {
		log.info("Personal Details_03 - Step 01 - Click to 'Personal Detail' tab in My info screen");
		personalDetailsPage.openTabNameInMyInforPage(driver, "Personal Details");
		
		log.info("Personal Details_03 - Step 02 - Verify 'Full name' row are disabled");
		personalDetailsPage.isEnableField(driver, "personal_txtEmpFirstName");
		personalDetailsPage.isEnableField(driver, "personal_txtEmpMiddleName");
		personalDetailsPage.isEnableField(driver, "personal_txtEmpLastName");
		
		log.info("Personal Details_03 - Step 03 - Verify 'Employee ID' field is disabled");
		personalDetailsPage.isEnableField(driver, "personal_txtEmployeeId");
		
		log.info("Personal Details_03 - Step 04 - Verify 'Gender' field is disabled");
		personalDetailsPage.isEnableField(driver, "personal_optGender_1");
		
		log.info("Personal Details_03 - Step 05 - Verify 'Date of birth' field is disabled");
		personalDetailsPage.isEnableField(driver, "personal_DOB");
		
		log.info("Personal Details_03 - Step 06 - Click to 'Edit' button in 'Personal Details' area");
		personalDetailsPage.clickToButtonById(driver, "btnSave");
		
		log.info("Personal Details_03 - Step 07 - Verify 'Employee Id' textbox is disabled");
		personalDetailsPage.isEnableField(driver, "personal_txtEmployeeId");
		
		log.info("Personal Details_03 - Step 08 - Verify 'Driver's License Number' textbox is disabled");
		personalDetailsPage.isEnableField(driver, "personal_txtLicenNo");

		log.info("Personal Details_03 - Step 09 - Verify 'Date of Birth' textbox is disabled");
		personalDetailsPage.isEnableField(driver, "personal_DOB");
		
		log.info("Personal Details_03 - Step 10 - Enter to 'Other ID' date field");
		personalDetailsPage.enterToTextboxById(driver, "1234", "personal_txtOtherID");
		
		log.info("Personal Details_03 - Step 11 - Enter to 'License Expiry Date' date field");
		personalDetailsPage.enterToDateFieldById(driver, "2018-11-14", "personal_txtLicExpDate");
		personalDetailsPage.sleepInSecond(2);
		
		log.info("Personal Details_03 - Step 12 - Select 'Marital Status' dropdownlist");
		personalDetailsPage.selectValueInDropdownById(driver, "personal_cmbMarital", "Single");
		
		log.info("Personal Details_03 - Step 13 - Select 'Nationality' dropdownlist");
		personalDetailsPage.selectValueInDropdownById(driver, "personal_cmbNation", "American");
		
		log.info("Personal Details_03 - Step 14 - Clisck to 'Save' button in 'Personal Details' area");
		personalDetailsPage.clickToButtonById(driver, "btnSave");
		
		log.info("Personal Details_03 - Step 15 - Verify saved success message is displayed");
		verifyTrue(personalDetailsPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Personal Details_03 - Step 16 - Verify 'Other ID' date is updated");
		verifyEquals(personalDetailsPage.getAttributeValueById(driver,  "value", "personal_txtOtherID"), "1234");
		
		
		log.info("Personal Details_03 - Step 17 - Verify 'License Expiry Date' is updated");
		verifyEquals(personalDetailsPage.getAttributeValueById(driver, "value", "personal_txtLicExpDate"), "2018-11-14");

		log.info("Personal Details_03 - Step 18 - Verify 'Marital Status' is updated");
		verifyEquals(personalDetailsPage.getSelectedItemValueById(driver,"personal_cmbMarital"), "Single");

		log.info("Personal Details_03 - Step 19 - Verify 'Nationality' dropdownlist is updated");
		verifyEquals(personalDetailsPage.getSelectedItemValueById(driver,"personal_cmbNation"), "American");
	}

	@Test
	public void Employee_04_Contact_Details() {
	}

	@Test
	public void Employee_05_Emergency_Details() {
	}

	@Test
	public void Employee_06_Assigned_Dependent() {
	}

	@Test
	public void Employee_07_Edit_View_Job() {
	}

	@Test
	public void Employee_08_Edit_View_Salary() {
	}

	@Test
	public void Employee_09_Edit_View_Tax() {
	}

	@Test
	public void Employee_10_Qualification() {
	}

	@Test
	public void Employee_11_Search_Employee() {
	}

	@AfterClass(alwaysRun = true)
	public void afterTest() {
		log.info("Pre-condition: close browser and driver");
		closeBrowserAndDriver();
	}
}
