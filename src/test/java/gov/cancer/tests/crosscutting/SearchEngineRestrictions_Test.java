package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.SearchEngineRestrictions;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class SearchEngineRestrictions_Test extends TestObjectBase {

  /**
   * Verify that for meta property = robot the attribute "index" is rendered on
   * pages when "Include in Search" is set.
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithIndex")
  public void verifyIncludeSearchIsSelected(String path) {

    TestRunner.run(SearchEngineRestrictions.class, path, (SearchEngineRestrictions page) -> {
      Assert.assertEquals("index", page.getIncludeInSearchMetaTagProperty());

    });
  }

  /**
   * Verify that for meta property = robot the attribute "noindex" is rendered on
   * pages when "Exclude From Search" is set.
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithNoIndex")
  public void verifyExcludeFromSearchIsSelected(String path) {

    TestRunner.run(SearchEngineRestrictions.class, path, (SearchEngineRestrictions page) -> {
      Assert.assertEquals("noindex", page.getIncludeInSearchMetaTagProperty());

    });

  }

  /************** Data Providers *************/
  /**
   * Retrieves a list of paths to pages which are expected to have Include in
   * Search Selected
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithIndex")
  public Iterator<Object[]> getPagesWithIndex() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("searchenginerestriction-data.xlsx"), "getpages_with_index", columns);
  }

  /**
   * Retrieves a list of paths to pages which are expected to have Exclude from
   * Search Selected
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPagesWithNoIndex")
  public Iterator<Object[]> getPagesWithNoIndex() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("searchenginerestriction-data.xlsx"), "getpages_with_noindex", columns);
  }
}
