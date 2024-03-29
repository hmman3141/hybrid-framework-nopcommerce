package commons.wordpress;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import commons.GlobalConstants;

public class BasePage_Wordpress {
	public BasePage_Wordpress getBasePage() {
		return new BasePage_Wordpress();
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

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
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
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
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

	private By getByLocator(String locator) {
		String lowerCaseLocator = locator.toLowerCase();
		if (lowerCaseLocator.startsWith("id=")) {
			return By.id(locator.substring(3));
		} else if (lowerCaseLocator.startsWith("xpath=")) {
			return By.xpath(locator.substring(6));
		} else if (lowerCaseLocator.startsWith("class=")) {
			return By.className(locator.substring(6));
		} else if (lowerCaseLocator.startsWith("css=")) {
			return By.cssSelector(locator.substring(4));
		} else
			throw new RuntimeException("Locator type is not supported");
	}

	private String getDynamicXpath(String locator, String... dynamicVariables) {
		return String.format(locator, (Object[]) dynamicVariables);
	}

	private WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	private WebElement getWebElement(WebDriver driver, String xpathLocator, String... dynamicVariables) {
		return driver.findElement(getByLocator(getDynamicXpath(xpathLocator, dynamicVariables)));
	}

	private List<WebElement> getWebElements(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}

	private List<WebElement> getWebElements(WebDriver driver, String xpathLocator, String... dynamicVariables) {
		return driver.findElements(getByLocator(getDynamicXpath(xpathLocator, dynamicVariables)));
	}

	protected void clickOnElement(WebDriver driver, String locator) {
		waitForElementClickable(driver, locator);
		getWebElement(driver, locator).click();
	}

	protected void clickOnElements(WebDriver driver, String locator) {
		waitForAllElementsPresence(driver, locator);
		for (WebElement element : getWebElements(driver, locator)) {
			waitForElementClickable(driver, locator);
			element.click();
		}
	}

	protected void clickOnElement(WebDriver driver, String xpathLocator, String... dynamicVariables) {
		waitForElementClickable(driver, xpathLocator, dynamicVariables);
		getWebElement(driver, xpathLocator, dynamicVariables).click();
	}

	protected void sendKeyToElement(WebDriver driver, String locator, String key) {
		waitForElementVisibile(driver, locator);
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(key);
	}

	protected void sendKeyToElement(WebDriver driver, String xpathLocator, String key, String... dynamicVariables) {
		waitForElementVisibile(driver, xpathLocator, dynamicVariables);
		WebElement element = getWebElement(driver, xpathLocator, dynamicVariables);
		element.clear();
		element.sendKeys(key);
	}

	protected void pressKeysToDeleteMessage(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	protected void pressKeysToDeleteMessage(WebDriver driver, String locator, String... dynamicVariables) {
		Actions action = new Actions(driver);
		WebElement element = getWebElement(driver, locator, dynamicVariables);
		action.sendKeys(element, Keys.CONTROL, Keys.chord("A"), Keys.DELETE).perform();
	}

	protected String getElementText(WebDriver driver, String locator) {
		waitForElementVisibile(driver, locator);
		return getWebElement(driver, locator).getText();
	}

	protected String getElementText(WebDriver driver, String xpathLocator, String... dynamicVariables) {
		waitForElementVisibile(driver, xpathLocator, dynamicVariables);
		return getWebElement(driver, xpathLocator, dynamicVariables).getText();
	}

	protected String getElementTextJS(WebDriver driver, String locator) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return (String) executor.executeScript("return arguments[0].value", getWebElement(driver, locator));
	}

	protected String getElementTextJS(WebDriver driver, String xpathLocator, String... dynamicVariables) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return (String) executor.executeScript("return arguments[0].value",
				getWebElement(driver, xpathLocator, dynamicVariables));
	}

