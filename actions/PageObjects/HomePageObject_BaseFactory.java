package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BaseFactory;

public class HomePageObject_BaseFactory extends BaseFactory {
	private WebDriver driver;

	public HomePageObject_BaseFactory(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[@class='ico-register']")
	private WebElement registerLink;
	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement loginLink;
	@FindBy(xpath = "//a[@class='ico-account']")
	private WebElement accountLink;
	
	public void clickToRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickToElement(driver, registerLink);
	}

	public void clickToLoginLink() {
		waitForElementVisibile(driver, loginLink);
		clickToElement(driver, loginLink);
	}

	public boolean isAccountLink() {
		return false;
	}
}
