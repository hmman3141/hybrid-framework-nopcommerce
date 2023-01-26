package pageUIs.wordpress.admin;

public class AdminPostSearchPageUI {
	public static final String ADD_NEW_POST = "xpath=//a[@class='page-title-action' and text()='Add New']";
	public static final String TITLE_OF_RECENTLY_ADDED_POST = "css=tbody#the-list>tr:nth-child(1)>td[data-colname='Title']>strong>a";
	public static final String EDIT_BUTTON_OF_RECENTLY_ADDED_POST = "css=tbody#the-list>tr:nth-child(1)>td[data-colname='Title']>div.row-actions>span.edit>a";
}
