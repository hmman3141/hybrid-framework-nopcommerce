package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FILE_FOLDER_PATH = PROJECT_PATH + File.separator + "uploadFiles";
	public static final String DOWNLOAD_FILE_FOLDER_PATH = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG_FOLDER_PATH = PROJECT_PATH + File.separator + "browserLogs";
	public static final String REPORT_HTML5_FOLDER_PATH = PROJECT_PATH + File.separator + "reportHTML";
	public static final String OS_NAME = "window";
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 10;
	public static final long RETRY_TEST_FAIL = 3;
	
	public static final String SAUCELAB_EMAIL = "huaminhman15112000-326ef";
	public static final String SAUCELAB_ID = "277eb7ad-3b0f-4023-8aef-d84b58315dc2";
	public static final String SAUCELAB_BUILDID = "selenium-build-1X0H9";
	
}
