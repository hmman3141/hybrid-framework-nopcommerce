package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import pageUIs.wordpress.admin.BasePageUI;

public class AdminPageGeneratorManager {
	public static AdminLoginPageObject getAdminLoginPageObject(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPageObject(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	public static AdminPostSearchPageObject getAdminPostSearchPageObject(WebDriver driver) {
		return new AdminPostSearchPageObject(driver);
	}

	public static AdminPostViewPageObject getAdminPostViewPageObject(WebDriver driver) {
		return new AdminPostViewPageObject(driver);
	}

	public static AdminDashboardPageObject openAdminURL(WebDriver driver) {
		driver.get(BasePageUI.ADMIN_URL);
		return new AdminDashboardPageObject(driver);
	}
}
