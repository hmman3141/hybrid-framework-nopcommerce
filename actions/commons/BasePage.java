package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;
import pageUIs.nopCommerce.user.BasePageUI;

public class BasePage {
	public BasePage getBasePage() {
		return new BasePage();
	}

	public void openURL(WebDriver driver, String url) {
		driver.get(url);
	}

	protected String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void back(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	protected void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	protected void sendKeyToAlert(WebDriver driver, String key) {
		driver.switchTo().alert().sendKeys(key);
	}

	protected String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	protected void waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void switchToWindowByID(WebDriver driver, String currentWindowID) {
		Set<String> windowIDs = driver.getWindowHandles();
		for (String ID : windowIDs) {
			if (!ID.equals(currentWindowID)) {
				driver.switchTo().window(ID);
				break;
			}
		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> windowIDs = driver.getWindowHandles();
		String currentWindowID = driver.getWindowHandle();
		boolean titleFound = false;

		for (String ID : windowIDs) {
			driver.switchTo().window(ID);
			if (getPageTitle(driver).equals(title)) {
				titleFound = true;
				break;
			}
		}

		if (!titleFound)
			driver.switchTo().window(currentWindowID);
	}

	protected void closeAllTabsWithoutParent(WebDriver driver, String parentID) {
		Set<String> windowIDs = driver.getWindowHandles();
		for (String ID : windowIDs) {
			if (!ID.equals(parentID)) {
				driver.switchTo().window(ID);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	private By getByXPath(String xpath) {
		return By.xpath(xpath);
	}

	private WebElement getWebElement(WebDriver driver, String xpath) {
		return driver.findElement(getByXPath(xpath));
	}

	private List<WebElement> getWebElements(WebDriver driver, String xpath) {
		return driver.findElements(getByXPath(xpath));
	}

	public void clickToElement(WebDriver driver, String xpath) {
		getWebElement(driver, xpath).click();
	}

	public void sendKeyToElement(WebDriver driver, String xpath, String key) {
		WebElement element = getWebElement(driver, xpath);
		element.clear();
		element.sendKeys(key);
	}

	public String getElementText(WebDriver driver, String xpath) {
		return getWebElement(driver, xpath).getText();
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String xpath, String text) {
		Select select = new Select(getWebElement(driver, xpath));
		select.selectByVisibleText(text);
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String xpath) {
		Select select = new Select(getWebElement(driver, xpath));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String xpath) {
		Select select = new Select(getWebElement(driver, xpath));
		return select.isMultiple();
	}

	protected void selectValueFromCustomDropdown(WebDriver driver, String parentXPath, String childXPath, String value) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);

		getWebElement(driver, parentXPath).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXPath(childXPath)));

		List<WebElement> listOfElements = getWebElements(driver, childXPath);
		for (WebElement element : listOfElements) {
			if (element.getText().trim().equals(value)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
				element.click();
				break;
			}
		}
	}

	protected String getElementAttribute(WebDriver driver, String xpath, String attributeName) {
		return getWebElement(driver, xpath).getAttribute(attributeName);
	}

	protected String getElementCssValue(WebDriver driver, String xpath, String propertyName) {
		return getWebElement(driver, xpath).getCssValue(propertyName);
	}

	protected String convertRgbaToHex(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex();
	}

	protected int getElementSize(WebDriver driver, String xpath) {
		return getWebElements(driver, xpath).size();
	}

	protected void selectValueFromDefaultCheckbox(WebDriver driver, String xpath) {
		WebElement element = getWebElement(driver, xpath);
		if (!element.isSelected())
			element.click();
	}

	protected void deselectValueFromCheckbox(WebDriver driver, String xpath) {
		WebElement element = getWebElement(driver, xpath);
		if (element.isSelected())
			element.click();
	}

	protected void selectAllValuesFromCheckbox(WebDriver driver, String xpath) {
		List<WebElement> listElement = getWebElements(driver, xpath);
		for (WebElement webElement : listElement) {
			if (!webElement.isSelected() && webElement.isEnabled()) {
				webElement.click();
			}
		}
	}

	protected void deselectAllValuesFromCheckbox(WebDriver driver, String xpath) {
		List<WebElement> listElement = getWebElements(driver, xpath);
		for (WebElement webElement : listElement) {
			if (webElement.isSelected() && webElement.isEnabled()) {
				webElement.click();
			}
		}
	}

	protected boolean isElementSelected(WebDriver driver, String xpath) {
		return getWebElement(driver, xpath).isSelected();
	}

	protected boolean isElementDisplayed(WebDriver driver, String xpath) {
		return getWebElement(driver, xpath).isDisplayed();
	}

	protected boolean isElementEnable(WebDriver driver, String xpath) {
		return getWebElement(driver, xpath).isEnabled();
	}
	
	protected boolean isElementPresence(WebDriver driver, String xpath) {
		return getWebElements(driver, xpath).size() != 0;
	}

	protected void switchToFrameIFrame(WebDriver driver, String xpath) {
		driver.switchTo().frame(getWebElement(driver, xpath));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String xpath) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpath)).perform();
	}

	protected String getValidationMessage(WebDriver driver, String xpath) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage", getWebElement(driver, xpath));
	}

	protected void scrollToElement(WebDriver driver, String xpath) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", getWebElement(driver, xpath));
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void clickToElementByJS(WebDriver driver, String xpath) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click()", getWebElement(driver, xpath));
	}

	protected void waitForElementVisibile(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXPath(xpath)));
	}

	protected void waitForAllElementsVisibile(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXPath(xpath)));
	}

	protected void waitForElementInvisibile(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXPath(xpath)));
	}

	protected void waitForAllElementsInvisibile(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, xpath)));
	}

	protected void waitForElementClickable(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXPath(xpath)));
	}

	protected void waitForElementPresence(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByXPath(xpath)));
	}

	protected void waitForAllElementsPresence(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXPath(xpath)));
	}
	
	public UserAddressesPageObject clickToAddressesLink(WebDriver driver) {
		waitForElementVisibile(driver, BasePageUI.ADDRESSES_LINK);
		clickToElement(driver, BasePageUI.ADDRESSES_LINK);
		return PageGeneratorManager.getUserAddressesPage(driver);
	}
	
	public UserRewardPointsPageObject clickToRewardPointsLink(WebDriver driver) {
		waitForElementVisibile(driver, BasePageUI.REWARD_POINTS_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINTS_LINK);
		return PageGeneratorManager.getUserRewardPointsPage(driver);
	}
	
	public UserMyProductReviewPageObject clickToMyProductReviewLink(WebDriver driver) {
		waitForElementVisibile(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}
	
	public UserHomePageObject clickToLogoutAtUserPage(WebDriver driver) {
		waitForElementVisibile(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public AdminLoginPageObject clickToLogoutAtAdminPage(WebDriver driver) {
		waitForElementVisibile(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
}
