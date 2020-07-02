package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.GlobalVaribles;

public class StudentPageObject extends BasePage {

  WebDriver driver;

  public StudentPageObject(WebDriver mappingDriver) {
    driver = mappingDriver;
  }

  public boolean isStudentPageDisplayed(WebDriver driver, String value) {
    waitForElementVisible(driver, value);
    return isControlDisplayed(driver, value);
  }

  public void clickToFilterButton(WebDriver driver, String locator) {
    waitForElementVisible(driver, locator);
    clickToElement(driver, locator);
  }

  public void selectInactiveStatus(WebDriver driver, String locator, String value) {
    waitForElementVisible(driver, locator);
    selectItemInDropdown(driver, locator, value);
  }

  public void clickToApplyFilterButton(WebDriver driver, String locator) {
    waitForElementVisible(driver, locator);
    waitForElementClickable(driver, locator);
    clickToElement(driver, locator);
    sleepInSecond(driver, GlobalVaribles.TIMEOUT);
  }

  public boolean isInactiveStatusFiltered(WebDriver driver, String locator, String value) {
    waitForElementVisible(driver, locator);
    return isOneTextDisplayed(driver, locator, value);
  }

  public void clickToFirrstNameColumn(WebDriver driver, String locator) {
    waitForElementVisible(driver, locator);
    clickToElement(driver, locator);
  }

  public boolean isFirstNameColumnSortedByDESC(WebDriver driver, String locator) {
    return isDataSortedDescending(driver, locator);
  }

  public boolean isFirstNameColumnSortedByASC(WebDriver driver, String locator) {
    return isDataSortedAscending(driver, locator);
  }
}
