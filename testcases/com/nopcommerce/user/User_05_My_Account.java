package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserChangePasswordPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserDesktopsPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserProductInformationPageObject;
import pageObjects.nopCommerce.user.UserProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageUIs.nopCommerce.user.UserCustomerInfoSidebar;

public class User_05_My_Account extends BaseTest {
	private WebDriver driver;
	private String createdEmail, createdFirstName, createdLastName, password, newPassword, dayOfBirth, monthOfBirth,
			yearOfBirth, firstName, lastName, email, company, country, state, city, address1, address2, postalCode,
			phone, fax, fullName, newAddressEmail, newAddressPhone, newAddressFax, cityStateZip, reviewTitle, reviewText;
	private UserRegisterPageObject userRegisterPage;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private UserAddressesPageObject userAddressesPage;
	private UserChangePasswordPageObject userChangePasswordPage;
	private UserDesktopsPageObject userDesktopsPage;
	private UserProductInformationPageObject userProductInformationPage;
	private UserProductReviewPageObject userProductReviewPage;
	private UserMyProductReviewPageObject userMyProductReviewPage;

	@Parameters("Browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		createdEmail = getRandomNumber() + "@test.com";
		createdFirstName = "First name";
		createdLastName = "Last name";
		email = "automationfc.vn@gmail.com";
		newAddressEmail = "Email: " + email;
		password = "password";
		newPassword = "12345678";
		dayOfBirth = "1";
		monthOfBirth = "January";
		yearOfBirth = "1999";
		firstName = "Automation";
		lastName = "FC";
		fullName = firstName + " " + lastName;
		company = "Automation FC";
		country = "Viet Nam";
		state = "Other";
		city = "Da Nang";
		postalCode = "550000";
		cityStateZip = city + ", " + postalCode;
		address1 = "123/04 Le Lai";
		address2 = "234/ 05 Hai Phong";
		phone = "0123456789";
		newAddressPhone = "Phone number: " + phone;
		fax = "0987654321";
		newAddressFax = "Fax number: " + fax;
		reviewTitle = "Title";
		reviewText = "Text for review";

		userRegisterPage = userHomePage.clickToRegisterLink();

		userRegisterPage.sendKeyToFirstNameTextbox(createdFirstName);
		userRegisterPage.sendKeyToLastNameTextbox(createdLastName);
		userRegisterPage.sendKeyToPasswordTextbox(password);
		userRegisterPage.sendKeyToConfirmPasswordTextbox(password);
		userRegisterPage.sendKeyToEmailTextbox(createdEmail);
		userRegisterPage.clickToRegisterButton();

		System.out.println(createdEmail + "/" + password);

		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
		userRegisterPage.clickToLogoutButton();

		userLoginPage = userHomePage.clickToLoginLink();
		userHomePage = userLoginPage.loginAsUser(createdEmail, password);

		Assert.assertTrue(userHomePage.isAccountLink());
	}

	@Test
	public void TC_01_Customer_Information() {
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();
		Assert.assertTrue(userCustomerInfoPage.isCustomerInfoDisplay());

		userCustomerInfoPage.clickToFemaleRadioButton();
		userCustomerInfoPage.sendKeyToFirstNameTextBoxInput(firstName);
		userCustomerInfoPage.sendKeyToLastNameTextBoxInput(lastName);
		userCustomerInfoPage.sendKeyToEmailTextBoxInput(email);
		userCustomerInfoPage.selectItemFromDayCheckbox(dayOfBirth);
		userCustomerInfoPage.selectItemFromMonthCheckbox(monthOfBirth);
		userCustomerInfoPage.selectItemFromYearCheckbox(yearOfBirth);
		userCustomerInfoPage.sendKeyToCompanyTextBoxInput(company);
		userCustomerInfoPage.clickToSaveButton();

		Assert.assertEquals(userCustomerInfoPage.getTextFromFirstNameTextBoxInput(), firstName);
		Assert.assertEquals(userCustomerInfoPage.getTextFromLastNameTextBoxInput(), lastName);
		Assert.assertEquals(userCustomerInfoPage.getTextFromEmailNameTextBoxInput(), email);
		Assert.assertEquals(userCustomerInfoPage.getTextFromCompanyNameTextBoxInput(), company);
		Assert.assertEquals(userCustomerInfoPage.getTextFromDayOfBirthDropdown(), dayOfBirth);
		Assert.assertEquals(userCustomerInfoPage.getTextFromMonthOfBirthDropdown(), monthOfBirth);
		Assert.assertEquals(userCustomerInfoPage.getTextFromYearOfBirthDropdown(), yearOfBirth);
	}

