package pageFactoryNopcommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUINopCommerce.HomePageUI;

public class HomePageObject extends BasePageFactory{
	private WebDriver driver;
	
	@FindBy(xpath="//a[@class='ico-register']")
	WebElement registerLink;
	
	@FindBy(xpath="//a[@class='ico-login']")
	WebElement loginLink;
	
	@FindBy(xpath="//div[@class='nivoSlider']")
	WebElement homepageLogo;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void clickToRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickToElement(registerLink);
	}
	public void clickToLoginLink() {
		waitForElementClickable(driver, loginLink);
		clickToElement(loginLink);
	}
	public boolean isHomePageSlider() {
		waitForElementVisible(driver, homepageLogo);
		return isElementDisplay(homepageLogo);
	}
}
