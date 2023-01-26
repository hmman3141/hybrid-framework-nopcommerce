package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
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
}
