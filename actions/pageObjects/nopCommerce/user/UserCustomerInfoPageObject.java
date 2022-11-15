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
}
