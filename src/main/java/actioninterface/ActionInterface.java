package actioninterface;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;


public interface ActionInterface {
	public void implicitWait(WebDriver driver, int timeOut);
	public void explicitWait(WebDriver driver, WebElement element, Duration timeOut);
	public boolean selectByValue(WebElement element,String value);	
    public boolean GetText(WebElement ele,String expectedresult);
    public boolean isElementVisible(WebDriver driver,By locator);
    public boolean areElementsVisible(WebDriver driver, By locator); 
    public void scrollDownToFooter(WebDriver driver, By locator);
    public boolean isPageScrolledUp(WebDriver driver);
    public void scrollDown(WebDriver driver,int x ,int y);
    public void scrollUpPage(WebDriver driver);
	public void MouseHoverElement(WebDriver driver, WebElement locator);
	public void ClickOnElement(WebDriver driver,WebElement element);
	public boolean type(WebElement ele, String text);
	public String getCurrentURL(WebDriver driver);
}
