package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

  /* Web Browser */
  public void openUrl(WebDriver driver, String urlValue) {
    driver.get(urlValue);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  public String getPageTitle(WebDriver driver) {
    return driver.getTitle();
  }

  public String getCurrentPageUrl(WebDriver driver) {
    return driver.getCurrentUrl();
  }

  /* Web Element */
  private WebElement element;
  private Select select;
  private JavascriptExecutor javascriptExecutor;
  private WebDriverWait waitExplicit;
  private By byLocator;
  private int longTimeout = GlobalVaribles.LONG_TIMEOUT;

  public void clickToElement(WebDriver driver, String locator) {
    element = driver.findElement(By.xpath(locator));
    if (driver.toString().contains("internet explorer")) {
      clickToElementByJS(driver, locator);
      sleepInSecond(driver, 5);
    } else {
      element.click();
    }
  }

  public void clearDataElement(WebDriver driver, String locator) {
    element = driver.findElement(By.xpath(locator));
    element.clear();
  }

  public void sendkeyToElement(WebDriver driver, String locator, String value) {
    element = driver.findElement(By.xpath(locator));
    element.sendKeys(value);
  }

  public void selectItemInDropdown(WebDriver driver, String locator, String itemText) {
    element = driver.findElement(By.xpath(locator));
    select = new Select(element);
    select.selectByVisibleText(itemText);
  }

  public boolean isControlDisplayed(WebDriver driver, String locator) {
    element = driver.findElement(By.xpath(locator));
    return element.isDisplayed();
  }

  public boolean isOneTextDisplayed(WebDriver driver, String locator, String expectedText) {
    boolean flag = true;
    List<WebElement> elements = driver.findElements(By.xpath(locator));
    for (WebElement element : elements) {
      if (element.getText().equals(expectedText)) {
        flag = true;
      } else {
        flag = false;
        break;
      }
    }
    return flag;
  }

  public boolean isDataSortedAscending(WebDriver driver, String locator) {
    ArrayList<String> arrayList = new ArrayList<String>();
    List<WebElement> elementList = driver.findElements(By.xpath(locator));
    for (WebElement element : elementList) {
      arrayList.add(element.getText());
    }
    ArrayList<String> sortedList = new ArrayList<String>();
    for (String child : arrayList) {
      sortedList.add(child);
    }
    Collections.sort(arrayList);
    return sortedList.equals(arrayList);
  }

  public boolean isDataSortedDescending(WebDriver driver, String locator) {
    ArrayList<String> arrayList = new ArrayList<String>();
    List<WebElement> elementList = driver.findElements(By.xpath(locator));
    for (WebElement element : elementList) {
      arrayList.add(element.getText());
    }
    ArrayList<String> sortedList = new ArrayList<String>();
    for (String child : arrayList) {
      sortedList.add(child);
    }
    Collections.sort(arrayList);
    Collections.reverse(arrayList);
    return sortedList.equals(arrayList);
  }

  public void clickToElementByJS(WebDriver driver, String locator) {
    element = driver.findElement(By.xpath(locator));
    javascriptExecutor = (JavascriptExecutor) driver;
    javascriptExecutor.executeScript("arguments[0].click();", element);
  }

  public void waitForElementVisible(WebDriver driver, String locator) {
    waitExplicit = new WebDriverWait(driver, longTimeout);
    byLocator = By.xpath(locator);
    waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
  }

  public void waitForElementClickable(WebDriver driver, String locator) {
    waitExplicit = new WebDriverWait(driver, longTimeout);
    byLocator = By.xpath(locator);
    waitExplicit.until(ExpectedConditions.elementToBeClickable(byLocator));
  }

  public void sleepInSecond(WebDriver driver, long timeInSecond) {
    try {
      Thread.sleep(timeInSecond * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
