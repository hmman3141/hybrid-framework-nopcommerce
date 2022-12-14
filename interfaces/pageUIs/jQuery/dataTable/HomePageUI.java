package pageUIs.jQuery.dataTable;

public class HomePageUI {
	public static final String PAGE_URL = "https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/";
	public static final String ROW_CONTENT_OF_TABLE = "css=table.qgrd tbody tr";
	public static final String DYNAMIC_PAGINATION_BY_NUMBER = "xpath=//div[@class='qgrd-wrap']//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String DYNAMIC_SEARCH_TEXTBOX = "xpath=//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
	public static final String DYNAMIC_INPUT_APPENDGRID = "xpath=//tbody//tr[%s]//td[count(//td[@class='ui-widget-header' and text()='%s']/preceding-sibling::td)+1]/input";
	public static final String DYNAMIC_ACTION_BUTTON_APPENDGRID = "css=tbody tr:nth-child(%s) button[title='%s']";
}
