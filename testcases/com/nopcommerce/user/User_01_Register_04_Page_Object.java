package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class User_01_Register_04_Page_Object {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String email, invalidEmail, firstName, lastName, password, invalidPassword, confirmPassword, invalidConfirmPassword;
	private UserRegisterPageObject registerPageObject;
	private UserHomePageObject homePageObject;

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt();
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		registerPageObject = new UserRegisterPageObject(driver);
		homePageObject = new UserHomePageObject(driver);
		email = getRandomNumber() + "@test.com";
		invalidEmail = "abc@test/com";
		password = "password";
		invalidPassword = "passw";
		confirmPassword = "password";
		invalidConfirmPassword = "password1";
		firstName = "First name";
		lastName = "Last name";
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

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		homePageObject.clickToRegisterLink();

		registerPageObject.sendKeyToEmailTextbox(invalidEmail);
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getErrorMessageAtEmailInput(), "Wrong email");
	}

	@Test
	public void TC_03_SuccessfullyRegister() {
		homePageObject.clickToRegisterLink();

		registerPageObject.sendKeyToFirstNameTextbox(firstName);
		registerPageObject.sendKeyToLastNameTextbox(lastName);
		registerPageObject.sendKeyToPasswordTextbox(password);
		registerPageObject.sendKeyToConfirmPasswordTextbox(confirmPassword);
		registerPageObject.sendKeyToEmailTextbox(email);
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getRegisterSuccessMessage(), "Your registration completed");
		registerPageObject.clickToLogoutButton();
	}

	@Test
	public void TC_04_RegisterWithExistedEmail() {
		homePageObject.clickToRegisterLink();

		registerPageObject.sendKeyToFirstNameTextbox(firstName);
		registerPageObject.sendKeyToLastNameTextbox(lastName);
		registerPageObject.sendKeyToPasswordTextbox(password);
		registerPageObject.sendKeyToConfirmPasswordTextbox(confirmPassword);
		registerPageObject.sendKeyToEmailTextbox(email);
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getRegisterDuplicatedMessage(), "The specified email already exists");
	}

	@Test
	public void TC_05_RegisterWithInvalidPassword() {
		homePageObject.clickToRegisterLink();

		registerPageObject.sendKeyToPasswordTextbox(invalidPassword);
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getErrorMessageAtPasswordNameInput(),
				"Password must meet the following rules:\n" + "must have at least 6 characters");
	}

	@Test
	public void TC_06_RegisterWithInvalidConfirmPassword() {
		homePageObject.clickToRegisterLink();

		registerPageObject.sendKeyToPasswordTextbox(password);
		registerPageObject.sendKeyToConfirmPasswordTextbox(invalidConfirmPassword);
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getErrorMessageAtConfirmPasswordInput(),
				"The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
