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

	public UserProductInformationPageObject clickToProductBuildYourOwnComputer() {
		waitForElementVisibile(driver, UserDesktopsPageUI.BUILD_YOUR_OWN_PRODUCT_LINK);
		clickToElement(driver, UserDesktopsPageUI.BUILD_YOUR_OWN_PRODUCT_LINK);
		return PageGeneratorManager.getUserProductInformationPage(driver);
	}
}
