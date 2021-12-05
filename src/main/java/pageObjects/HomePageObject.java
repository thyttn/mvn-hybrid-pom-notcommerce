package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUINopCommerce.HomePageUI;

public class HomePageObject extends BasePage{
	private WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
	}
	public void clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
	}
	public boolean isHomePageSlider() {
		waitForElementVisible(driver, HomePageUI.HOME_PAGE_LOGO);
		return isElementDisplay(driver, HomePageUI.HOME_PAGE_LOGO);
	}
}
