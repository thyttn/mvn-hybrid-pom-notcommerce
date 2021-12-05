package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;
import pageUINopCommerce.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToGender() {
		waitForElementClickable(driver, RegisterPageUI.GENDER_LOCATOR);
		clickToElement(driver, RegisterPageUI.GENDER_LOCATOR);
	}
	public void enterToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_LOCATOR);
		sendKeyToElement(driver, RegisterPageUI.FIRSTNAME_LOCATOR, firstName);
	}
	public void enterToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_LOCATOR);
		sendKeyToElement(driver, RegisterPageUI.LASTNAME_LOCATOR, lastName);
	}
	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_LOCATOR);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_LOCATOR, email);
	}
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_LOCATOR);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_LOCATOR, password);
	}
	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRMPASSWORD_LOCATOR);
		sendKeyToElement(driver, RegisterPageUI.CONFIRMPASSWORD_LOCATOR, confirmPassword);
	}
	public void clickToRegisterButton() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_BUTTON_LOCATOR);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON_LOCATOR);
	}
	public boolean verifySuccess() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_REGISTER_LOCATOR);
		return isElementDisplay(driver, RegisterPageUI.SUCCESS_REGISTER_LOCATOR);
	}
	public void clickToLogoutLink() {
		waitForElementVisible(driver, RegisterPageUI.LOGOUT_BUTTON_LOCATOR);
		clickToElement(driver, RegisterPageUI.LOGOUT_BUTTON_LOCATOR);
	}
}
