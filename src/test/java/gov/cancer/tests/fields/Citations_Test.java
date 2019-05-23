package gov.cancer.tests.fields;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithCitations;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class Citations_Test extends TestObjectBase {

  /**
   * This method is checking if the Citation section exists on the pages
   *
   * @param path
   *          Path of the page to check.
   * @param language
   *          Language of the page to check.
   */
  @Test(dataProvider = "getPageCitationsPaths")
  public void verifyCitationSectionIsPresent(String path) {

    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {

      Assert.assertTrue(page.isCitationSectionPresent(), "Citation Section is present.");

    });

  }

  /**
   * This method is checking if the header on the Citation is displayed on the
   * pages
   *
   * @param path
   *          Path of the page to check.
   * @param language
   *          Language of the page to check.
   */
  @Test(dataProvider = "getPageCitationsPaths")
  public void verifyCitationHeaderIsVisible(String path) {

    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {

      Assert.assertTrue(page.isCitationHeaderPresent(), "Citation Header is visible.");
    });
  }

  /**
   * This method is checking if the correct Citation is displayed on pages
   *
   * @param path
   *          Path of the page to check.
   * @param Expectedlanguage
   *          Language of the page to check.
   */
  @Test(dataProvider = "getCitationHeaderTextPaths")
  public void isHeaderTextCorrect(String path, String expectedHeaderText) {

    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {

      Assert.assertEquals(page.getCitationHeaderText(), expectedHeaderText, "Citation header text is correct.");

    });
  }

  /**
   * This method is checking if the length of text displayed on header on the
   * pages is in considerable range
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageCitationsPaths")
  public void verifyCitationTextlengthIsCorrect(String path) {
    // TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {
    // List<Citation> list = page.getCitationList();

  }

  /*******************************************
   * DATA PROVIDER
   *******************************************/

  /**
   * Retrieves a list of paths to pages which are expected to have Citations
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         language.
   */
  @DataProvider(name = "getPageCitationsPaths")
  public Iterator<Object[]> getPageCitationsPaths() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("citations-data.xlsx"), "pages_with_citations", columns);

  }

  /**
   * Retrieves a list of paths to pages which are expected to have Citations
   *
   * @return An iterable list of two element arrays, each containing a path and
   *         language.
   */
  @DataProvider(name = "getCitationHeaderTextPaths")
  public Iterator<Object[]> getCitationHeadetTextPaths() {
    String[] columns = { "path", "expectedHeaderText" };
    return new ExcelDataReader(getDataFilePath("citations-data.xlsx"), "pages_with_citations", columns);

  }
}