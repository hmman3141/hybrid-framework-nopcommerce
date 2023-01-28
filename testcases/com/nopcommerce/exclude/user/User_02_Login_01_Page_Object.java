package com.nopcommerce.exclude.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.GlobalConstants;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class User_02_Login_01_Page_Object {
	private WebDriver driver;
	private UserLoginPageObject loginPageObject;
	private UserHomePageObject homePageObject;
	private UserRegisterPageObject registerPageObject;
	private String projectPath = System.getProperty("user.dir");
	private String email, invalidEmail, nonExistentEmail, password, incorrectPassword, firstName, lastName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		loginPageObject = new UserLoginPageObject(driver);
		homePageObject = new UserHomePageObject(driver);
		registerPageObject = new UserRegisterPageObject(driver);
		email = getRandomNumber() + "@test.com";
		invalidEmail = "abc@test/com";
		nonExistentEmail = getRandomNumber() + "@yahoo.com";
		password = "password";
		incorrectPassword = "password1";
		firstName = "First name";
		lastName = "Last name";

		homePageObject.clickOnRegisterLink();
		registerPageObject.sendKeyToFirstNameTextbox(firstName);
		registerPageObject.sendKeyToLastNameTextbox(lastName);
		registerPageObject.sendKeyToPasswordTextbox(password);
		registerPageObject.sendKeyToConfirmPasswordTextbox(password);
		registerPageObject.sendKeyToEmailTextbox(email);
		registerPageObject.clickOnRegisterButton();
		Assert.assertEquals(registerPageObject.getRegisterSuccessMessage(), "Your registration completed");
		registerPageObject.clickOnLogoutButton();
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt();
	}

	@Test
	public void TC_01_LoginWithEmptyData() {
		homePageObject.clickOnLoginLink();
		loginPageObject.clickOnLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailInput(), "Please enter your email");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		homePageObject.clickOnLoginLink();
		loginPageObject.sendKeyToEmailTextbox(invalidEmail);
		loginPageObject.clickOnLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailInput(), "Wrong email");
	}

	@Test
	public void TC_03_LoginWithNonExistentEmail() {
		homePageObject.clickOnLoginLink();
		loginPageObject.sendKeyToEmailTextbox(nonExistentEmail);
		loginPageObject.clickOnLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@Test
	public void TC_04_LoginWithBlankPassword() {
		homePageObject.clickOnLoginLink();
		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.clickOnLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		homePageObject.clickOnLoginLink();
		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.sendKeyToPasswordTextbox(incorrectPassword);
		loginPageObject.clickOnLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void TC_06_LoginWithExistedEmailAndCorrectPassword() {
		homePageObject.clickOnLoginLink();
		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.sendKeyToPasswordTextbox(password);
		loginPageObject.clickOnLoginButton();
		Assert.assertTrue(homePageObject.isAccountLink());
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
