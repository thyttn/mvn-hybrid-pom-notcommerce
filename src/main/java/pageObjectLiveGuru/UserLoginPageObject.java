package pageObjectLiveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUILiveGuru.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	WebDriver driver;
	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void enterEmailTxt(String email) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_LOCATOR);
		sendKeyToElement(driver, UserLoginPageUI.EMAIL_LOCATOR, email);
	}

	public void clickToLoginButton() {
		waitForElementVisible(driver, UserLoginPageUI.LOGIN_BUTTON_LOCATOR);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON_LOCATOR);
	}

	public void enterPasswordTxt(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_LOCATOR);
		sendKeyToElement(driver, UserLoginPageUI.PASSWORD_LOCATOR, password);
	}

	public String getEmptyEmailErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.EMPTY_EMAIL_MESSAGE_LOCATOR);
		return getTextValue(driver, UserLoginPageUI.EMPTY_EMAIL_MESSAGE_LOCATOR);
	}

	public String getEmptyPasswordErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.EMPTY_PASSWORD_MESSAGE_LOCATOR);
		return getTextValue(driver, UserLoginPageUI.EMPTY_PASSWORD_MESSAGE_LOCATOR);
	}

	public String getInvalidEmailErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.INVALID_EMAIL_MESSAGE_LOCATOR);
		return getTextValue(driver, UserLoginPageUI.INVALID_EMAIL_MESSAGE_LOCATOR);
	}

	public String getInvalidPasswordErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.INVALID_PASSWORD_MESSAGE_LOCATOR);
		return getTextValue(driver, UserLoginPageUI.INVALID_PASSWORD_MESSAGE_LOCATOR);
	}

	public void refreshLoginPage(WebDriver driver) {
		getRefreshPage(driver);
	}

	public String getIncorrectEmailOrPassword() {
		waitForElementVisible(driver, UserLoginPageUI.INCORRECT_EMAIL_PASSWORD_MESSAGE_LOCATOR);
		return getTextValue(driver, UserLoginPageUI.INCORRECT_EMAIL_PASSWORD_MESSAGE_LOCATOR);
	}


	public UserRegisterPageObject openRegisterPage() {
		waitForElementClickable(driver, UserLoginPageUI.CREATE_ACCOUNT_BUTTON);
		clickToElement(driver, UserLoginPageUI.CREATE_ACCOUNT_BUTTON);
		return GenerateObject.getUserRegisterPage(driver);
	}

}
