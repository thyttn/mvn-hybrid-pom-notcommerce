package generatorManager;

import org.openqa.selenium.WebDriver;

public class GenerateManagerObject {
	private static HomePageObject homePage;
	private static LoginPageObject loginPage;
	private static RegisterPageObject registerPage;
	private static MyAccountPagesObject customerInforPage;
	private static AddressesPageObject addressesPage;
	private static RewardPointsPageObject rewardPointsPage;
	private static OrdersPageObject ordersPage;
	
	
	private GenerateManagerObject(){
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
//		if(homePage == null) {
//			return new HomePageObject(driver);
//		}
			return new HomePageObject(driver);
	}
	public static LoginPageObject getLoginPage(WebDriver driver) {
//		if(loginPage == null) {
//			return new LoginPageObject(driver);
//		}
			return new LoginPageObject(driver);
	}
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
//		if(registerPage == null) {
//			return new RegisterPageObject(driver);
//		}
			return new RegisterPageObject(driver);
	}
	public static MyAccountPagesObject getCustomerInforPage(WebDriver driver) {
//		if(customerInforPage == null) {
//			return new MyAccountPagesObject(driver);
//		}
		return new MyAccountPagesObject(driver);
	}
	public static AddressesPageObject getAddressesPage(WebDriver driver) {
//		if(addressesPage == null) {
//			return new AddressesPageObject(driver);
//		}
		return new AddressesPageObject(driver);
	}
	public static RewardPointsPageObject getRewardPointsPage(WebDriver driver) {
//		if(rewardPointsPage == null) {
//			return new RewardPointsPageObject(driver);
//		}
		return new RewardPointsPageObject(driver);
	}
	public static OrdersPageObject getOrdersPage(WebDriver driver) {
//		if(ordersPage == null) {
//			return new OrdersPageObject(driver);
//		}
		return new OrdersPageObject(driver);
	}
}
