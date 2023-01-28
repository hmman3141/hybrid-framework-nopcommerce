package pageObjects.nopCommerce.user.baseFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BaseFactory;

public class UserHomePageObject_BaseFactory extends BaseFactory {
	private WebDriver driver;

	public UserHomePageObject_BaseFactory(WebDriver driver) {
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
	
	public void clickOnRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickOnElement(driver, registerLink);
	}

	public void clickOnLoginLink() {
		waitForElementVisibile(driver, loginLink);
		clickOnElement(driver, loginLink);
	}

	public boolean isAccountLink() {
		return false;
	}
}
