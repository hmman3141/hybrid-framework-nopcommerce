package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.HomePageObject_BaseFactory;
import PageObjects.LoginPageObject_BaseFactory;

public class User_02_Login_02_Page_Factory {
	private WebDriver driver;
	private LoginPageObject_BaseFactory loginPageObject;
	private HomePageObject_BaseFactory homePageObject;
	private String projectPath = System.getProperty("user.dir");
	private String email, invalidEmail, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		loginPageObject = new LoginPageObject_BaseFactory(driver);
		homePageObject = new HomePageObject_BaseFactory(driver);
		email = getRandomNumber() + "@test.com";
		invalidEmail = "abc@test/com";
		password = "password";
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
		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.sendKeyToPasswordTextbox(password);
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
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
