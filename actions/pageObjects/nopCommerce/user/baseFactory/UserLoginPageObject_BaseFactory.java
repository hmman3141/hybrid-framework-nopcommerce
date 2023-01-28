package pageObjects.nopCommerce.user.baseFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BaseFactory;

public class UserLoginPageObject_BaseFactory extends BaseFactory {
	private WebDriver driver;

	public UserLoginPageObject_BaseFactory(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(@class,'login-button')]")
	private WebElement loginButton;
	@FindBy(id = "Email")
	private WebElement emailTextBox;
	@FindBy(id = "Password")
	private WebElement passwordTextBox;
	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;
	@FindBy(xpath = "//div[contains(@class,'message-error')]")
	private WebElement emailNonexistentErrorMessage;

	public void clickOnLoginButton() {
		waitForElementVisibile(driver, loginButton);
		clickOnElement(driver, loginButton);
	}

	public String getErrorMessageAtEmailInput() {
		waitForElementVisibile(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public String getErrorMessageByNonExistentEmail() {
		waitForElementVisibile(driver, emailNonexistentErrorMessage);
		return getElementText(driver, emailNonexistentErrorMessage);
	}

	public void sendKeyToEmailTextbox(String string) {
		waitForElementVisibile(driver, emailTextBox);
		sendKeyToElement(driver, emailTextBox, string);
	}

	public void sendKeyToPasswordTextbox(String string) {
		waitForElementVisibile(driver, passwordTextBox);
		sendKeyToElement(driver, passwordTextBox, string);
	}
}
