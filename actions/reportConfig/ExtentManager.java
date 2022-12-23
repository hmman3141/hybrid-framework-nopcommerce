package reportConfig;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import commons.GlobalConstants;

public class ExtentManager {
	public static final ExtentReports extentReports = new ExtentReports();

	public static synchronized ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				GlobalConstants.REPORT_HTML5_FOLDER_PATH + File.separator + "extent-report.html");
		reporter.config().setReportName("Sample Extent Report");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Blog Name", "SW Test Academy");
		extentReports.setSystemInfo("Author", "Onur Baskirt");
		return extentReports;
	}
}