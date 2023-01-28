package pageObjects.jQuery.uploadFiles;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class HomePageObject extends BasePage {
	private String[] files = { "file2.png", "file3.png" };
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isFileLoadedByName(String fileName) {
		waitForElementVisibile(driver, pageUIs.jQuery.uploadFiles.HomePageUI.DYNAMIC_FILE_NAME, fileName);
		return isElementDisplayed(driver, pageUIs.jQuery.uploadFiles.HomePageUI.DYNAMIC_FILE_NAME, fileName);
	}

	public void clickOnMultipleStartButtons() {
		clickOnElements(driver, pageUIs.jQuery.uploadFiles.HomePageUI.MULTIPLE_BUTTON_START_UPLOAD_FILE);
	}

	public void sendFilesToElement() {
		uploadMultipleFiles(driver, pageUIs.jQuery.uploadFiles.HomePageUI.BUTTON_ADD_FILE, files);
	}
}
