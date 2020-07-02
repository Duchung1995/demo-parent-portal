package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {

  WebDriver driver;

  public LoginPageObject(WebDriver mappingDriver) {
    driver = mappingDriver;
  }

  public void inputToEmailTextbox(String username) {
    waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
    clearDataElement(driver, LoginPageUI.EMAIL_TEXTBOX);
    sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, username);
  }

  public void inputToPasswordTextbox(String password) {
    waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
    clearDataElement(driver, LoginPageUI.PASSWORD_TEXTBOX);
    sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
  }

  public StudentPageObject clickToLoginButton() {
    waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
    clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    return PageGeneratorManager.getStudentPage(driver);
  }
}
