package pageObjectJquery;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import pageUIJquery.HomePageUI;
import pageUIJquery.UploadFilePageUI;

public class UploadFilePageObject extends BasePage{
	private WebDriver driver;
	UploadFilePageObject uploadFilePage;
	
	public UploadFilePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void uploadFiles(String... filesName) {
		uploadMutipleFiles(driver, UploadFilePageUI.ADD_FILE_BUTTON, filesName);
	}

	public void loadFiles(String... fileName) {
		waitForElementClickable(driver, UploadFilePageUI.START_FILE_BUTTON, fileName);
		clickToElement(driver, UploadFilePageUI.START_FILE_BUTTON, fileName);
	}

	public boolean isUploadFileSuccess(String fileName) {
		return isElementDisplay(driver, UploadFilePageUI.UPLOAD_FILENAME_POSITION, fileName);
	}
}
