package com.jquery;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectJquery.GenerateManagerObject;
import pageObjectJquery.HomePageObject;
import pageObjectJquery.UploadFilePageObject;

public class Level_12_Upload_File extends BaseTest{
	WebDriver driver;
	UploadFilePageObject uploadFilePage;
	String flower01 = "image1.jpg";
	String flower02 = "image2.jpg";
	String fileLocation;
	
	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = multiBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	}
	
	@Test
	public void TC_01_Upload_One_File() {
		uploadFilePage = GenerateManagerObject.getUploadFilePage(driver);
		
		uploadFilePage.uploadFiles(flower01);
		uploadFilePage.loadFiles(flower01);
		Assert.assertTrue(uploadFilePage.isUploadFileSuccess(flower01));
	}
	
	@Test
	public void TC_02_Upload_Mutiple_File() {
		uploadFilePage.getRefreshPage(driver);
		uploadFilePage.uploadFiles(flower01,flower02);
		
		uploadFilePage.loadFiles(flower01);
		uploadFilePage.loadFiles(flower02);
		
		Assert.assertTrue(uploadFilePage.isUploadFileSuccess(flower01));
		Assert.assertTrue(uploadFilePage.isUploadFileSuccess(flower02));
	}


	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
