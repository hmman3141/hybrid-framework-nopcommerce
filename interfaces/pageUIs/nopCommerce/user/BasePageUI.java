package pageUIs.nopCommerce.user;

public class BasePageUI {
	public static final String ADDRESSES_LINK = "xpath=//ul[@class='list']/li[contains(@class,'customer-addresses')]/a";
	public static final String REWARD_POINTS_LINK = "xpath=//ul[@class='list']/li[contains(@class,'reward-points')]/a";
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//ul[@class='list']/li[contains(@class,'customer-reviews')]/a";
	public static final String COMPUTER_DROPDOWN_TOPMENU = "xpath=//ul[contains(@class,'top-menu') and contains(@class,'notmobile')]//a[contains(text(),'Computers')]";
	public static final String DESKTOPS_DROPDOWN_TOPMENU_CHOICE = "xpath=//ul[contains(@class,'top-menu') and contains(@class,'notmobile')]//a[contains(text(),'Computers')]/parent::li//li/a[contains(text(),'Desktops')]";
	public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";
	public static final String LOGOUT_LINK_AT_USER = "xpath=//a[@class='ico-logout']";
	public static final String ACCOUNT_LINK_AT_USER = "xpath=//a[@class='ico-account']";
	public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT = "xpath=//ul[@class='list']/li[contains(@class,'%s')]/a";
}
