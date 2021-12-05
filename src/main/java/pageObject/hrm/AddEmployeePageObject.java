package pageObject.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.hrm.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage{
	WebDriver driver;

	public AddEmployeePageObject(WebDriver driver) {
		this.driver = driver;
	}
}