	protected Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	protected void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locator, String text) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(text);
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	protected void selectValueFromCustomDropdown(WebDriver driver, String parentLocator, String childLocator,
			String value) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);

		getWebElement(driver, parentLocator).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

		List<WebElement> listOfElements = getWebElements(driver, childLocator);
		for (WebElement element : listOfElements) {
			if (element.getText().trim().equals(value)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
				element.click();
				break;
			}
		}
	}

	protected String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}

	protected String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}

	protected String convertRgbaToHex(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex();
	}

	protected int getElementSize(WebDriver driver, String locator) {
		return getWebElements(driver, locator).size();
	}

	protected int getElementSize(WebDriver driver, String xpathLocator, String... dynamicVariables) {
		return getWebElements(driver, xpathLocator, dynamicVariables).size();
	}

	protected void selectValueFromDefaultCheckbox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (!element.isSelected())
			element.click();
	}

	protected void selectValueFromDefaultCheckbox(WebDriver driver, String xpathLocator, String... dynamicVariables) {
		WebElement element = getWebElement(driver, xpathLocator, dynamicVariables);
		if (!element.isSelected())
			element.click();
	}

	protected void deselectValueFromCheckbox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (element.isSelected())
			element.click();
	}

	protected void selectAllValuesFromCheckbox(WebDriver driver, String locator) {
		List<WebElement> listElement = getWebElements(driver, locator);
		for (WebElement webElement : listElement) {
			if (!webElement.isSelected() && webElement.isEnabled()) {
				webElement.click();
			}
		}
	}

	protected void deselectAllValuesFromCheckbox(WebDriver driver, String locator) {
		List<WebElement> listElement = getWebElements(driver, locator);
		for (WebElement webElement : listElement) {
			if (webElement.isSelected() && webElement.isEnabled()) {
				webElement.click();
			}
		}
	}

	protected boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}

	protected boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return getWebElement(driver, locator).isDisplayed();
		} catch (NoSuchElementException e) {
			overrideGlobalImplicitTime(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicVariables) {
		try {
			return getWebElement(driver, locator, dynamicVariables).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalImplicitTime(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getWebElements(driver, locator);
		overrideGlobalImplicitTime(driver, GlobalConstants.LONG_TIMEOUT);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && elements.get(0).isDisplayed()) {
			return false;
		} else {
			return true;
		}
	}

	protected void overrideGlobalImplicitTime(WebDriver driver, long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	protected boolean isElementEnable(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	protected boolean isElementPresence(WebDriver driver, String locator) {
		return getWebElements(driver, locator).size() != 0;
	}

	protected void switchToFrameIFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}

	protected void hoverMouseToElementAndClick(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).click().perform();
	}

	protected void hoverMouseToElementAndClick(WebDriver driver, String locator, String... dynamicVariables) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator, dynamicVariables)).click().perform();
	}

	protected String getValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage",
				getWebElement(driver, locator));
	}

	protected void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", getWebElement(driver, locator));
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void clickOnElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click()", getWebElement(driver, locator));
	}

	protected void uploadMultipleFiles(WebDriver driver, String locator, String... fileNames) {
		String fullFileName = "";
		for (String fileName : fileNames) {
			fullFileName += GlobalConstants.UPLOAD_FILE_FOLDER_PATH + File.separator + fileName + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, locator).sendKeys(fullFileName);
	}

	protected void waitForElementVisibile(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}

	protected void waitForElementVisibile(WebDriver driver, String xpathLocator, String... dynamicVariables) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(getByLocator(getDynamicXpath(xpathLocator, dynamicVariables))));
	}

	protected void waitForAllElementsVisibile(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
	}

	protected void waitForElementInvisibile(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}

	protected void waitForAllElementsInvisibile(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, locator)));
	}

	protected void waitForElementClickable(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}

	protected void waitForElementClickable(WebDriver driver, String xpathLocator, String... dynamicVariables) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(
				ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(xpathLocator, dynamicVariables))));
	}

	protected void waitForElementPresence(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
	}

	protected void waitForAllElementsPresence(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
	}
}
