package pageUIs.nopCommerce.user;

public class BasePageUI {
	public static final String ADDRESSES_LINK = "xpath=//ul[@class='list']/li[contains(@class,'customer-addresses')]";
	public static final String REWARD_POINTS_LINK = "xpath=//ul[@class='list']/li[contains(@class,'reward-points')]";
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//ul[@class='list']/li[contains(@class,'customer-reviews')]";
	public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";
	public static final String LOGOUT_LINK_AT_USER = "xpath=//a[@class='ico-logout']";
	public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT = "xpath=//ul[@class='list']/li[contains(@class,'%s')]";
}
