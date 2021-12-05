package pageObjectJquery;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import pageUIJquery.HomePageUI;

public class HomePageObject extends BasePage{
	private WebDriver driver;
	HomePageObject homePage;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openPagingByName(String pageName) {
		waitForElementClickable(driver, HomePageUI.PAGE_LINK_BY_NAME, pageName);
		clickToElement(driver, HomePageUI.PAGE_LINK_BY_NAME, pageName);
	}

	public boolean isPagingActiveByName(String pageName) {
		return isElementDisplay(driver, HomePageUI.PAGE_LINK_ACTIVE_BY_NAME, pageName);
	}

	public void enterToTableTextSearch(String searchByCountryValue) {
		waitForElementVisible(driver, HomePageUI.TEXT_SEARCH, "Country");
		sendKeyToElement(driver, HomePageUI.TEXT_SEARCH, searchByCountryValue,"Country");
		pressKeyToElement(driver,  HomePageUI.TEXT_SEARCH, Keys.ENTER, "Country");
	}

	public boolean isSearchingByCountry(String femaleValue, String countryValue, String malesValue, String totalValue) {
		return isElementDisplay(driver, HomePageUI.RESULT_SEARCH, femaleValue,countryValue,malesValue,totalValue);
	}

	public void removeRow(String countryValue, String action) {
		getRefreshPage(driver);
		waitForElementVisible(driver, HomePageUI.ACTION_BUTTON_ROW, countryValue, action);
		clickToElement(driver, HomePageUI.ACTION_BUTTON_ROW, countryValue, action);
	}
}
