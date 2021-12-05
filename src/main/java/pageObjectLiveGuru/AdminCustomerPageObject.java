package pageObjectLiveGuru;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUILiveGuru.AdminCustomerPageUI;

public class AdminCustomerPageObject extends BasePage{
	WebDriver driver;

	public AdminCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isResultSearchDisplayed(String nameSearch, String emailSearch) {
		//waitForElementInvisible(driver, AdminCustomerPageUI.LOADING_SEARCH);
		return isElementDisplay(driver, AdminCustomerPageUI.RESULT_SEARCH_BY_COLUMN_NAME_POSITION, nameSearch,emailSearch);
	}

	public void closePopup() {
		waitForElementClickable(driver, AdminCustomerPageUI.INCOME_POPUP);
		clickToElement(driver, AdminCustomerPageUI.INCOME_POPUP);
	}

	public void enterToTextboxAtColumnName(String columnName, String textboxValue) {
		String columnNameIndex = String.valueOf(getFindElementSize(driver, AdminCustomerPageUI.COLUMN_NAME_POSITION,columnName) + 1);
		waitForElementVisible(driver, AdminCustomerPageUI.TEXTBOX_SEARCH_BY_COLUMN_NAME_POSITION,columnNameIndex);
		sendKeyToElement(driver,textboxValue, AdminCustomerPageUI.TEXTBOX_SEARCH_BY_COLUMN_NAME_POSITION, columnNameIndex);
		pressKeyToElement(driver, AdminCustomerPageUI.TEXTBOX_SEARCH_BY_COLUMN_NAME_POSITION,Keys.ENTER, columnNameIndex);
	}
}
