package pageObject.hrm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.velocity.runtime.directive.Foreach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.hrm.EmployeeListPageUI;

public class EmployeeListPageObject extends BasePage{
	WebDriver driver;

	public EmployeeListPageObject(WebDriver driver) {
		this.driver = driver;
	}

}