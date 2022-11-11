package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseFactory {
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, WebElement element, String key) {
		element.clear();
		element.sendKeys(key);
	}

	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}

	public void waitForElementVisibile(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
