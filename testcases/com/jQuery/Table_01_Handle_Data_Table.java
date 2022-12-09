package com.jQuery;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;
import pageUIs.jQuery.HomePageUI;

public class Table_01_Handle_Data_Table extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get(HomePageUI.PAGE_URL);
		homePage = PageGeneratorManager.getHomePageObject(driver);
	}

	@Test
	public void TC_01_Paging() {
		homePage.clickPaginationByNumber(2);
		homePage.clickPaginationByNumber(5);
		homePage.clickPaginationByNumber(9);
	}
	
	@Test
	public void TC_02_SendKeyToHeader() {
		homePage.sendKeyToTextbox("Females", "167000");
		homePage.sendKeyToTextbox("Country", "Zimbabwe");
		homePage.sendKeyToTextbox("Males", "166000");
	}
	
	@Test
	public void TC_03_GetValueFromCountryPerPage() {
		homePage.sendKeyToTextbox("Females", "167000");
		homePage.sendKeyToTextbox("Country", "Zimbabwe");
		homePage.sendKeyToTextbox("Males", "166000");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
