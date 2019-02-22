package gov.cancer.pageobject.helper;

import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.openqa.selenium.WebElement;

public class RelatedResource {

  WebElement element;

  public RelatedResource(WebElement element) {
    this.element = element;
  }

  public Boolean isLinkElement() {
    if(element != null) {
      return element.getTagName().equalsIgnoreCase("a");
    }
    else
      return false;
  }

  public String getLinkText() {
    if(element != null) {
      return element.getText();
    }
    else
      return "";
  }

  public String getLinkUrl() {
    throw new NotImplementedException("getLinkUrl");
  }

  public Boolean getIsExternal() {
    // Return true for links to anything other than .gov sites.
    throw new NotImplementedException("getIsExternal");
  }
}
