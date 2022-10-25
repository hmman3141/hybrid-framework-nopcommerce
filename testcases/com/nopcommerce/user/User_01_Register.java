package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User_01_Register {
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
		driver.get("https://demo.nopcommerce.com/");
		email = getRandomNumber() + "@test.com";
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("#FirstName-error")).getText(),
				"First name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("#LastName-error")).getText(), "Last name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("#Email-error")).getText(), "Email is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(),
				"Password is required.");
	}

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("#Email")).sendKeys("abc@test/com");
		driver.findElement(By.cssSelector("#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("#Email-error")).getText(), "Wrong email");
	}

	@Test
	public void TC_03_SuccessfullyRegister() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Firstname");
		driver.findElement(By.cssSelector("#LastName")).sendKeys("Lastname");
		driver.findElement(By.cssSelector("#Password")).sendKeys("password");
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("password");
		driver.findElement(By.cssSelector("#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector("a.ico-logout")).click();
	}
	
	@Test
	public void TC_04_RegisterWithExistedEmail() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Firstname");
		driver.findElement(By.cssSelector("#LastName")).sendKeys("Lastname");
		driver.findElement(By.cssSelector("#Password")).sendKeys("password");
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("password");
		driver.findElement(By.cssSelector("#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
	}
	
	@Test
	public void TC_05_RegisterWithInvalidPassword() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("#Password")).sendKeys("passw");
		driver.findElement(By.cssSelector("#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password must meet the following rules:\n"
				+ "must have at least 6 characters");
	}
	
	@Test
	public void TC_06_RegisterWithInvalidConfirmPassword() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("#Password")).sendKeys("password");
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("password1");
		driver.findElement(By.cssSelector("#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
