package pageObjectLiveGuru;

import org.openqa.selenium.WebDriver;

public class GenerateObject {
	private static UserHomePageObject userHomePage;
	private static UserLoginPageObject userLoginPage;
	private static UserRegisterPageObject userRegisterPage;
	private static UserDashboardPageObject userDashboadPage;
	private static AdminLoginPageObject adminLoginPage;
	private static AdminCustomerPageObject adminCustomerPage;
	
	private GenerateObject(){
	}
	
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		if(userHomePage == null) {
			return new UserHomePageObject(driver);
		}
			return userHomePage;
	}
	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		if(userLoginPage == null) {
			return new UserLoginPageObject(driver);
		}
		return userLoginPage;
	}
	
	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		if(userRegisterPage == null) {
			return new UserRegisterPageObject(driver);
		}
		return userRegisterPage;
	}
	public static UserDashboardPageObject getUserDashboadPage(WebDriver driver) {
		if(userDashboadPage == null) {
			return new UserDashboardPageObject(driver);
		}
		return userDashboadPage;
	}
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		if(adminLoginPage == null) {
			return new AdminLoginPageObject(driver);
		}
		return adminLoginPage;
	}
	public static AdminCustomerPageObject getAdminCustomerPage(WebDriver driver) {
		if(adminCustomerPage == null) {
			return new AdminCustomerPageObject(driver);
		}
		return adminCustomerPage;
	}
}
