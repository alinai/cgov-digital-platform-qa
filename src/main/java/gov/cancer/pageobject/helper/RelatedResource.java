package gov.cancer.pageobject.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RelatedResource {

	WebElement element;

	public RelatedResource(WebElement element) {
		this.element = element;
	}

	/*
	 * check if this element text has a tag 'a'
	 */
	public Boolean isLinkElement() {
		if(element != null) {
			return element.getTagName().equalsIgnoreCase("a");
		}
		else
			return false;
	}

	/*
	 * if element is null return false;
	 * else trim the element text and if its empty return false
	 */
	public boolean isLinkTextBlank() {		
		if(element != null) 
			return !element.getText().trim().isEmpty();	
		return false;
	}

	/*
	 * Does it have a non-empty href?
	 */
	public boolean isLinkHrefBlank() {
		if(element != null)
			return !element.getAttribute("href").isEmpty(); 
		else 
			return false;
	}

	/*
	 * checks for empty space, tab, new line presence
	 */
	public boolean isLinkBroken() {
		if(element != null)
			return !Pattern.matches("\\s+", element.getAttribute("href"));
		else
			return false;
	}


	/*
	 * Does external links have Exit disclaimer styling
	 */
	public Boolean isLinkExternal(){

		URL url=null;
		try {
			url = new URL(element.getAttribute("href"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String hostName =url.getHost();
		
		// hostName can be dynamically referenced from the config file
		if (element != null && !hostName.equalsIgnoreCase("www.devbox"))
			return element.findElement(By.xpath("following-sibling::a")).getAttribute("title").equals("Exit Disclaimer");
		else
			return true;

	}
}
