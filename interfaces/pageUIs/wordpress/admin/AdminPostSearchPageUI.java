package pageUIs.wordpress.admin;

public class AdminPostSearchPageUI {
	public static final String ADD_NEW_POST = "xpath=//a[@class='page-title-action' and text()='Add New']";
	public static final String TITLE_OF_RECENTLY_ADDED_POST = "css=tbody#the-list>tr:nth-child(1)>td[data-colname='Title']>strong>a";
	public static final String EDIT_BUTTON_OF_RECENTLY_ADDED_POST = "css=tbody#the-list>tr:nth-child(1)>td[data-colname='Title']>div.row-actions>span.edit>a";
	public static final String SELECT_ALL_BUTTON = "xpath=//table[contains(@class,'wp-list-table')]//label[text()='Select All']/following-sibling::input";
	public static final String BULK_ACTIONS_SELECT = "id=bulk-action-selector-top";
	public static final String BULK_ACTIONS_APPLY_BUTTON = "id=doaction";
	public static final String TRASH_LINK = "css=ul.subsubsub>li.trash>a";
	public static final String EMPTY_TRASH_BUTTON = "id=delete_all";
	public static final String NO_POSTS_MESSAGE = "css=tbody#the-list>tr.no-items>td";
}
