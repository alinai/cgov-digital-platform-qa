package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.pageobject.PageObjectBase;

public class SearchEngineRestrictions extends PageObjectBase {

  @FindBy(how = How.CSS, using = "head")
  WebElement pageHeader;

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public SearchEngineRestrictions(String path) {
    super(path);
  }

  /* Returns the meta tag content property of the page */
  public String getIncludeInSearchMetaTagProperty() {
    String tag = pageHeader.findElement(By.cssSelector("meta:nth-child(3)")).getAttribute("content");
    return tag;
  }

}
