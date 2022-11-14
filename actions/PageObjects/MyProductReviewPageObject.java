package PageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class MyProductReviewPageObject extends BasePage {
	private WebDriver driver;

	public MyProductReviewPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
}
