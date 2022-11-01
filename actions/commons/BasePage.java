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

public class BasePage {
	public BasePage getBasePage() {
		return new BasePage();
	}

	public void openURL(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSource(WebDriver driver) {
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

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void sendKeyToAlert(WebDriver driver, String key) {
		driver.switchTo().alert().sendKeys(key);
	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchToWindowByID(WebDriver driver, String currentWindowID) {
		Set<String> windowIDs = driver.getWindowHandles();
		for (String ID : windowIDs) {
			if (!ID.equals(currentWindowID)) {
				driver.switchTo().window(ID);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
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

	public void closeAllTabsWithoutParent(WebDriver driver, String parentID) {
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

	public void selectItemInDefaultDropdown(WebDriver driver, String xpath, String text) {
		Select select = new Select(getWebElement(driver, xpath));
		select.selectByVisibleText(text);
	}

	public String getSelectedItemDefaultDropdown(WebDriver driver, String xpath) {
		Select select = new Select(getWebElement(driver, xpath));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String xpath) {
		Select select = new Select(getWebElement(driver, xpath));
		return select.isMultiple();
	}

	public void selectValueFromCustomDropdown(WebDriver driver, String parentXPath, String childXPath, String value) {
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

	public String getElementAttribute(WebDriver driver, String xpath, String attributeName) {
		return getWebElement(driver, xpath).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String xpath, String propertyName) {
		return getWebElement(driver, xpath).getCssValue(propertyName);
	}

	public String convertRgbaToHex(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex();
	}

	public int getElementSize(WebDriver driver, String xpath) {
		return getWebElements(driver, xpath).size();
	}

	public void selectValueFromDefaultCheckbox(WebDriver driver, String xpath) {
		WebElement element = getWebElement(driver, xpath);
		if (!element.isSelected())
			element.click();
	}

	public void deselectValueFromCheckbox(WebDriver driver, String xpath) {
		WebElement element = getWebElement(driver, xpath);
		if (element.isSelected())
			element.click();
	}

	public void selectAllValuesFromCheckbox(WebDriver driver, String xpath) {
		List<WebElement> listElement = getWebElements(driver, xpath);
		for (WebElement webElement : listElement) {
			if (!webElement.isSelected() && webElement.isEnabled()) {
				webElement.click();
			}
		}
	}

	public void deselectAllValuesFromCheckbox(WebDriver driver, String xpath) {
		List<WebElement> listElement = getWebElements(driver, xpath);
		for (WebElement webElement : listElement) {
			if (webElement.isSelected() && webElement.isEnabled()) {
				webElement.click();
			}
		}
	}

	public boolean isElementSelected(WebDriver driver, String xpath) {
		return getWebElement(driver, xpath).isSelected();
	}

	public boolean isElementDisplayed(WebDriver driver, String xpath) {
		return getWebElement(driver, xpath).isDisplayed();
	}

	public boolean isElementEnableed(WebDriver driver, String xpath) {
		return getWebElement(driver, xpath).isEnabled();
	}

	public void switchToFrameIFrame(WebDriver driver, String xpath) {
		driver.switchTo().frame(getWebElement(driver, xpath));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String xpath) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpath)).perform();
	}

	public String getValidationMessage(WebDriver driver, String xpath) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage", getWebElement(driver, xpath));
	}

	public void scrollToElement(WebDriver driver, String xpath) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", getWebElement(driver, xpath));
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void clickToElementByJS(WebDriver driver, String xpath) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click()", getWebElement(driver, xpath));
	}

	public void waitForElementVisibile(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXPath(xpath)));
	}

	public void waitForAllElementsVisibile(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXPath(xpath)));
	}

	public void waitForElementInvisibile(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXPath(xpath)));
	}

	public void waitForAllElementsInvisibile(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, xpath)));
	}

	public void waitForElementClickable(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXPath(xpath)));
	}

	public void waitForElementPresence(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByXPath(xpath)));
	}

	public void waitForAllElementsPresence(WebDriver driver, String xpath) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXPath(xpath)));
	}
}
