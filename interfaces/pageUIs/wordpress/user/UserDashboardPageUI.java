package pageUIs.wordpress.user;

public class UserDashboardPageUI {
	public static final String DYNAMIC_TITLE_BY_POST_TITLE = "xpath=//li[contains(@class,'wp-block-post')]/h2[@class='wp-block-post-title']/a[text()='%s']";
	public static final String DYNAMIC_CONTENT_BY_POST_CONTENT = "xpath=//li[contains(@class,'wp-block-post')]/div[@class='wp-block-post-excerpt']/p[text()='%s ']";
}
