package commons;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

  private WebDriver driver;

  // Init log
  protected final Log log;

  // Constructor
  protected BaseTest() {
    log = LogFactory.getLog(getClass());
  }

  public WebDriver openMultiBrowser(String browserName) {
    if (browserName.equalsIgnoreCase("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
    } else if (browserName.equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
    } else if (browserName.equalsIgnoreCase("chromeheadless")) {
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("headless");
      options.addArguments("window-size=" + GlobalVaribles.HEADLESS_RESOLUTION);
      driver = new ChromeDriver(options);
    } else if (browserName.equalsIgnoreCase("ie")) {
      WebDriverManager.iedriver().arch32().setup();
      driver = new InternetExplorerDriver();
    } else if (browserName.equalsIgnoreCase("edge")) {
      WebDriverManager.edgedriver().setup();
      driver = new EdgeDriver();
    } else {
      System.out.println("Please choose your browser name in TestNG xml file.");
    }
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(GlobalVaribles.LONG_TIMEOUT, TimeUnit.SECONDS);
    driver.get(GlobalVaribles.SITE_URL);
    return driver;
  }

  private boolean checkPassed(boolean condition) {
    boolean pass = true;
    try {
      if (condition == true) {
        log.info("===PASSED==");
      } else {
        log.info("===FAILED==");
      }
      Assert.assertTrue(condition);
    } catch (Throwable e) {
      pass = false;

      // Add error to ReportNG
      VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
      Reporter.getCurrentTestResult().setThrowable(e);
    }
    return pass;
  }

  protected boolean verifyTrue(boolean condition) {
    return checkPassed(condition);
  }

  private boolean checkFailed(boolean condition) {
    boolean pass = true;
    try {
      if (condition == false) {
        log.info("===PASSED===");
      } else {
        log.info("===FAILED===");
      }
      Assert.assertFalse(condition);
    } catch (Throwable e) {
      pass = false;
      VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
      Reporter.getCurrentTestResult().setThrowable(e);
    }
    return pass;
  }

  protected boolean verifyFalse(boolean condition) {
    return checkFailed(condition);
  }

  private boolean checkEquals(Object actual, Object expected) {
    boolean pass = true;
    boolean status;
    try {
      if (actual instanceof String && expected instanceof String) {
        actual = actual.toString().trim();
        log.info("Actual = " + actual);
        expected = expected.toString().trim();
        log.info("Expected = " + expected);
        status = (actual.equals(expected));
      } else {
        status = (actual == expected);
      }

      log.info("Compare value = " + status);
      if (status) {
        log.info("===PASSED===");
      } else {
        log.info("===FAILED===");
      }
      Assert.assertEquals(actual, expected, "Value is not matching!");
    } catch (Throwable e) {
      pass = false;
      VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
      Reporter.getCurrentTestResult().setThrowable(e);
    }
    return pass;
  }

  protected boolean verifyEquals(Object actual, Object expected) {
    return checkEquals(actual, expected);
  }

  public WebDriver getDriver() {
    return driver;
  }

  protected void closeBrowser(WebDriver driver) {
    driver.close();
  }
}
