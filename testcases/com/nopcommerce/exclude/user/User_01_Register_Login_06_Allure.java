package com.nopcommerce.exclude.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObjects.nopCommerce.user.allure.PageGeneratorManager_Allure;
import pageObjects.nopCommerce.user.allure.UserHomePageObject_Allure;
import pageObjects.nopCommerce.user.allure.UserLoginPageObject_Allure;
import pageObjects.nopCommerce.user.allure.UserRegisterPageObject_Allure;

public class User_01_Register_Login_06_Allure extends BaseTest {
	private WebDriver driver;
	private String userEmail, userFirstName, userLastName, userPassword;
	private UserRegisterPageObject_Allure userRegisterPageObject;
	private UserHomePageObject_Allure userHomePage;
	private UserLoginPageObject_Allure userLoginPage;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager_Allure.getUserHomePage(driver);

		userEmail = getRandomNumber() + "@test.com";
		userPassword = "password";
		userFirstName = "First name";
		userLastName = "Last name";
	}

	@Test
	@Severity(SeverityLevel.NORMAL)
	@Description(value = "Register and login")
	public void TC_01_Register_Login() {
		userRegisterPageObject = userHomePage.clickToRegisterLink();

		userRegisterPageObject.sendKeyToFirstNameTextbox(userFirstName);
		userRegisterPageObject.sendKeyToLastNameTextbox(userLastName);
		userRegisterPageObject.sendKeyToPasswordTextbox(userPassword);
		userRegisterPageObject.sendKeyToConfirmPasswordTextbox(userPassword);
		userRegisterPageObject.sendKeyToEmailTextbox(userEmail);
		userRegisterPageObject.clickToRegisterButton();

		verifyEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");

		userLoginPage = userHomePage.clickToLoginLink();
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);

		verifyTrue(userHomePage.isAccountLink());

		userHomePage = userHomePage.clickToLogoutAtUserPage_Allure(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
