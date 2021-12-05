package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUINopCommerce.LoginPageUI;
import pageUINopCommerce.RegisterPageUI;

public class LoginPageObject extends BasePage{
	private WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_LOCATOR);
		sendKeyToElement(driver, LoginPageUI.EMAIL_LOCATOR, email);
	}
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_LOCATOR);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_LOCATOR, password);
	}
	public void clickToLoginButton() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON_LOCATOR);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON_LOCATOR);
	}
}
