package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
	private WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public boolean isCustomerInfoDisplay() {
		waitForAllElementsVisibile(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		return isElementDisplayed(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_HEADER);
	}
	
	public void clickToFemaleRadioButton() {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.FEMALE_RADIO);
		clickToElement(driver, UserCustomerInfoPageUI.FEMALE_RADIO);
	}
	
	public void clickToSaveButton() {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.SAVE_CUSTOMER_INFO_BUTTON);
		clickToElement(driver, UserCustomerInfoPageUI.SAVE_CUSTOMER_INFO_BUTTON);
	}
	
	public void sendKeyToFirstNameTextBoxInput(String key) {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX_INPUT);
		sendKeyToElement(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX_INPUT, key);
	}
	
	public void sendKeyToLastNameTextBoxInput(String key) {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX_INPUT);
		sendKeyToElement(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX_INPUT, key);
	}
	
	public void sendKeyToEmailTextBoxInput(String key) {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX_INPUT);
		sendKeyToElement(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX_INPUT, key);
	}
	
	public void sendKeyToCompanyTextBoxInput(String key) {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.COMPANY_TEXTBOX_INPUT);
		sendKeyToElement(driver, UserCustomerInfoPageUI.COMPANY_TEXTBOX_INPUT, key);
	}
	
	public void selectItemFromDayCheckbox(String itemName) {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.DAY_OF_BIRTH_DROPDOWN);
		selectItemInDefaultDropdown(driver, UserCustomerInfoPageUI.DAY_OF_BIRTH_DROPDOWN, itemName);
	}
	
	public void selectItemFromMonthCheckbox(String itemName) {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.MONTH_OF_BIRTH_DROPDOWN);
		selectItemInDefaultDropdown(driver, UserCustomerInfoPageUI.MONTH_OF_BIRTH_DROPDOWN, itemName);
	}
	
	public void selectItemFromYearCheckbox(String itemName) {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.YEAR_OF_BIRTH_DROPDOWN);
		selectItemInDefaultDropdown(driver, UserCustomerInfoPageUI.YEAR_OF_BIRTH_DROPDOWN, itemName);
	}
	
	public String getTextFromFirstNameTextBoxInput() {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX_INPUT);
		return getElementTextJS(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX_INPUT);
	}
	
	public String getTextFromLastNameTextBoxInput() {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX_INPUT);
		return getElementTextJS(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX_INPUT);
	}
	
	public String getTextFromEmailNameTextBoxInput() {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX_INPUT);
		return getElementTextJS(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX_INPUT);
	}
	
	public String getTextFromCompanyNameTextBoxInput() {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.COMPANY_TEXTBOX_INPUT);
		return getElementTextJS(driver, UserCustomerInfoPageUI.COMPANY_TEXTBOX_INPUT);
	}
	
	public String getTextFromDayOfBirthDropdown() {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.DAY_OF_BIRTH_DROPDOWN);
		return getSelectedItemDefaultDropdown(driver, UserCustomerInfoPageUI.DAY_OF_BIRTH_DROPDOWN);
	}
	
	public String getTextFromMonthOfBirthDropdown() {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.MONTH_OF_BIRTH_DROPDOWN);
		return getSelectedItemDefaultDropdown(driver, UserCustomerInfoPageUI.MONTH_OF_BIRTH_DROPDOWN);
	}
	
	public String getTextFromYearOfBirthDropdown() {
		waitForElementVisibile(driver, UserCustomerInfoPageUI.YEAR_OF_BIRTH_DROPDOWN);
		return getSelectedItemDefaultDropdown(driver, UserCustomerInfoPageUI.YEAR_OF_BIRTH_DROPDOWN);
	}
}
