package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;
import pageUIs.nopCommerce.user.UserCustomerInfoSidebar;

public class User_03_Switch_Page_02_Dynamic_Locator extends BaseTest {
	private WebDriver driver;
	private String email, firstName, lastName, password;
	private UserRegisterPageObject registerPageObject;
	private UserHomePageObject homePageObject;
	private UserLoginPageObject loginPageObject;
	private UserAddressesPageObject addressesPageObject;
	private UserRewardPointsPageObject rewardPointsPageObject;
	private UserMyProductReviewPageObject myProductReviewPageObject;
	private UserCustomerInfoPageObject customerInfoPageObject;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		homePageObject = PageGeneratorManager.getPageGenerator().getUserHomePage(driver);

		email = getRandomNumber() + "@test.com";
		password = "password";
		firstName = "First name";
		lastName = "Last name";
	}

	@Test
	public void TC_01_Register() {
		registerPageObject = homePageObject.clickOnRegisterLink();

		registerPageObject.sendKeyToFirstNameTextbox(firstName);
		registerPageObject.sendKeyToLastNameTextbox(lastName);
		registerPageObject.sendKeyToPasswordTextbox(password);
		registerPageObject.sendKeyToConfirmPasswordTextbox(password);
		registerPageObject.sendKeyToEmailTextbox(email);
		registerPageObject.clickOnRegisterButton();

		Assert.assertEquals(registerPageObject.getRegisterSuccessMessage(), "Your registration completed");
		registerPageObject.clickOnLogoutButton();
	}

	@Test
	public void TC_02_Login() {
		loginPageObject = homePageObject.clickOnLoginLink();

		loginPageObject.sendKeyToEmailTextbox(email);
		loginPageObject.sendKeyToPasswordTextbox(password);
		loginPageObject.clickOnLoginButton();

		Assert.assertTrue(homePageObject.isAccountLink());
	}

	@Test
	public void TC_03_MyAccount() {
		customerInfoPageObject = homePageObject.clickOnMyAccountLink();
		addressesPageObject = (UserAddressesPageObject) customerInfoPageObject.openMyAccountPageByName(driver,
				UserCustomerInfoSidebar.ADDRESSES);
		rewardPointsPageObject = (UserRewardPointsPageObject) addressesPageObject.openMyAccountPageByName(driver,
				UserCustomerInfoSidebar.REWARD_POINTS);
		myProductReviewPageObject = (UserMyProductReviewPageObject) rewardPointsPageObject
				.openMyAccountPageByName(driver, UserCustomerInfoSidebar.MY_PRODUCT_REVIEW);
		addressesPageObject = (UserAddressesPageObject) myProductReviewPageObject.openMyAccountPageByName(driver,
				UserCustomerInfoSidebar.ADDRESSES);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
