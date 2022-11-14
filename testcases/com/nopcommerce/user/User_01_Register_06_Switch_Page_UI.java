package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.AddressesPageObject;
import PageObjects.CustomerInfoPageObject;
import PageObjects.HomePageObject;
import PageObjects.LoginPageObject;
import PageObjects.MyProductReviewPageObject;
import PageObjects.PageGeneratorManager;
import PageObjects.RegisterPageObject;
import PageObjects.RewardPointsPageObject;
import commons.BaseTest;

public class User_01_Register_06_Switch_Page_UI extends BaseTest {
	private WebDriver driver;
	private String email, firstName, lastName, password;
	private RegisterPageObject registerPageObject;
	private HomePageObject homePageObject;
	private LoginPageObject loginPageObject;
	private AddressesPageObject addressesPageObject;
	private RewardPointsPageObject rewardPointsPageObject;
	private MyProductReviewPageObject myProductReviewPageObject;
	private CustomerInfoPageObject customerInfoPageObject;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");
		homePageObject = PageGeneratorManager.getHomePage(driver);
		
		email = getRandomNumber() + "@test.com";
		password = "password";
		firstName = "First name";
		lastName = "Last name";
	}

	@Test
	public void TC_01_Register() {
		registerPageObject = homePageObject.clickToRegisterLink();
		
		registerPageObject.sendKeyToFirstNameTextbox(firstName);
		registerPageObject.sendKeyToLastNameTextbox(lastName);
		registerPageObject.sendKeyToPasswordTextbox(password);
		registerPageObject.sendKeyToConfirmPasswordTextbox(password);
		registerPageObject.sendKeyToEmailTextbox(email);
		registerPageObject.clickToRegisterButton();

		Assert.assertEquals(registerPageObject.getRegisterSuccessMessage(), "Your registration completed");
		registerPageObject.clickToLogoutButton();
	}

	@Test
	public void TC_02_Login() {
		loginPageObject = homePageObject.clickToLoginLink();

		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.sendKeyToPasswordTextbox(password);
		loginPageObject.clickToLoginButton();

		Assert.assertTrue(homePageObject.isAccountLink());
	}

	@Test
	public void TC_03_MyAccount() {
		customerInfoPageObject = homePageObject.clickToMyAccountLink();
		addressesPageObject = customerInfoPageObject.clickToAddressesLink(driver);
		rewardPointsPageObject = addressesPageObject.clickToRewardPointsLink(driver);
		myProductReviewPageObject = rewardPointsPageObject.clickToMyProductReviewLink(driver);
		addressesPageObject = myProductReviewPageObject.clickToAddressesLink(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
