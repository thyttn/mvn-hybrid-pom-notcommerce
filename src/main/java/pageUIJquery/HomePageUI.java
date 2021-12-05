package pageUIJquery;

public class HomePageUI {
	public final static String PAGE_LINK_BY_NAME = "//li[@class='qgrd-pagination-page']//a[text()='%s']";
	public final static String PAGE_LINK_ACTIVE_BY_NAME = "//li[@class='qgrd-pagination-page']//a[contains(@class,'active') and text()='%s']";
	public final static String TEXT_SEARCH = "//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
	public final static String RESULT_SEARCH = "//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country'"
			+ " and text()='%s']/following-sibling::td[@data-key='males' and text()='%s']"
			+ "/following-sibling::td[@data-key='total' and text()='%s']";
	public final static String ACTION_BUTTON_ROW = "//td[@data-key='country' and text()='%s']/preceding-sibling::td//button[contains(@class,'%s')]";

}
