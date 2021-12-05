package pageFactoryNopcommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUINopCommerce.LoginPageUI;
import pageUINopCommerce.RegisterPageUI;

public class LoginPageObject extends BasePageFactory{
	private WebDriver driver;
	
	@FindBy(xpath ="//input[@id='Email']")
	WebElement emailElement;
	
	@FindBy(xpath ="//input[@id='Password']")
	WebElement passwordElement;
	
	@FindBy(xpath ="//button[@class='button-1 login-button']")
	WebElement loginButtonElement;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, emailElement);
		sendKeyToElement(emailElement, email);
	}
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordElement);
		sendKeyToElement(passwordElement, password);
	}
	public void clickToLoginButton() {
		waitForElementVisible(driver, loginButtonElement);
		clickToElement(loginButtonElement);
	}
}
