package commons;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.hrm.DashboardPageObject;
import pageObject.hrm.LoginPageObject;
import pageObject.hrm.MyInfoPageObject;
import pageObject.hrm.PageGenerator;
import pageUIs.hrm.CommonPageUI;
import pageUIs.hrm.MyInfoPageUI;

/**
 * @author NGOC-THY
 *
 */
/**
 * @author NGOC-THY
 *
 */
/**
 * @author NGOC-THY
 *
 */
public  class BasePage {
	private Actions action;
	private JavascriptExecutor jsExecutor;
	private Select select;
	private Alert alert;
	private WebDriverWait explicitWait;
	private int longTimeout = GlobalConstants.LONG_TIMEOUT;
	
	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void getBackPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void getForwardPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void getRefreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		alert.dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		return alert.getText();
	}

	public void sendKeytoAlert(WebDriver driver, String textValue) {
		alert = waitAlertPresence(driver);
		alert.sendKeys(textValue);
	}
	
	public void switchToWindowById(WebDriver driver, String parentId) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for (String runWindowId : allWindows) {
			if(!runWindowId.equals(parentId)) {
				driver.switchTo().window(runWindowId);
				break;
			}
		}
	}
	
	
	public void switchToWindowByTitle(WebDriver driver, String expectedWindowTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for (String actualWindowID : allWindows) {
			driver.switchTo().window(actualWindowID);
			String actualWindowTitle = driver.getTitle();
			if(actualWindowTitle.equals(expectedWindowTitle)) {
				break;
			}
		}
	}
	
	public void closeAllWindowExceptParent(WebDriver driver, String parentWindowTitle) {
		Set<String> allWindowIDs  = driver.getWindowHandles();
		
		for (String windowID : allWindowIDs) {
			driver.switchTo().window(windowID);
			String windowTitle = driver.getTitle();
			if(!windowTitle.equals(parentWindowTitle)) {
				driver.close();
			}
		}
	}

	public By getByXpath(String locator, String... values) {
		return By.xpath(castRestparameter(locator, values));
	}
	public By getByXpath(String locator) {
		return By.xpath(locator);
	}
	public WebElement getFindElement(WebDriver driver, String locator, String... values) {
		return driver.findElement(getByXpath(castRestparameter(locator, values)));
	}
	public WebElement getFindElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}
	
	public List<WebElement> getFindElements(WebDriver driver,String locator) {
		return driver.findElements(getByXpath(locator));
	}
	
	/** get all text value list from items list
	 * @param driver
	 * @param locator
	 */
	public ArrayList<String> getTextValueList(WebDriver driver,String locator) {
		List<WebElement> locatorItemsList = driver.findElements(getByXpath(locator));
		ArrayList<String> itemsList = new ArrayList<String>();
		String item = "";
		for (WebElement locatorItem : locatorItemsList) {
			item = locatorItem.getText();
			itemsList.add(item);
		}
		return itemsList;
	}

	public void clickToElement(WebDriver driver,String locator) {
		getFindElement(driver, locator ).click();
	}
	public void clickToElement(WebDriver driver,String locator, String... values) {
		getFindElement(driver, castRestparameter(locator, values)).click();
	}
	public void sendKeyToElement(WebDriver driver,String locator, String valueSenkey) {
		getFindElement(driver,locator ).clear();
		getFindElement(driver,locator ).sendKeys(valueSenkey);
	}
	public void sendKeyToElement(WebDriver driver, String locator, String valueSenkey, String... expectedValues ) {
		getFindElement(driver, castRestparameter(locator, expectedValues)).clear();
		getFindElement(driver, castRestparameter(locator, expectedValues)).sendKeys(valueSenkey);
	}
	
	public void selectItemInDropdown(WebDriver driver,String locator,String textValue, String...expectedValues ) {
		//waitForElementClickable(driver, castRestparameter(locator, expectedValues));
		select = new Select(getFindElement(driver, castRestparameter(locator, expectedValues)));
		select.selectByVisibleText(textValue);
	}
	
	public void selectItemInDropdown(WebDriver driver,String locator,String textValue) {
		select = new Select(getFindElement(driver, locator));
		select.selectByVisibleText(textValue);
	}
	
	public String getSelectItemInDropdown(WebDriver driver,String locator, String...expectedValues) {
		select = new Select(getFindElement(driver, castRestparameter(locator, expectedValues)));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMutiple(WebDriver driver,String locator) {
		select = new Select(getFindElement(driver, locator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getFindElement(driver, parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	
	public void sleepInSecond(long timeOutInSecond ) {
		try {
			Thread.sleep(timeOutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getAttributeValue(WebDriver driver,String locator, String attributeName, String...expectedValues) {
		return getFindElement(driver, castRestparameter(locator, expectedValues)).getAttribute(attributeName);
	}
	public String getAttributeValue(WebDriver driver,String locator, String attributeName) {
		return getFindElement(driver, locator).getAttribute(attributeName);
	}
	public String getTextValue(WebDriver driver,String locator, String...expectedVales) {
		return getFindElement(driver, castRestparameter(locator, expectedVales)).getText();
	}
	public String getTextValue(WebDriver driver,String locator) {
		return getFindElement(driver,locator).getText();
	}


	
	public String getCssValue(WebDriver driver,String locator, String attributeName) {
		return getFindElement(driver, locator).getCssValue(locator);
	}
	
	public String convertRgbaToHexa(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getFindElementSize(WebDriver driver,String locator) {
		return getFindElements(driver, locator).size();
	}
	public int getFindElementSize(WebDriver driver,String locator, String...values) {
		return getFindElements(driver, castRestparameter(locator, values)).size();
	}
	
	public void checkToCheckBoxOrRadio(WebDriver driver,String locator) {
		if(!getFindElement(driver, locator).isSelected()) {
			getFindElement(driver, locator).click();
		}
	}

	public void uncheckTheCheckbox(WebDriver driver,String locator) {
		if(getFindElement(driver, locator).isSelected()) {
			getFindElement(driver, locator).click();
		}
	}
	
	public boolean isElementDisplay(WebDriver driver,String locator) {
		return getFindElement(driver, locator).isDisplayed();
	}
	public boolean isElementDisplay(WebDriver driver,String locator,String...values) {
		return getFindElement(driver, castRestparameter(locator, values)).isDisplayed();
	}
	public boolean isElementSelected(WebDriver driver,String locator) {
		return getFindElement(driver, locator).isSelected();
	}
	public boolean isElementEnable(WebDriver driver,String locator) {
		return getFindElement(driver, locator).isEnabled();
	}
	public boolean isElementEnable(WebDriver driver,String locator, String...values) {
		return getFindElement(driver, castRestparameter(locator, values)).isEnabled();
	}

	public void switchToIframe(WebDriver driver,String locator) {
		driver.switchTo().frame(getFindElement(driver, locator));
	}
	
	public void switchToDefaultContent(WebDriver driver,String locator) {
		driver.switchTo().defaultContent();
	}
	
	public void doubleClickToElement(WebDriver driver,String locator) {
		action = new Actions(driver);
		action.doubleClick().perform();;
	}
	public void hoverMouseToElement(WebDriver driver,String locator) {
		action = new Actions(driver);
		action.moveToElement(getFindElement(driver, locator)).perform();
	}
	public void hoverMouseToElement(WebDriver driver,String locator,String...values) {
		action = new Actions(driver);
		action.moveToElement(getFindElement(driver, castRestparameter(locator, values))).perform();
	}
	public void rightClickToElement(WebDriver driver,String locator) {
		action = new Actions(driver);
		action.contextClick(getFindElement(driver, locator)).perform();
	}
	public void dragAndDropElement(WebDriver driver,String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getFindElement(driver, sourceLocator),getFindElement(driver, targetLocator)).perform();
	}
	
	public void pressKeyToElement(WebDriver driver,String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getFindElement(driver, locator), key).perform();
		//key  = Keys.TAB...
	}
	public void pressKeyToElement(WebDriver driver,String locator, Keys key,String...values) {
		action = new Actions(driver);
		action.sendKeys(getFindElement(driver, locator, values), key).perform();
		//key  = Keys.TAB...
	}
	
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getFindElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getFindElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getFindElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getFindElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getFindElement(driver, locator));
	}

	public boolean isJQueryAJaxLoadedSuccess(WebDriver driver){
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active == 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
	
 	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getFindElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getFindElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator, values)));
	}
	public void waitForElementClickable(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator, values)));
	}
	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}
	public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator, values)));
	}
	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}
	public void waitForAlertPresence(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public String castRestparameter(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}
	
	public void uploadMutipleFiles(WebDriver driver, String locator, String... fileNames) {
		String fullFilePath = "";
		String projectPath= System.getProperty("user.dir") + File.separator +"uploadFiles" + File.separator;
		for (String fileName : fileNames) {
			fullFilePath += projectPath + fileName + "\n";
		}
		fullFilePath = fullFilePath.trim();
		System.out.println("location; " + fullFilePath );
		getFindElement(driver, locator).sendKeys(fullFilePath);
		sleepInSecond(2);
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setAllCookies(WebDriver driver,Set<Cookie> allCookies ) {
		for(Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}
	


	/**Open main menu link
	 * @author NGOC-THY
	 * @param driver
	 * @param mainMenuName
	 */
	public void openMainMenu(WebDriver driver, String mainMenuName) {
		waitForElementClickable(driver, CommonPageUI.DYNAMIC_MENU_PAGE, mainMenuName);
		clickToElement(driver, CommonPageUI.DYNAMIC_MENU_PAGE, mainMenuName);
		
		isJQueryAJaxLoadedSuccess(driver);
	}
	/** Open sub menu link
	 * @author NGOC-THY
	 * @param driver
	 * @param mainMenuName
	 * @param subMenuName
	 */
	public void openSubMenu(WebDriver driver, String mainMenuName, String subMenuName) {
		waitForElementVisible(driver, CommonPageUI.DYNAMIC_MENU_PAGE, mainMenuName);
		clickToElement(driver, CommonPageUI.DYNAMIC_MENU_PAGE, mainMenuName);
		isJQueryAJaxLoadedSuccess(driver);
		
		waitForElementClickable(driver, CommonPageUI.DYNAMIC_MENU_PAGE, subMenuName);
		clickToElement(driver, CommonPageUI.DYNAMIC_MENU_PAGE, subMenuName);
		isJQueryAJaxLoadedSuccess(driver);
	}
	
	/** Open third sub menu link
	 * @author NGOC-THY
	 * @param driver
	 * @param mainMenuName
	 * @param subMenuName
	 * @param thirdMenuName
	 */
	public void openThirdMenu(WebDriver driver, String mainMenuName, String subMenuName, String thirdMenuName) {
		waitForElementClickable(driver, CommonPageUI.DYNAMIC_MENU_PAGE, mainMenuName);
		clickToElement(driver, CommonPageUI.DYNAMIC_MENU_PAGE, mainMenuName);
		isJQueryAJaxLoadedSuccess(driver);
		
		waitForElementClickable(driver, CommonPageUI.DYNAMIC_MENU_PAGE, subMenuName);
		clickToElement(driver, CommonPageUI.DYNAMIC_MENU_PAGE, subMenuName);
		
		waitForElementClickable(driver, CommonPageUI.DYNAMIC_MENU_PAGE, thirdMenuName);
		clickToElement(driver, CommonPageUI.DYNAMIC_MENU_PAGE, thirdMenuName);
		isJQueryAJaxLoadedSuccess(driver);
	}
	
	/** enter to textbox by ID
	 * @author NGOC-THY
	 * @param driver
	 * @param textboxId
	 * @param valueText
	 */
	public void enterToTextboxById(WebDriver driver, String valueText, String textboxId) {
		waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_ID, textboxId);
		sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_ID, valueText, textboxId);
	}
	
	/** enter value to date field by ID
	 * @author NGOC-THY
	 * @param driver
	 * @param valueText
	 * @param dateFieldId
	 */
	public void enterToDateFieldById(WebDriver driver, String valueText, String dateFieldId) {
		waitForElementVisible(driver, CommonPageUI.DYNAMIC_DATE_FIELD_ID, dateFieldId);
		sendKeyToElement(driver, CommonPageUI.DYNAMIC_DATE_FIELD_ID, valueText, dateFieldId);
		pressKeyToElement(driver, CommonPageUI.DYNAMIC_DATE_FIELD_ID, Keys.ENTER, dateFieldId);
	}
	
	/** Click to button by ID
	 * @author NGOC-THY
	 * @param driver
	 * @param buttonId
	 */
	public void clickToButtonById(WebDriver driver, String buttonId) {
		waitForElementClickable(driver, CommonPageUI.DYNAMIC_BUTTON, buttonId);
		clickToElement(driver, CommonPageUI.DYNAMIC_BUTTON, buttonId);
	}
	
	/** get value of textbox by ID
	 * @author NGOC-THY
	 * @param driver
	 * @param textboxId
	 */
	public String getValueOfTextboxById(WebDriver driver, String textboxId) {
		waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_ID, textboxId);
		return getAttributeValue(driver, CommonPageUI.DYNAMIC_TEXTBOX_ID, "value", textboxId);
	}
	
	/** select value in dropdown list by id
	 * @author NGOC-THY
	 * @param driver
	 * @param dropdownId
	 * @param textValue
	 */
	public void selectValueInDropdownById(WebDriver driver, String dropdownId, String textValue) {
		waitForElementClickable(driver, CommonPageUI.DYNAMIC_DROPDOWN, dropdownId);
		selectItemInDropdown(driver, CommonPageUI.DYNAMIC_DROPDOWN, textValue, dropdownId);
	}
	
	/** verify result search by table id and column name
	 * @author NGOC-THY
	 * @param driver
	 * @param tableId
	 * @param columnName
	 */
	public String verifyResultSearchByTableIdAndColumnName(WebDriver driver, String tableId, String columnName) {
		int columnIndex = getFindElementSize(driver, CommonPageUI.COLUMN_INDEX_BY_COLUMN_NAME , tableId , columnName) +1;
		return getTextValue(driver, CommonPageUI.RESULT_SEARCH_BY_TABLE_ID_AND_COLUMN_INDEX, tableId , String.valueOf(columnIndex));
	}		
	
	/** Logout HRM system
	 * @param driver
	 */
	public LoginPageObject logoutHRM(WebDriver driver) {
		waitForElementClickable(driver, CommonPageUI.WELCOME_ICON);
		clickToElement(driver, CommonPageUI.WELCOME_ICON);
		
		waitForElementClickable(driver, CommonPageUI.LOGOUT_LINK);
		clickToElement(driver, CommonPageUI.LOGOUT_LINK);
		return PageGenerator.getLoginPage(driver);
	}
	
	/** Login HRM system
	 * @param driver
	 * @param userName
	 * @param password
	 */
	public DashboardPageObject loginSystem(WebDriver driver,String userName, String password) {
		waitForElementVisible(driver, CommonPageUI.USERNAME);
		sendKeyToElement(driver, CommonPageUI.USERNAME, userName);
		sendKeyToElement(driver, CommonPageUI.PASSWORD, password);
		
		clickToElement(driver, CommonPageUI.LOGIN_BUTTON);
		return PageGenerator.getDashboardPage(driver);
	}
	
	/** Upload avatar image
	 * @param driver
	 * @param uploadBoxId
	 * @param pathToFile
	 */
	public void uploadFileToHRM(WebDriver driver, String uploadBoxId, String pathToFile) {
		waitForElementVisible(driver, CommonPageUI.UPLOAD_FILE, uploadBoxId );
		getFindElement(driver, CommonPageUI.UPLOAD_FILE, uploadBoxId).sendKeys(pathToFile);
	}
	
	/** verify success message is displayed
	 * @param driver
	 * @param successMessage
	 */
	public boolean isSuccessMessageDisplayed(WebDriver driver, String successMessage) {
		return isElementDisplay(driver, CommonPageUI.SUCCESS_MESSAGE, successMessage );
	}
	
	/** Open Tab in left side by tab name
	 * @param driver
	 * @param tabName
	 */
	public MyInfoPageObject openTabNameInMyInforPage(WebDriver driver, String tabName) {
		waitForElementClickable(driver, CommonPageUI.TAB_NAME_IN_PERSONAL_PAGE, tabName);
		clickToElement(driver, CommonPageUI.TAB_NAME_IN_PERSONAL_PAGE, tabName);
		return PageGenerator.getMyInfoPage(driver);
	}
	
	/** verify if a field is enable
	 * @param driver
	 * @param fieldId
	 */
	public boolean isEnableField(WebDriver driver, String fieldId) {
		return isElementEnable(driver, CommonPageUI.ANY_FIELD, fieldId );
	}

	/** Get attribute value by Id of field
	 * @param driver
	 * @param attributeName
	 * @param fieldId
	 */
	public String getAttributeValueById(WebDriver driver, String attributeName, String fieldId) {
		waitForElementVisible(driver, CommonPageUI.ANY_FIELD, fieldId);
		return getFindElement(driver, CommonPageUI.ANY_FIELD, fieldId).getAttribute(attributeName);
	}
	
	public String getSelectedItemValueById(WebDriver driver, String fieldId) {
		waitForElementVisible(driver, CommonPageUI.ANY_FIELD, fieldId);
		select = new Select(getFindElement(driver, CommonPageUI.ANY_FIELD, fieldId));
		return select.getFirstSelectedOption().getText();
	}
}
