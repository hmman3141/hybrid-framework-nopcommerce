package PageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class AddressesPageObject extends BasePage {
	private WebDriver driver;

	public AddressesPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
}
