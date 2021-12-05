package pageFactoryNopcommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import commons.BasePageFactory;
import pageUINopCommerce.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory {
	private WebDriver driver;
	
	@FindBy(xpath="//input[@id='gender-male']")
	WebElement genderElement;
	
	@FindBy(xpath="//input[@id='FirstName']")
	WebElement firstNameElement;
	
	@FindBy(xpath="//input[@id='LastName']")
	WebElement lastNameElement;
	
	@FindBy(xpath="//input[@id='Email']")
	WebElement emailElement;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement passwordElement;
	
	@FindBy(xpath="//input[@id='ConfirmPassword']")
	WebElement confirmPasswordElement;
	
	@FindBy(xpath="//button[text()='Register']")
	WebElement registerButtonElement;
	
	@FindBy(xpath="//div[@class='result' and text()='Your registration completed']")
	WebElement successRegisterElement;
	
	@FindBy(xpath="//a[@class='ico-logout']")
	WebElement logoutButtonElement;
	
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void clickToGender() {
		waitForElementClickable(driver, genderElement);
		clickToElement(genderElement);
	}
	public void enterToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameElement);
		sendKeyToElement(firstNameElement, firstName);
	}
	public void enterToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameElement);
		sendKeyToElement(lastNameElement, lastName);
	}
	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, emailElement);
		sendKeyToElement(emailElement, email);
	}
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordElement);
		sendKeyToElement(passwordElement, password);
	}
	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordElement);
		sendKeyToElement(confirmPasswordElement, confirmPassword);
	}
	public void clickToRegisterButton() {
		waitForElementVisible(driver, registerButtonElement);
		clickToElement(registerButtonElement);
	}
	public boolean verifySuccess() {
		waitForElementVisible(driver, successRegisterElement);
		return isElementDisplay(successRegisterElement);
	}
	public void clickToLogoutLink() {
		waitForElementVisible(driver, logoutButtonElement);
		clickToElement(logoutButtonElement);
	}
}
