package com.jQuery;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

public class Table_02_Handle_Data_Table extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		homePage = PageGeneratorManager.getHomePageObject(driver);
	}

	@Test
	public void TC_01() {
		homePage.sendKeyToTextboxAppendGrid(1, "Album", "Abc");
		homePage.sendKeyToTextboxAppendGrid(1, "Artist", "Abcde");
		homePage.clickToButtonAppendGrid(1, "Insert Row Above");
		homePage.clickToButtonAppendGrid(1, "Move Down");
		homePage.sendKeyToTextboxAppendGrid(2, "Album", "Def");
		homePage.sendKeyToTextboxAppendGrid(2, "Artist", "Defghi");
		homePage.sendKeyToTextboxAppendGrid(2, "Price", "300");
		homePage.clickToButtonAppendGrid(2, "Insert Row Above");
		homePage.clickToButtonAppendGrid(2, "Insert Row Above");
		homePage.sendKeyToTextboxAppendGrid(3, "Album", "Def");
		homePage.clickToButtonAppendGrid(2, "Move Down");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
