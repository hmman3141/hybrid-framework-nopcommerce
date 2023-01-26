package pageUIs.wordpress.admin;

public class AdminPostAddEditPageUI {
	public static final String ADD_EDIT_TITLE = "css=h1.editor-post-title";
	public static final String ADD_PARAGRAPH_CONTENT_FOR_CONTENT_BLOCK = "css=p.wp-block-paragraph";
	public static final String ADD_CONTENT_BLOCK_BUTTON = "css=div.block-editor-inserter>button";
	public static final String ADD_PARAGRAPH_BUTTON_FOR_CONTENT_BLOCK = "xpath=//span[text()='Paragraph']/parent::span/parent::button";
	public static final String PUBLISH_BUTTON = "xpath=//button[text()='Publish']";
	public static final String UPDATE_BUTTON = "xpath=//button[text()='Update']";
	public static final String POST_PUBLISH_BUTTON = "css=button.editor-post-publish-button";
	public static final String VIEW_POST_LINK = "xpath=//a[text()='View Post']";
	public static final String VIEW_UPDATED_POST_LINK = "xpath=//div[@class='components-snackbar__content']/a[text()='View Post']";
	public static final String DYNAMIC_CONTENTS_BY_ORDINAL = "css=div.wp-block-post-content>*:nth-child(%s)";
	public static final String VIEW_ALL_POSTS_LINK = "xpath=//a[@aria-label='View Posts']";
	public static final String POST_SUCCESSFULLY_UPDATED_MESSAGE = "css=div.components-editor-notices__snackbar div.components-snackbar__content";
}
