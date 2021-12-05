package generatorManager;

import java.text.Format;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUINopCommerce.CommonUI;
import pageUINopCommerce.MyAccountUI;

public class MyAccountPagesObject extends BasePage{
	private WebDriver driver;
	private static MyAccountPagesObject myAccountPage;
	public MyAccountPagesObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public static MyAccountPagesObject getMyAccountPage(WebDriver driver) {
		if(myAccountPage == null) {
			return new MyAccountPagesObject(driver);
		}
		return myAccountPage;
	}
	
	public String getSuccessLoginMessage() {
		waitForElementVisible(driver, MyAccountUI.SUCCESS_LOGIN_MESSAGE_LOCATOR);
		return getTextValue(driver, MyAccountUI.SUCCESS_LOGIN_MESSAGE_LOCATOR);
	}
	
// Cach 1
	
	public MyAccountPagesObject openAddressesPage() {
		waitForElementClickable(driver,CommonUI.ADDRESSES_LINK_LOCATOR);
		clickToElement(driver, CommonUI.ADDRESSES_LINK_LOCATOR);
		return getMyAccountPage(driver);
	}
	public MyAccountPagesObject openRewardPointsPage() {
		waitForElementClickable(driver, CommonUI.REWARD_POINTS_LINK_LOCATOR);
		clickToElement(driver, CommonUI.REWARD_POINTS_LINK_LOCATOR);
		return getMyAccountPage(driver);
	}
	public MyAccountPagesObject openOrdersPage() {
		waitForElementClickable(driver, CommonUI.ORDERS_LINK_LOCATOR);
		clickToElement(driver, CommonUI.ORDERS_LINK_LOCATOR);
		return getMyAccountPage(driver);
	}
	public MyAccountPagesObject openCustomerInforPage() {
		waitForElementClickable(driver, CommonUI.CUSTOMER_INFOR_LINK_LOCATOR);
		clickToElement(driver, CommonUI.CUSTOMER_INFOR_LINK_LOCATOR);
		return getMyAccountPage(driver);
	}
	
	//Cach 2
	public MyAccountPagesObject openGeneralPage(String value) {
		waitForElementClickable(driver, CommonUI.GENERAL_LINK_LOCATOR, value);
		clickToElement(driver, CommonUI.GENERAL_LINK_LOCATOR,  value);
		return getMyAccountPage(driver);
	}
	
}
