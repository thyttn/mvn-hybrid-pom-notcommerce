package pageObjectLiveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUILiveGuru.UserHomePageUI;

public class UserHomePageObject extends BasePage{
	WebDriver driver;
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}	

	public UserLoginPageObject openLoginPage() {
		waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_BOTTON_LINK_LOCATOR);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_BOTTON_LINK_LOCATOR);
		return GenerateObject.getUserLoginPage(driver);
	}

}
