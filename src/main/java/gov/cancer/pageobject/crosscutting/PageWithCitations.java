package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The Citation class is
 * used solely for verifying attributes of a page's Citation section.
 */
public class PageWithCitations extends PageObjectBase {

  @FindBy(how = How.CSS, using = "#cgvCitationSl")
  WebElement citationsSection;

  /********* CITATION SELECTORS ***********************/

  final public String citationheader = "#cgvCitationSl > div > h6";

  /********* CITATION Methods ***********************/

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */

  public PageWithCitations(String path) {
    super(path);
  }

  /* Returns true if Citation Section is displayed on the page */
  public boolean isCitationSectionPresent() {
    return citationsSection.isDisplayed();
  }

  /* Returns true if header of Citation is displayed */
  public boolean isCitationHeaderVisible() {
    if (ElementHelper.findElement(citationsSection, citationheader) != null)
      return ElementHelper.findElement(citationsSection, citationheader).isDisplayed();
    else
      return false;
  }

  /* Returns the header of the Citation */
  public String getCitationHeaderText() {
    if (ElementHelper.findElement(citationsSection, citationheader) != null)
      return ElementHelper.findElement(citationsSection, citationheader).getText();
    else
      return null;
  }

}
// /* Returns text of Citations */
// public String citationText() {
//
// return citationsSection.getText().trim();
// }
//
