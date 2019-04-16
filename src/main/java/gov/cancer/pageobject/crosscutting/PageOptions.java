package gov.cancer.pageobject.crosscutting;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

public class PageOptions extends PageObjectBase {

  String printcss = ".page-options--print";
  String fontresizercss = ".page-options--resize";

  @FindBy(how = How.CSS, using = "#PageOptionsControl1")
  WebElement pageOptioncontrol;

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public PageOptions(String path) {
    super(path);

  }

  /*
   * Testing if the PageOptionsControl exists on page and is visible
   */
  public boolean isPageOptionsVisible() {
    List<WebElement> elementExists = getPageOptionsControl();

    if (elementExists.size() > 0) {
      WebElement pocElement = elementExists.get(0);
      return pocElement.isDisplayed();
    }
    return false;
  }

  /*
   * Testing if the PageOptions Buttons exists on page and is visible
   */
  public boolean isPrintButtonVisible() {
    return ElementHelper.isVisible(pageOptioncontrol, printcss);
  }

  public boolean isFontResizerButtonVisible() {
    return ElementHelper.isVisible(pageOptioncontrol, fontresizercss);
  }

  /*
   * Finds and returns PageOptionsControl
   */
  private List<WebElement> getPageOptionsControl() {
    List<WebElement> pocControls = getBrowser().findElements(By.cssSelector("#PageOptionsControl1"));
    return pocControls;
  }
}
