package pageObject.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.hrm.CommonPageUI;
import pageUIs.hrm.MyInfoPageUI;

public class MyInfoPageObject extends BasePage{
	WebDriver driver;

	public MyInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAvatarImage() {
		waitForElementClickable(driver, MyInfoPageUI.AVATAR_IMAGE);
		clickToElement(driver, MyInfoPageUI.AVATAR_IMAGE);
	}



	public boolean isUploadedAvatarDisplayed() {
		waitForElementVisible(driver, MyInfoPageUI.AVATAR_IMAGE);
		int width =Integer.parseInt(getAttributeValue(driver, MyInfoPageUI.AVATAR_IMAGE, "width"));
		int height =Integer.parseInt(getAttributeValue(driver, MyInfoPageUI.AVATAR_IMAGE, "height"));
		return (width != 200) || (height != 200);
	}
}


