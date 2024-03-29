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

public class User_01_Register_02_Apply_BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email;
	BasePage basePage;

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
		basePage = new BasePage();
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		basePage.clickOnElement(driver, "//a[@class='ico-register']");
		basePage.clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"),
				"First name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"),
				"Password is required.");
	}

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		basePage.clickOnElement(driver, "//a[@class='ico-register']");

		basePage.sendKeyToElement(driver, "//input[@id='Email']", "abc@test/com");
		basePage.clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_03_SuccessfullyRegister() {
		basePage.clickOnElement(driver, "//a[@class='ico-register']");

		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Firstname");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Lastname");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "password");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "password");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", email);
		basePage.clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
		basePage.clickOnElement(driver, "//a[@class='ico-logout']");
	}

	@Test
	public void TC_04_RegisterWithExistedEmail() {
		basePage.clickOnElement(driver, "//a[@class='ico-register']");

		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Firstname");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Lastname");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "password");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "password");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", email);
		basePage.clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//div[contains(@class,'message-error')]//li"),
				"The specified email already exists");
	}

	@Test
	public void TC_05_RegisterWithInvalidPassword() {
		basePage.clickOnElement(driver, "//a[@class='ico-register']");

		basePage.sendKeyToElement(driver, "//input[@id='Password']", "passw");
		basePage.clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"),
				"Password must meet the following rules:\n" + "must have at least 6 characters");
	}

	@Test
	public void TC_06_RegisterWithInvalidConfirmPassword() {
		basePage.clickOnElement(driver, "//a[@class='ico-register']");

		driver.findElement(By.cssSelector("#Password")).sendKeys("password");
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("password1");
		basePage.clickOnElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"),
				"The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
