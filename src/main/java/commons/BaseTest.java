package commons;

import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	protected final Log log;
	
	protected BaseTest(){
		log = LogFactory.getLog(getClass());
	}
	
	public enum BROWSER{
		CHROME,FIREFOX,EDGE,IE;
	}
	protected WebDriver multiBrowser(String browserName) {
		BROWSER browser  = BROWSER.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX: 
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			break;
		default:
			break;
		}
		return driver;
	}
	
	protected WebDriver multiBrowser(String browserName, String url) {
		BROWSER browser  = BROWSER.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX: 
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + "\\consoleLog\\ConsoleFirefoxLog.txt");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			System.setProperty("Webdriver.chrome.args","--disable-logging");
			System.setProperty("Webdriver.chrome.silentOutput","true");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			break;
		default:
			return (WebDriver) new RuntimeException("Please enter correct browser!");
		}
		return driver;
	}
	protected WebDriver multiBrowserHeadless(String browserName, String url) {
		BROWSER browser  = BROWSER.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX: 
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(true);
			driver = new FirefoxDriver(firefoxOptions);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("window-size=1366x768");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			break;
		default:
			return (WebDriver) new RuntimeException("Please enter correct browser!");
		}
		return driver;
	}
	
	public String getRandomEmail() {
		Random rand = new Random();
		return "mark" + rand.nextInt(99999) + "@gmail.com";
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			AssertJUnit.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			AssertJUnit.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			AssertJUnit.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	@BeforeTest
	public void deleteAllFilesInReportNGScreenshot() {
		log.info("---------- START delete file in folder ----------");
		try {
			String workingDir = System.getProperty("user.dir");
			String pathFolderDownload = workingDir + "\\allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

		log.info("---------- END delete file in folder ----------");
	}

	protected  void closeBrowserAndDriver() {
		String cmd = "";
		try {
			//get name os va convert ra chu thuong
			
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name is " + osName);
			System.out.println("OS name is " + osName);
			
			//Quit driver executable file in Task Manager
			if(driver.toString().contains("chrome")) {
				if(osName.contains("mac")) {
					cmd = "pkill chromedriver";
				}else if(osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			}else if(driver.toString().contains("firefox")) {
				if(osName.contains("mac")) {
					cmd = "pkill geckodriver";
				}else if(osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			
			}else if(driver.toString().contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("edge")) {
				if (osName.contains("mac")) {
					cmd = "pkill msedgedriver";
				} else if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				}
			}
			
			System.out.println("driver instance: " + driver.toString());
			// Quit browser
			if(driver!= null) {
				driver.manage().deleteAllCookies();
				driver.quit();
				System.out.println("close browser");
			}
			
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process= Runtime.getRuntime().exec(cmd);
				process.waitFor();
				System.out.println("run commandline");
			} catch (Exception e) {
				e.printStackTrace();
			} 
				
			log.info("---------- QUIT BROWSER SUCCESS ----------");
		}
	}
	
	protected void showBrowserConsoleLog(WebDriver driver) {
		if(driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for(LogEntry logging : logList) {
				System.out.println("---------" + logging.getLevel().toString() + "---------\n" + logging.getMessage());
			}
		} 
	}

	
	@AfterSuite(alwaysRun = true)
	public void closeExecuteBrowser() {
		System.out.println("Run aftersuite");
	}
	
}
