package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.LoginPageObject;
import pageObjects.StudentPageObject;

public class PageGeneratorManager {

  public static LoginPageObject getLoginPage(WebDriver driver) {
    return new LoginPageObject(driver);
  }

  public static StudentPageObject getStudentPage(WebDriver driver) {
    return new StudentPageObject(driver);
  }
}
