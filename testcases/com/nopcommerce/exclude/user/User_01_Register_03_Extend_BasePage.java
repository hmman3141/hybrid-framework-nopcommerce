package com.nopcommerce.exclude.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.GlobalConstants;

public class User_01_Register_03_Extend_BasePage extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email;

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt();
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		email = getRandomNumber() + "@test.com";
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		clickOnElement(driver, "//a[@class='ico-register']");
		clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"),
				"First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"),
				"Password is required.");
	}

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		clickOnElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='Email']", "abc@test/com");
		clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_03_SuccessfullyRegister() {
		clickOnElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Firstname");
		sendKeyToElement(driver, "//input[@id='LastName']", "Lastname");
		sendKeyToElement(driver, "//input[@id='Password']", "password");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "password");
		sendKeyToElement(driver, "//input[@id='Email']", email);
		clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		clickOnElement(driver, "//a[@class='ico-logout']");
	}

	@Test
	public void TC_04_RegisterWithExistedEmail() {
		clickOnElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Firstname");
		sendKeyToElement(driver, "//input[@id='LastName']", "Lastname");
		sendKeyToElement(driver, "//input[@id='Password']", "password");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "password");
		sendKeyToElement(driver, "//input[@id='Email']", email);
		clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"),
				"The specified email already exists");
	}

	@Test
	public void TC_05_RegisterWithInvalidPassword() {
		clickOnElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='Password']", "passw");
		clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"),
				"Password must meet the following rules:\n" + "must have at least 6 characters");
	}

	@Test
	public void TC_06_RegisterWithInvalidConfirmPassword() {
		clickOnElement(driver, "//a[@class='ico-register']");

		driver.findElement(By.cssSelector("#Password")).sendKeys("password");
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("password1");
		clickOnElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"),
				"The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
