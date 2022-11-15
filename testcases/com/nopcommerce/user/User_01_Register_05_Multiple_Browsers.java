package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class User_01_Register_05_Multiple_Browsers extends BaseTest {
	private WebDriver driver;
	private UserRegisterPageObject registerPageObject;
	private UserHomePageObject homePageObject;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");
		registerPageObject = new UserRegisterPageObject(driver);
		homePageObject = new UserHomePageObject(driver);
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		homePageObject.clickToRegisterLink();
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getErrorMessageAtFirstNameInput(), "First name is required.");
		Assert.assertEquals(registerPageObject.getErrorMessageAtLastNameInput(), "Last name is required.");
		Assert.assertEquals(registerPageObject.getErrorMessageAtEmailInput(), "Email is required.");
		Assert.assertEquals(registerPageObject.getErrorMessageAtPasswordNameInput(), "Password is required.");
		Assert.assertEquals(registerPageObject.getErrorMessageAtConfirmPasswordInput(), "Password is required.");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
