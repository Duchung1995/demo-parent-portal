package com.demo.parentPortal;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalVaribles;
import commons.PageGeneratorManager;
import pageObjects.LoginPageObject;
import pageObjects.StudentPageObject;
import pageUIs.ParentPortalPageUI;

public class Parent_Portal_Test_Case extends BaseTest {

  WebDriver driver;
  LoginPageObject loginPage;
  StudentPageObject studentPage;

  @Parameters("browser")
  @BeforeClass
  public void beforeClass(String browserName) {
    log.info("Pre-conditions - Step 1. Open Demo site");
    driver = openMultiBrowser(browserName);
    loginPage = PageGeneratorManager.getLoginPage(driver);

    log.info("Pre-conditions - Step 2. Input  and Password");
    loginPage.inputToEmailTextbox(GlobalVaribles.EMAIL);
    loginPage.inputToPasswordTextbox(GlobalVaribles.PASSWORD);

    log.info("Pre-conditions - Step 3. Click on Sign In button");
    studentPage = loginPage.clickToLoginButton();

    log.info("Pre-conditions - Step 4. Verify Parent Portal page is displayed");
    verifyTrue(studentPage.isStudentPageDisplayed(driver, ParentPortalPageUI.ADMIN_LBL));
  }

  @Test
  public void TC_01_Verify_Filter_Student_Access_Request_With_Inactive() {
    log.info("TC_01_Verify_Filter - Step 1. Click on Filter button");
    studentPage.clickToFilterButton(driver, ParentPortalPageUI.FILTER_BTN);

    log.info("TC_01_Verify_Filter - Step 2. Select Inactive status");
    studentPage.selectInactiveStatus(driver, ParentPortalPageUI.REQUEST_STATUS_DROPDOWN, GlobalVaribles.REQUEST_STATUS);

    log.info("TC_01_Verify_Filter - Step 3. Click on Apply Filter button");
    studentPage.clickToApplyFilterButton(driver, ParentPortalPageUI.APPLY_FILTER_BTN);

    log.info("TC_01_Verify_Filter - Step 4. Verify only Inactive status is displayed");
    verifyTrue(studentPage.isInactiveStatusFiltered(
        driver,
        ParentPortalPageUI.REQUEST_STATUS_COLUMN,
        GlobalVaribles.REQUEST_STATUS
    ));
  }

  @Test
  public void TC_02_Verify_Sorting_Of_First_Name_Column() {
    log.info("TC_02_Verify_Sorting - Step 1. Click on First Name column");
    studentPage.clickToFirrstNameColumn(driver, ParentPortalPageUI.FIRST_NAME_HEADER_COLUMN);
    log.info("TC_02_Verify_Sorting - Step 2. Verify data are sorted by DESC accordingly");
    verifyTrue(studentPage.isFirstNameColumnSortedByDESC(driver, ParentPortalPageUI.FIRST_NAME_DATA_COLUMN));
    log.info("TC_02_Verify_Sorting - Step 3. Click on First Name column");
    studentPage.clickToFirrstNameColumn(driver, ParentPortalPageUI.FIRST_NAME_HEADER_COLUMN);
    log.info("TC_02_Verify_Sorting - Step 4. Verify data are sorted by ASC accordingly");
    verifyTrue(studentPage.isFirstNameColumnSortedByASC(driver, ParentPortalPageUI.FIRST_NAME_DATA_COLUMN));
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
    closeBrowser(driver);
  }
}
