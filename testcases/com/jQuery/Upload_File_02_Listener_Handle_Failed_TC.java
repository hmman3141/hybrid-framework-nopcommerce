package com.jQuery;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.uploadFiles.HomePageObject;
import pageObjects.jQuery.uploadFiles.PageGeneratorManager;

public class Upload_File_02_Listener_Handle_Failed_TC extends BaseTest {
	private WebDriver driver;
	private String[] files = { "file2.png", "file3.png" };
	private HomePageObject homePage;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		homePage = PageGeneratorManager.getHomePageObject(driver);
	}

	@Test
	public void TC_01() {
		homePage.sendFilesToElement();
		homePage.clickToMultipleStartButtons();
		for (String file : files) {
			verifyFalse(homePage.isFileLoadedByName(file));
		}
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
