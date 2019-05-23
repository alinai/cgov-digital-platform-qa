package gov.cancer.pageobject.helper;

import java.net.URL;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebElement;

public class Citation {

  /**
   * this is the element representing the entire metatag.
   */
  private WebElement theCitation;

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */

  public Citation(WebElement element) {
    this.theCitation = element;
  }

  public String getText() {

    throw new NotImplementedException("TODO:implement getText method");

  }

  public boolean hasPubMedLink() {

    throw new NotImplementedException("TODO:implement hasPubMedLink method");
  }

  public URL getPubMedLink() {
    throw new NotImplementedException("TODO:implement getUrl method");
  }
}
