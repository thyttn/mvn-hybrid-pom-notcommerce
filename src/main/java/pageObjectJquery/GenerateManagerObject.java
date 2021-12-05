package pageObjectJquery;

import org.openqa.selenium.WebDriver;

public class GenerateManagerObject {
	private static HomePageObject homePage;
	private static UploadFilePageObject uploadFilePage;
	
	private GenerateManagerObject(){
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
		if(homePage == null) {
			return new HomePageObject(driver);
		}
			return homePage;
	}
	
	public static UploadFilePageObject getUploadFilePage(WebDriver driver) {
		if(uploadFilePage == null) {
			return new UploadFilePageObject(driver);
		}
		return uploadFilePage;
	}
	
}
