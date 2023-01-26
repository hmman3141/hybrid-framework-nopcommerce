package commons;

import static reportConfig.ExtentTestManager.getTest;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

public class BaseLog {
	private final Log log;

	public BaseLog() {
		log = LogFactory.getLog(getClass());
	}

	public static String getBase64Screenshot(WebDriver driver) {
		return "data:image/png;base64,"
				+ ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
	}

	public void Info(String message) {
		log.info(message);
		getTest().log(Status.INFO, message);
	}
	
	public void Pass() {
		log.info("PASSED");
		getTest().log(Status.PASS, "PASSED");
	}

	public void Pass(String message) {
		log.info(message);
		getTest().log(Status.PASS, message);
	}

	public void Fail(WebDriver driver) {
		log.info("FAILED");
		String base64Screenshot = getBase64Screenshot(driver);
		getTest().log(Status.FAIL, "FAILED",
				getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
	}
	
	public void Fail(String message) {
		log.info(message);
		getTest().log(Status.FAIL, message);
	}
	
	public void Skip(String message) {
		log.info(message);
		getTest().log(Status.SKIP, message);
	}
}
