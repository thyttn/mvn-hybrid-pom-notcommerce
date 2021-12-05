package pageUILiveGuru;

public class UserLoginPageUI {
	public final static String EMAIL_LOCATOR = "//input[@id='email']";
	public final static String PASSWORD_LOCATOR = "//input[@id='pass']";
	public final static String LOGIN_BUTTON_LOCATOR = "//button[@id='send2']";
	public final static String EMPTY_EMAIL_MESSAGE_LOCATOR = "//div[@id='advice-required-entry-email']";
	public final static String EMPTY_PASSWORD_MESSAGE_LOCATOR = "//div[@id='advice-required-entry-pass']";
	public final static String INVALID_EMAIL_MESSAGE_LOCATOR = "//div[@id='advice-validate-email-email']";
	public final static String INVALID_PASSWORD_MESSAGE_LOCATOR = "//div[@id='advice-validate-password-pass']";
	public final static String INCORRECT_EMAIL_PASSWORD_MESSAGE_LOCATOR = "//li[@class='error-msg']//span";
	public final static String CREATE_ACCOUNT_BUTTON = "//a[@title='Create an Account']";
}