	@Test
	public void TC_02_Addresses() {
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();
		Assert.assertTrue(userCustomerInfoPage.isCustomerInfoDisplay());

		userAddressesPage = (UserAddressesPageObject) userCustomerInfoPage.openMyAccountPageByName(driver,
				UserCustomerInfoSidebar.ADDRESSES);
		userAddressesPage.clickToAddNewButton();
		userAddressesPage.sendKeyToFirstNameTextbox(firstName);
		userAddressesPage.sendKeyToLastNameTextbox(lastName);
		userAddressesPage.sendKeyToEmailTextbox(email);
		userAddressesPage.sendKeyToCompanyTextbox(company);
		userAddressesPage.selectItemFromCountryDropdown(country);
		userAddressesPage.selectItemFromStateDropdown(state);
		userAddressesPage.sendKeyToCityTextbox(city);
		userAddressesPage.sendKeyToAddress1Textbox(address1);
		userAddressesPage.sendKeyToAddress2Textbox(address2);
		userAddressesPage.sendKeyToPostalCodeTextbox(postalCode);
		userAddressesPage.sendKeyToPhoneNumberTextbox(phone);
		userAddressesPage.sendKeyToFaxNumberTextbox(fax);
		userAddressesPage.clickToSaveButton();

		Assert.assertEquals(userAddressesPage.getTextFromNameText(fullName), fullName);
		Assert.assertEquals(userAddressesPage.getTextFromEmailText(fullName), newAddressEmail);
		Assert.assertEquals(userAddressesPage.getTextFromCompanyText(fullName), company);
		Assert.assertEquals(userAddressesPage.getTextFromCountryText(fullName), country);
		Assert.assertEquals(userAddressesPage.getTextFromCityStateZipText(fullName), cityStateZip);
		Assert.assertEquals(userAddressesPage.getTextFromAddress1Text(fullName), address1);
		Assert.assertEquals(userAddressesPage.getTextFromAddress2Text(fullName), address2);
		Assert.assertEquals(userAddressesPage.getTextFromPhoneNumberText(fullName), newAddressPhone);
		Assert.assertEquals(userAddressesPage.getTextFromFaxNumberText(fullName), newAddressFax);
	}

	@Test
	public void TC_03_ChangePassword() {
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();
		Assert.assertTrue(userCustomerInfoPage.isCustomerInfoDisplay());

		userChangePasswordPage = (UserChangePasswordPageObject) userCustomerInfoPage.openMyAccountPageByName(driver,
				UserCustomerInfoSidebar.CHANGE_PASSWORD);
		userChangePasswordPage.sendKeyToOldPasswordTextbox(password);
		userChangePasswordPage.sendKeyToNewPasswordTextbox(newPassword);
		userChangePasswordPage.sendKeyToConfirmNewPasswordTextbox(newPassword);
		userChangePasswordPage.clickToChangePasswordButton();
		userChangePasswordPage.clickToCloseSuccessfulPasswordChangeButton();

		userHomePage = userChangePasswordPage.clickToLogoutAtUserPage(driver);
		userLoginPage = userHomePage.clickToLoginLink();
		userLoginPage.sendKeyToEmailTextbox(email);
		userLoginPage.sendKeyToPasswordTextbox(password);
		userLoginPage.clickToLoginButton();

		Assert.assertEquals(userLoginPage.getErrorMessageByNonExistentEmail(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
		userLoginPage.sendKeyToEmailTextbox(email);
		userLoginPage.sendKeyToPasswordTextbox(newPassword);
		userLoginPage.clickToLoginButton();

		Assert.assertTrue(userChangePasswordPage.isAccountLink(driver));
	}

	@Test
	public void TC_04_MyProductReview() {
		userHomePage.hoverMouseToComputersDropdown();
		userDesktopsPage = userHomePage.hoverMouseToDesktopsLinkInComputersDropdownAndClick();
		userProductInformationPage = userDesktopsPage.clickToProductBuildYourOwnComputer();
		userProductReviewPage = userProductInformationPage.clickToLinkAddYourReview();
		userProductReviewPage.sendKeyToReviewTitleTextbox(reviewTitle);
		userProductReviewPage.sendKeyToReviewTextTextbox(reviewText);
		userProductReviewPage.clickToRatingPoint1RadioButton();
		userProductReviewPage.clickToSubmitReviewButton();
		
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();
		userMyProductReviewPage = (UserMyProductReviewPageObject) userCustomerInfoPage.openMyAccountPageByName(driver, UserCustomerInfoSidebar.MY_PRODUCT_REVIEW);
		
		Assert.assertEquals(userMyProductReviewPage.getTextFromRecentReviewTitle(), reviewTitle);
		Assert.assertEquals(userMyProductReviewPage.getTextFromRecentReviewText(), reviewText);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
