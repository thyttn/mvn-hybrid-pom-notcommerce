package generatorManager;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUINopCommerce.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Choose gender")
	public void clickToGender() {
		waitForElementClickable(driver, RegisterPageUI.GENDER_LOCATOR);
		clickToElement(driver, RegisterPageUI.GENDER_LOCATOR);
	}
	
	@Step("Enter to firstname textbox with value {0}")
	public void enterToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_LOCATOR);
		sendKeyToElement(driver, RegisterPageUI.FIRSTNAME_LOCATOR, firstName);
	}
	
	@Step("Enter to lastname textbox with value {0}")
	public void enterToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_LOCATOR);
		sendKeyToElement(driver, RegisterPageUI.LASTNAME_LOCATOR, lastName);
	}
	
	@Step("Enter to firstname textbox with value {0}")
	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_LOCATOR);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_LOCATOR, email);
	}
	
	@Step("Enter to firstname textbox with value {0}")
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_LOCATOR);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_LOCATOR, password);
	}
	
	@Step("Choose gender")
	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRMPASSWORD_LOCATOR);
		sendKeyToElement(driver, RegisterPageUI.CONFIRMPASSWORD_LOCATOR, confirmPassword);
	}
	
	@Step("Choose gender")
	public void clickToRegisterButton() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_BUTTON_LOCATOR);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON_LOCATOR);
	}
	public boolean verifySuccess() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_REGISTER_LOCATOR);
		return isElementDisplay(driver, RegisterPageUI.SUCCESS_REGISTER_LOCATOR);
	}
	public HomePageObject clickToLogoutLink() {
		waitForElementVisible(driver, RegisterPageUI.LOGOUT_BUTTON_LOCATOR);
		clickToElement(driver, RegisterPageUI.LOGOUT_BUTTON_LOCATOR);
		return GenerateManagerObject.getHomePage(driver);
	}
}
