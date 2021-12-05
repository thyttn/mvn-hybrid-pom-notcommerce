package pageObject.SourceLab;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static InventoryObject getInventoryPage(WebDriver driver) {
		return new InventoryObject(driver);
	}
	
}
