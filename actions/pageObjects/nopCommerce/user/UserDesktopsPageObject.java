package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserDesktopsPageUI;

public class UserDesktopsPageObject extends BasePage {
	private WebDriver driver;

	public UserDesktopsPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public UserProductInformationPageObject clickOnProductBuildYourOwnComputer() {
		waitForElementVisibile(driver, UserDesktopsPageUI.BUILD_YOUR_OWN_PRODUCT_LINK);
		clickOnElement(driver, UserDesktopsPageUI.BUILD_YOUR_OWN_PRODUCT_LINK);
		return PageGeneratorManager.getUserProductInformationPage(driver);
	}
}
