package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.RegisterPageObject;

public class User_01_Register_04_Page_Object {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String email;
	private RegisterPageObject registerPageObject;

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
		registerPageObject = new RegisterPageObject(driver);
		email = getRandomNumber() + "@test.com";
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		registerPageObject.clickToRegisterLink();
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getErrorMessageAtFirstNameInput(), "First name is required.");
		Assert.assertEquals(registerPageObject.getErrorMessageAtLastNameInput(), "Last name is required.");
		Assert.assertEquals(registerPageObject.getErrorMessageAtEmailInput(), "Email is required.");
		Assert.assertEquals(registerPageObject.getErrorMessageAtPasswordNameInput(), "Password is required.");
		Assert.assertEquals(registerPageObject.getErrorMessageAtConfirmPasswordInput(), "Password is required.");
	}

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		registerPageObject.clickToRegisterLink();

		registerPageObject.sendKeyToEmailTextbox("abc@test/com");
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getErrorMessageAtEmailInput(), "Wrong email");
	}

	@Test
	public void TC_03_SuccessfullyRegister() {
		registerPageObject.clickToRegisterLink();

		registerPageObject.sendKeyToFirstNameTextbox("Firstname");
		registerPageObject.sendKeyToLastNameTextbox("Lastname");
		registerPageObject.sendKeyToPasswordTextbox("password");
		registerPageObject.sendKeyToConfirmPasswordTextbox("password");
		registerPageObject.sendKeyToEmailTextbox(email);
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getRegisterSuccessMessage(), "Your registration completed");
		registerPageObject.clickToLogoutButton();
	}

	@Test
	public void TC_04_RegisterWithExistedEmail() {
		registerPageObject.clickToRegisterLink();

		registerPageObject.sendKeyToFirstNameTextbox("Firstname");
		registerPageObject.sendKeyToLastNameTextbox("Lastname");
		registerPageObject.sendKeyToPasswordTextbox("password");
		registerPageObject.sendKeyToConfirmPasswordTextbox("password");
		registerPageObject.sendKeyToEmailTextbox(email);
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getRegisterDuplicatedMessage(), "The specified email already exists");
	}

	@Test
	public void TC_05_RegisterWithInvalidPassword() {
		registerPageObject.clickToRegisterLink();

		registerPageObject.sendKeyToPasswordTextbox("passw");
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getErrorMessageAtPasswordNameInput(),
				"Password must meet the following rules:\n" + "must have at least 6 characters");
	}

	@Test
	public void TC_06_RegisterWithInvalidConfirmPassword() {
		registerPageObject.clickToRegisterLink();

		registerPageObject.sendKeyToPasswordTextbox("password");
		registerPageObject.sendKeyToConfirmPasswordTextbox("password1");
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getErrorMessageAtConfirmPasswordInput(),
				"The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
