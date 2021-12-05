package pageUILiveGuru;

public class AdminCustomerPageUI {
	public final static String INCOME_POPUP = "//a[@title='close']";
	public final static String EMAIL_TXT_SEARCH = "//input[@name='email']";
	public final static String LOADING_SEARCH = "//p[@id='loading_mask_loader']";
	public final static String TEXTBOX_SEARCH_BY_COLUMN_NAME_POSITION = "//tr[@class='filter']/th[%s]//input";
	public final static String COLUMN_NAME_POSITION = "//a[@name='%s']/ancestor::th/preceding-sibling::th";
	public final static String RESULT_SEARCH_BY_COLUMN_NAME_POSITION = "//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]";
	
}
