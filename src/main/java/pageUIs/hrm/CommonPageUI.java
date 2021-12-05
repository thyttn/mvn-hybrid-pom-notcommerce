package pageUIs.hrm;

public class CommonPageUI {
	public final static String FIRST_MENU = "//a[@id='menu_pim_viewPimModule']//b[text()='%s']";
	public final static String SECOND_MENU = "//b[text()='%s']/parent::a/following-sibling::ul//a[text()='%s']";
	public final static String THIRD_MENU = "//b[text()='%s']/parent::a/following-sibling::ul//a[text()='%s']//following-sibling::ul//a[text()='%s']";
	public final static String DYNAMIC_MENU_PAGE = "//div[@id='mainMenu']//a[string()='%s']";
	public final static String DYNAMIC_TEXTBOX_ID = "//input[@id='%s']";
	public final static String DYNAMIC_DATE_FIELD_ID = "//input[@id='%s']";
	public final static String DYNAMIC_BUTTON = "//input[@id='%s']";
	public final static String DYNAMIC_CHECKBOX = "//input[@id='%s']";
	public final static String ANY_FIELD = "//*[@id='%s']";
	
	public final static String DYNAMIC_DROPDOWN = "//select[@id='%s']";
	public final static String COLUMN_INDEX_BY_COLUMN_NAME = "//table[@id='%s']//a[text() ='%s']/parent::th/preceding-sibling::th";
	public final static String RESULT_SEARCH_BY_TABLE_ID_AND_COLUMN_INDEX = "//table[@id='%s']//td[%s]/a"; 
	public final static String UPLOAD_FILE = "//input[@id='%s']";
	public final static String SUCCESS_MESSAGE = "//div[@class='message success fadable' and contains(text(),'%s')]";
	public final static String TAB_NAME_IN_PERSONAL_PAGE = "//div[@id='sidebar']//a[string()='%s']";
	public final static String DYNAMIC_FIELD_DISABLED = "//input[@id='%s' and @disabled]";
	
	public final static String WELCOME_ICON = "//a[@id='welcome']"; 
	public final static String LOGOUT_LINK = "//a[text()='Logout']";
	public final static String USERNAME = "//input[@id='txtUsername']";
	public final static String PASSWORD = "//input[@id='txtPassword']";
	public final static String LOGIN_BUTTON = "//input[@id='btnLogin']";
	
}
