package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageOptions;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with page options.
 */
public class PageOptions_Test extends TestObjectBase {

  /**
   * Are pageoptions container displayed on pages where they're expected?
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageOptionsPaths")
  public void pageoptionsIsVisible(String path) {

    TestRunner.run(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertTrue(page.IsVisible(), "PageOption is visible.");

    });

  }

  /**
   * Are the Print Buttons displayed on pages where they're expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPageOptionsPaths")
  public void verifyPrintButtonIsVisible(String path) {
    String cssSelector = "page-options--print";
    TestRunner.run(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertTrue(page.ButtonVisible(cssSelector), "Print Button is visible.");

    });

  }

  /**
   * At desktop breakpoint, are the Font Resizer Buttons displayed on pages where
   * they're expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getFontResizerPresentPaths")
  public void verifyFontResizerButtonIsVisibleAtDesktop(String path) {
    String cssSelector = "page-options--resize";
    TestRunner.runDesktop(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertTrue(page.ButtonVisible(cssSelector), "FontResizer Button is visible at Desktop breakpoint.");

    });

  }

  /**
   * At tablet breakpoint, are the Font Resizer Buttons displayed on pages where
   * they're NOT expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getFontResizerPresentPaths")
  public void verifyFontResizerButtonIsVisibleAtTablet(String path) {
    String cssSelector = "page-options--resize";
    TestRunner.runTablet(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertFalse(page.ButtonVisible(cssSelector), "Error: Font Resizer Button is visible at Tablet breakpoint");

    });

  }

  /**
   * At mobile breakpoint, are the Font Resizer Button displayed on pages where
   * they're NOT expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getFontResizerPresentPaths")
  public void verifyFontResizerButtonIsVisibleAtMobile(String path) {
    String cssSelector = "page-options--resize";
    TestRunner.runMobile(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertFalse(page.ButtonVisible(cssSelector), "Error: Font Resizer Button is visible at Mobile breakpoint");

    });

  }

  /**
   * Are the Font Resizer Buttons displayed on pages where they're NOT expected?
   *
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getFontResizerAbsentPaths")
  public void verifyFontResizerButtonIsNotVisible(String path) {
    String cssSelector = "page-options--resize";
    TestRunner.run(PageOptions.class, path, (PageOptions page) -> {

      Assert.assertFalse(page.ButtonVisible(cssSelector), "Error: Font Resizer Button is visible.");

    });

  }

  /************** Data Providers *************/
  /**
   * Retrieves a list of paths to pages which are expected to have pageoptions
   * container.
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPageOptionsPaths")
  public Iterator<Object[]> getPageOptionsPaths() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("page-options-data.xlsx"), "pages_with_pageoptions", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have fontresizers
   *
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getFontResizerPresentPaths")
  public Iterator<Object[]> getFontResizerPresentPaths() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("page-options-data.xlsx"), "pages_with_fontresizers", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected NOT to have
   * fontresizers
   *
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getFontResizerAbsentPaths")
  public Iterator<Object[]> getFontResizerAbsentPaths() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("page-options-data.xlsx"), "pages_without_fontresizers", columns);
  }

}
