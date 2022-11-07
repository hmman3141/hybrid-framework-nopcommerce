package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.HomePageObject;
import PageObjects.LoginPageObject;
import PageObjects.RegisterPageObject;

public class User_02_Login {
	private WebDriver driver;
	private LoginPageObject loginPageObject;
	private HomePageObject homePageObject;
	private RegisterPageObject registerPageObject;
	private String projectPath = System.getProperty("user.dir");
	private String email, invalidEmail, nonExistentEmail, password, incorrectPassword, firstName, lastName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		loginPageObject = new LoginPageObject(driver);
		homePageObject = new HomePageObject(driver);
		registerPageObject = new RegisterPageObject(driver);
		email = getRandomNumber() + "@test.com";
		invalidEmail = "abc@test/com";
		nonExistentEmail = getRandomNumber() + "@yahoo.com";
		password = "password";
		incorrectPassword = "password1";
		firstName = "First name";
		lastName = "Last name";

		homePageObject.clickToRegisterLink();
		registerPageObject.sendKeyToFirstNameTextbox(firstName);
		registerPageObject.sendKeyToLastNameTextbox(lastName);
		registerPageObject.sendKeyToPasswordTextbox(password);
		registerPageObject.sendKeyToConfirmPasswordTextbox(password);
		registerPageObject.sendKeyToEmailTextbox(email);
		registerPageObject.clickToRegisterButton();
		Assert.assertEquals(registerPageObject.getRegisterSuccessMessage(), "Your registration completed");
		registerPageObject.clickToLogoutButton();
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt();
	}

	@Test
	public void TC_01_LoginWithEmptyData() {
		homePageObject.clickToLoginLink();
		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailInput(), "Please enter your email");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		homePageObject.clickToLoginLink();
		loginPageObject.sendKeyToEmailTextbox(invalidEmail);
		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailInput(), "Wrong email");
	}

	@Test
	public void TC_03_LoginWithNonExistentEmail() {
		homePageObject.clickToLoginLink();
		loginPageObject.sendKeyToEmailTextbox(nonExistentEmail);
		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@Test
	public void TC_04_LoginWithBlankPassword() {
		homePageObject.clickToLoginLink();
		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		homePageObject.clickToLoginLink();
		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.sendKeyToPasswordTextbox(incorrectPassword);
		loginPageObject.clickToLoginButton();

		Assert.assertEquals(loginPageObject.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void TC_06_LoginWithExistedEmailAndCorrectPassword() {
		homePageObject.clickToLoginLink();
		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.sendKeyToPasswordTextbox(password);
		loginPageObject.clickToLoginButton();
		Assert.assertTrue(homePageObject.isAccountLink());
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
