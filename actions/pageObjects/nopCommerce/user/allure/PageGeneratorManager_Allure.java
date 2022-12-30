package pageObjects.nopCommerce.user.allure;

import org.openqa.selenium.WebDriver;

import pageObjects.nopCommerce.user.UserLoginPageObject;

public class PageGeneratorManager_Allure{
	public static UserHomePageObject_Allure getUserHomePage(WebDriver driver) {
		return new UserHomePageObject_Allure(driver);
	}
	
	public static UserRegisterPageObject_Allure getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject_Allure(driver);
	}
	
	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
}
