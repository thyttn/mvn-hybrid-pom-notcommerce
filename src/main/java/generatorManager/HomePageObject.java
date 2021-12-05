package generatorManager;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageObjectLiveGuru.UserRegisterPageObject;
import pageUINopCommerce.HomePageUI;

public class HomePageObject extends BasePage{
	private WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click to Register link")
	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return GenerateManagerObject.getRegisterPage(driver);
	}
	
	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return GenerateManagerObject.getLoginPage(driver);
	}
	
	public boolean isHomePageSlider() {
		waitForElementVisible(driver, HomePageUI.HOME_PAGE_LOGO);
		return isElementDisplay(driver, HomePageUI.HOME_PAGE_LOGO);
	}

	public MyAccountPagesObject clickToMyAccountButton() {
		waitForElementClickable(driver, HomePageUI.MYACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MYACCOUNT_LINK);
		return GenerateManagerObject.getCustomerInforPage(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.MYACCOUNT_LINK);
		return isElementDisplay(driver, HomePageUI.MYACCOUNT_LINK);
	}
}
