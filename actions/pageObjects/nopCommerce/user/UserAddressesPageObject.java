package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserAddressesPageUI;

public class UserAddressesPageObject extends BasePage {
	private WebDriver driver;

	public UserAddressesPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToAddNewButton() {
		waitForElementClickable(driver, UserAddressesPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, UserAddressesPageUI.ADD_NEW_BUTTON);
	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, UserAddressesPageUI.SAVE_BUTTON);
		clickToElement(driver, UserAddressesPageUI.SAVE_BUTTON);
	}

	public void sendKeyToFirstNameTextbox(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserAddressesPageUI.FIRST_NAME_TEXTBOX, key);
	}

	public void sendKeyToLastNameTextbox(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserAddressesPageUI.LAST_NAME_TEXTBOX, key);
	}

	public void sendKeyToEmailTextbox(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, UserAddressesPageUI.EMAIL_TEXTBOX, key);
	}

	public void sendKeyToCompanyTextbox(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.COMPANY_TEXTBOX);
		sendKeyToElement(driver, UserAddressesPageUI.COMPANY_TEXTBOX, key);
	}

	public void selectItemFromCountryDropdown(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.COUNTRY_DROPDOWN);
		waitForAllElementsPresence(driver, UserAddressesPageUI.COUNTRY_OPTION);
		selectItemInDefaultDropdown(driver, UserAddressesPageUI.COUNTRY_DROPDOWN, key);
	}

	public void selectItemFromStateDropdown(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.STATE_DROPDOWN);
		waitForAllElementsPresence(driver, UserAddressesPageUI.STATE_OPTION);
		selectItemInDefaultDropdown(driver, UserAddressesPageUI.STATE_DROPDOWN, key);
	}

	public void sendKeyToCityTextbox(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.CITY_TEXTBOX);
		sendKeyToElement(driver, UserAddressesPageUI.CITY_TEXTBOX, key);
	}

	public void sendKeyToAddress1Textbox(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.ADDRESS_1_TEXTBOX);
		sendKeyToElement(driver, UserAddressesPageUI.ADDRESS_1_TEXTBOX, key);
	}

	public void sendKeyToAddress2Textbox(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.ADDRESS_2_TEXTBOX);
		sendKeyToElement(driver, UserAddressesPageUI.ADDRESS_2_TEXTBOX, key);
	}

	public void sendKeyToPostalCodeTextbox(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.POSTAL_CODE_TEXTBOX);
		sendKeyToElement(driver, UserAddressesPageUI.POSTAL_CODE_TEXTBOX, key);
	}

	public void sendKeyToPhoneNumberTextbox(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.PHONE_NUMBER_TEXTBOX);
		sendKeyToElement(driver, UserAddressesPageUI.PHONE_NUMBER_TEXTBOX, key);
	}

	public void sendKeyToFaxNumberTextbox(String key) {
		waitForElementVisibile(driver, UserAddressesPageUI.FAX_NUMBER_TEXTBOX);
		sendKeyToElement(driver, UserAddressesPageUI.FAX_NUMBER_TEXTBOX, key);
	}

	public String getTextFromNameText(String fullName) {
		waitForElementVisibile(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.NAME_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.NAME_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
	}

	public String getTextFromEmailText(String fullName) {
		waitForElementVisibile(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.EMAIL_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.EMAIL_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
	}

	public String getTextFromCompanyText(String fullName) {
		waitForElementVisibile(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.COMPANY_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.COMPANY_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
	}

	public String getTextFromCountryText(String fullName) {
		waitForElementVisibile(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.COUNTRY_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.COUNTRY_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
	}

	public String getTextFromCityStateZipText(String fullName) {
		waitForElementVisibile(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.CITY_STATE_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.CITY_STATE_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
	}

	public String getTextFromAddress1Text(String fullName) {
		waitForElementVisibile(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.ADDRESS1_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.ADDRESS1_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
	}

	public String getTextFromAddress2Text(String fullName) {
		waitForElementVisibile(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.ADDRESS2_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.ADDRESS2_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
	}

	public String getTextFromPhoneNumberText(String fullName) {
		waitForElementVisibile(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.PHONE_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.PHONE_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
	}

	public String getTextFromFaxNumberText(String fullName) {
		waitForElementVisibile(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.FAX_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_ACCOUNT_INFORMATION, fullName,
				UserAddressesPageUI.FAX_CLASS_FOR_DYNAMIC_ACCOUNT_INFORMATION);
	}
}
