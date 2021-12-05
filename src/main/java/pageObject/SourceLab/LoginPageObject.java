package pageObject.SourceLab;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.SourceLab.LoginPageUI;
import pageUINopCommerce.HomePageUI;

public class LoginPageObject extends BasePage{
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUserNameTxt(String userName) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);
	}

	public void enterToPasswordTxt(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public InventoryObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGenerator.getInventoryPage(driver);
	}

	public void sortByNameAToZ() {
		// TODO Auto-generated method stub
		
	}
}
