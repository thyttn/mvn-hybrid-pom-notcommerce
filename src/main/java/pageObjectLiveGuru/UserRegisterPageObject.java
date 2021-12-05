package pageObjectLiveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUILiveGuru.UserRegisterPageUI;
import pageUINopCommerce.CommonUI;
import pageUINopCommerce.MyAccountUI;

public class UserRegisterPageObject extends BasePage {

	WebDriver driver;
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void enterToFirstName(String firstName) {
		waitForElementVisible(driver, UserRegisterPageUI.GENERAL_REGISTER_TXT, "firstname");
		sendKeyToElement(driver, firstName,UserRegisterPageUI.GENERAL_REGISTER_TXT, "firstname");
	}
	public void enterToLastName(String lastName) {
		waitForElementVisible(driver, UserRegisterPageUI.GENERAL_REGISTER_TXT, "lastname");
		sendKeyToElement(driver, lastName ,UserRegisterPageUI.GENERAL_REGISTER_TXT, "lastname");
	}
	public void enterToEmail(String email) {
		waitForElementVisible(driver, UserRegisterPageUI.GENERAL_REGISTER_TXT, "email_address");
		sendKeyToElement(driver, email ,UserRegisterPageUI.GENERAL_REGISTER_TXT, "email_address");
	}
	public void enterToPassword(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.GENERAL_REGISTER_TXT, "password");
		sendKeyToElement(driver, password ,UserRegisterPageUI.GENERAL_REGISTER_TXT, "password");
	}
	public void enterToConfirmPassword(String confirmPassword) {
		waitForElementVisible(driver, UserRegisterPageUI.GENERAL_REGISTER_TXT, "confirmation");
		sendKeyToElement(driver, confirmPassword ,UserRegisterPageUI.GENERAL_REGISTER_TXT, "confirmation");
	}
	public UserDashboardPageObject clickToLoginButton() {
		waitForElementVisible(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
		return GenerateObject.getUserDashboadPage(driver);
	}
	
}
