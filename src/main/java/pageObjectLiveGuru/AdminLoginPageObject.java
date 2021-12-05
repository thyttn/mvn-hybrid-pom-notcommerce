package pageObjectLiveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUILiveGuru.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{
	WebDriver driver;
	
	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUserName(String userName) {
		waitForElementVisible(driver, AdminLoginPageUI.USERNAME_TXT);
		sendKeyToElement(driver, AdminLoginPageUI.USERNAME_TXT, userName);
	}

	public void enterToPassword(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TXT);
		sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TXT, password);
	}

	public AdminCustomerPageObject clickToLoginButton() {
		waitForElementVisible(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return GenerateObject.getAdminCustomerPage(driver);
	}
}
