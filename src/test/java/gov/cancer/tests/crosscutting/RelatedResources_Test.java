package gov.cancer.tests.crosscutting;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithRelatedResources;
import gov.cancer.pageobject.helper.RelatedResource;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class RelatedResources_Test extends TestObjectBase {

  @Test(dataProvider = "getPagesWithRelatedResources")
  public void VerifyRelatedResourcesSectionAppears(String path) {

    // Get the page.
    TestRunner.run(PageWithRelatedResources.class, path, (PageWithRelatedResources page) -> {

      // Assert the related resources section is visible.
      Assert.assertTrue(page.hasRelatedResources(), "Related Resources section is visible.");

    });
  }

  @Test(dataProvider = "getPagesWithoutRelatedResources")
  public void VerifyRelatedResourcesSectionDoesNotAppear(String path) {

    // Get the page.
    TestRunner.run(PageWithRelatedResources.class, path, (PageWithRelatedResources page) -> {

      // Assert the related resources section is visible.
      Assert.assertFalse(page.hasRelatedResources(), "Page has no Related Resources section.");

    });
  }

  @Test(dataProvider = "getPagesWithRelatedResources")
  public void VerifyRelatedResourcesLinks(String path) {

    // Get the page.
    TestRunner.run(PageWithRelatedResources.class, path, (PageWithRelatedResources page) -> {

      List<RelatedResource> resources = page.getRelatedResources();

      // Are there any related resources?
      Assert.assertTrue(resources.size() > 0);

      // For each resource.
      for (RelatedResource item : resources) {

        // Is it a link?
        Assert.assertTrue(item.isLinkElement(), "Is a link tag.");

        // Does it have non-blank text?
        // Does it have a non-empty href?
      }

    });
  }

  /**
   * Returns a list of paths for pagpes which are expected to display
   * a related resources section.
   */
  @DataProvider(name = "getPagesWithRelatedResources")
  public Iterator<Object[]> getPagesWithRelatedResources() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("related-resources-data.xlsx"), "pages_with_related_resources", columns);
  }

  /**
   * Returns a list of paths for pagpes which are NOT expected to display a related
   * resources section.
   */
  @DataProvider(name = "getPagesWithoutRelatedResources")
  public Iterator<Object[]> getPagesWithoutRelatedResources() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("related-resources-data.xlsx"), "pages_without_related_resources", columns);
  }

}
