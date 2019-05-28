package gov.cancer.pageobject.crosscutting;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.helper.Citation;

/**
 * Pseudo page object representing any page in the system. The Citation class is
 * used solely for verifying attributes of a page's Citation section.
 */
public class PageWithCitations extends PageObjectBase {

  /********* CITATION SELECTORS ***********************/
  final public String citationSection = "#cgvCitationSl";
  final public String citationheader = "#cgvCitationSl > div > h6";

  // @FindBy(how = How.CSS, using = "#cgvCitationSl")
  // WebElement citationsSection;

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
  public boolean isSectionPresent() {
    return ElementHelper.isVisible(getBrowser(), citationSection);
  }

  /* Returns true if header of Citation is displayed */
  public boolean isHeaderPresent() {
    if (ElementHelper.findElement(getBrowser(), citationheader) != null)
      return ElementHelper.findElement(getBrowser(), citationheader).isDisplayed();
    else
      return false;
  }

  /* Returns the header of the Citation */
  public String getHeaderText() {
    if (ElementHelper.findElement(getBrowser(), citationheader) != null)
      return ElementHelper.findElement(getBrowser(), citationheader).getText();
    else
      return null;
  }

  /**
   * Find all of the citation objects on the page.
   */
  public List<Citation> getCitationList() {
    List<Citation> lists = new ArrayList<Citation>();
    List<WebElement> citationslists = ElementHelper.findElements(getBrowser(), citationSection);
    for (WebElement list : citationslists) {
      lists.add(new Citation(list));
    }
    return lists;

    // throw new NotImplementedException("TODO:implement getCitationList method");
  }

}
