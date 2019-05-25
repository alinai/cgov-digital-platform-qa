package gov.cancer.tests.fields;

import java.util.Iterator;
import java.util.List;

import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithCitations;
import gov.cancer.pageobject.helper.Citation;
import gov.cancer.pageobject.helper.Link;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;



public class Citations_Test extends TestObjectBase {


  public static void main(String[] args) {

    String path = "/about-cancer/treatment/side-effects";
    TestRunner.run(PageWithCitations.class, path, (PageWithCitations page) -> {

      boolean sectionIsPresent = page.isCitationSectionPresent();
      boolean headerIsPresent = page.isCitationHeaderPresent();

      String headerText = page.getCitationHeaderText();

      // Gets a list of three citations.
      List<Citation> citationList = page.getCitationList();

      // The first citation has no PUBMED link.
      Citation plainCitation = citationList.get(0);
      String text1 = plainCitation.getText();
      boolean hasPubMedLink = plainCitation.hasPubMedLink();

      // The third citation does have a PUBMED link.
      Citation pubmedCitation = citationList.get(2);
      String text2 = pubmedCitation.getText();
      Link pubmedLink = pubmedCitation.getPubMedLink();

    });

  }

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
