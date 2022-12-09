package pageObjects.jQuery;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.jQuery.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickPaginationByNumber(int pageNumber) {
		String number = String.valueOf(pageNumber);
		waitForElementClickable(driver, HomePageUI.DYNAMIC_PAGINATION_BY_NUMBER, number);
		clickToElement(driver, HomePageUI.DYNAMIC_PAGINATION_BY_NUMBER, number);
	}

	public void sendKeyToTextbox(String textBoxName, String key) {
		waitForElementVisibile(driver, HomePageUI.DYNAMIC_SEARCH_TEXTBOX, textBoxName);
		sendKeyToElement(driver, HomePageUI.DYNAMIC_SEARCH_TEXTBOX, key, textBoxName);
	}
}
