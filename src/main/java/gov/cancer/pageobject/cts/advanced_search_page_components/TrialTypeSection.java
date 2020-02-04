package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementChange;
import gov.cancer.framework.ElementHelper;
import gov.cancer.framework.ScrollUtil;
import gov.cancer.pageobject.components.CheckBox;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * This page represents Trial Type section from Advanced Search Form page
 */
public class TrialTypeSection extends Component {

  //Limit results to accepting healthy volunteers
  private WebElement limitResultsToggle;
  // 'All' checkbox
  private CheckBox allCheckbox;
  // List of all option checkboxes
  private List<CheckBox> allOptions = new ArrayList<>();
  //section title
  private WebElement title;
  // help link
  private Link helpLink;
  //webdriver is added to support scroll method (see below)
  private WebDriver driver;
  //private list of webelements is used for scroll method (to access the last checkbox and scroll to it)
  private List<WebElement> otherCheckBoxes;


  //Locator to find all trial type checkboxes
  private final static String GENERIC_CHECKBOX_LOCATOR = ".group-trial-types div.cts-checkbox ";
  private final static String LIMIT_RESULTS_LOCATOR = ":scope .cts-toggle__label[for='hv']";
  private final static String ALL_CHECK_BOX_LOCATOR = ":scope .select-all div";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";


  /**
   * Constructor
   *
   * @param element
   */
  public TrialTypeSection(WebDriver driver, WebElement element) {
    super(element);
    this.driver=driver;
    limitResultsToggle = ElementHelper.findElement(element, LIMIT_RESULTS_LOCATOR);
    allCheckbox = new CheckBox(ElementHelper.findElement(element, ALL_CHECK_BOX_LOCATOR));
    otherCheckBoxes = ElementHelper.findElements(element, GENERIC_CHECKBOX_LOCATOR);
    for (WebElement we : otherCheckBoxes) {
      allOptions.add(new CheckBox(we));
    }
    helpLink = new Link(ElementHelper.findElement(element, HELP_LINK_LOCATOR));
    title = ElementHelper.findElement(element, TITLE_LOCATOR);
  }

  /**
   * Getter method to retrieve checkbox 'all'
   *
   * @return
   */
  public CheckBox getAllCheckbox() {
    return allCheckbox;
  }

  /**
   * Getter method for all checkboxes (Except 'All')
   *
   * @return all option of checkboxes
   */
  public List<CheckBox> getAllOptions() {
    return allOptions;
  }

  /**
   * Method to switch 'limit result' toggle
   *
   * @param switchToggle if 'Yes'(true) and 'yes' span is not displayed , then method clicks on a toggle
   *                     if 'No' (false) and 'yes' span is displayed, then click to unselect
   */
  public void limitToHealthyVolunteer(boolean switchToggle) {
    if (switchToggle && !(ElementHelper.findElement(limitResultsToggle, ":scope .pos").isDisplayed())) {
      limitResultsToggle.click();
    } else if (!switchToggle && ElementHelper.findElement(limitResultsToggle, ":scope .pos").isDisplayed())
      limitResultsToggle.click();
  }

  /**
   * Returns title text
   */
  public String getTitle() {
    return title.getText();
  }

  /**
   * Getter for Help Link
   *
   * @return
   */
  public Link getHelpLink() {
    return helpLink;
  }
  /**
   *This method is scrolling until the first checkbox is visible
   * It is necessary, because of the presence of 'sticky block (form action)' which receives the click, instead of
   * the checkbox
   */
  public void scrollUntilCheckBoxVisible(){
    ScrollUtil.scrollIntoview(driver, otherCheckBoxes.get(1));
  }

  /**
   * This method is waiting for a 'healthy volunteers' toggle to be  switched to 'Yes'
   * It checks for the toggle element's text to be 'Yes'
   */
  public void waitForYes (){
    ElementChange.WaitForText(driver,limitResultsToggle,"Yes");
  }
  /**
   * This method is waiting for a 'healthy volunteers' toggle to be  switched to 'No'
   * It checks for the toggle element's text to be 'No'
   */
  public void waitForNo(){
    ElementChange.WaitForText(driver,limitResultsToggle,"No");
  }
  /**
   * This method checks the spans of Toggle element - when the toggle is swicthed to 'Yes', '.pos' span
   * element is displayed when the toggle is swicthed to 'No', '.neg' span element is displayed
   * Returns TRUE  for 'Yes' position and FALSE for 'No'
   * If none of the states are true, then there is something completely off with the state of toggle -
   * exception is thrown
   * @return
   */
  public boolean getToggleState(){
    if(ElementHelper.findElement(limitResultsToggle, ":scope .pos").isDisplayed()){
      return true;
    }else if(ElementHelper.findElement(limitResultsToggle, ":scope .neg").isDisplayed()){
      return false;
    }else {
      throw new RuntimeException("Error -Toggle state is undefined");
    }
  }
}
