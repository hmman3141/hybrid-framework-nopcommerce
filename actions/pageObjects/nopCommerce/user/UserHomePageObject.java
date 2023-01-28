package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.BasePageUI;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void hoverMouseToComputersDropdown() {
		waitForElementVisibile(driver, BasePageUI.COMPUTER_DROPDOWN_TOPMENU);
		hoverMouseToElement(driver, BasePageUI.COMPUTER_DROPDOWN_TOPMENU);
	}
	
	public UserDesktopsPageObject hoverMouseToDesktopsLinkInComputersDropdownAndClick() {
		waitForAllElementsVisibile(driver, BasePageUI.DESKTOPS_DROPDOWN_TOPMENU_CHOICE);
		hoverMouseToElementAndClick(driver, BasePageUI.DESKTOPS_DROPDOWN_TOPMENU_CHOICE);
		return PageGeneratorManager.getUserDesktopsPage(driver);
	}

	public UserRegisterPageObject clickOnRegisterLink() {
		waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
		clickOnElement(driver, UserHomePageUI.REGISTER_LINK);
		return new UserRegisterPageObject(driver);
	}
	
	public UserLoginPageObject clickOnLoginLink() {
		waitForElementVisibile(driver, UserHomePageUI.LOGIN_LINK);
		clickOnElement(driver, UserHomePageUI.LOGIN_LINK);
		return new UserLoginPageObject(driver);
	}
	
	public UserCustomerInfoPageObject clickOnMyAccountLink() {
		waitForElementVisibile(driver, UserHomePageUI.ACCOUNT_LINK);
		clickOnElement(driver, UserHomePageUI.ACCOUNT_LINK);
		return new UserCustomerInfoPageObject(driver);
	}
	
	public boolean isAccountLink() {
		return isElementPresence(driver, UserHomePageUI.ACCOUNT_LINK);
	}
}
