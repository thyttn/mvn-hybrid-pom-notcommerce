package pageObjectLiveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUILiveGuru.UserDashboardPageUI;
import pageUINopCommerce.CommonUI;
import pageUINopCommerce.MyAccountUI;

public class UserDashboardPageObject extends BasePage {

	WebDriver driver;
	public UserDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isCreateAccountSuccessMessageDisplayed() {
		return isElementDisplay(driver, UserDashboardPageUI.CREATE_ACCOUNT_SUCCESS_MSG);
	}
	
}
