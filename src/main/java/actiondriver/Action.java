package actiondriver;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;

import Base.BaseClass;
import actioninterface.ActionInterface;

public class Action extends BaseClass implements ActionInterface{
	
	public Action() throws IOException {
		super();			
	}

	@Override
	public void implicitWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	//to avoid StaleElementReferenceException 
	@Override
	public void explicitWait(WebDriver driver, WebElement element, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		  try {
			  element.click();
		    } catch (StaleElementReferenceException e) {
		    	 explicitWait(driver, element, timeOut);
		    	 element.click();
		    }
	}		
	@Override
	public boolean selectByValue(WebElement element,String value) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Value");
			} else {
				System.out.println("Option not selected by Value");
			}
		}
	}	
	@Override
	public boolean GetText(WebElement ele,String expectedresult) {
		 boolean flag = false;
		 String actualresult  = ele.getText();		 
		 if (actualresult.equalsIgnoreCase(expectedresult)) {
			    flag = true;			 
	            System.out.println("The expected text is visible: " + expectedresult);
	      } else {
	            System.out.println("The expected text is not visible. Actual text: " + actualresult);
	      } 
		 
		 return flag;
	}	
	@Override
    public boolean isElementVisible(WebDriver driver,By locator) {
        try {
            WebElement element = driver.findElement(locator);     
            return element.isDisplayed();
        } catch (StaleElementReferenceException 
        		| ElementNotInteractableException e) {
                
            return false;
        }
    }	
	@Override
    public boolean areElementsVisible(WebDriver driver, By locator) {
        try {           
            for (WebElement element : driver.findElements(locator)) {
                if (!element.isDisplayed()) {
                    return false;
                }
            }
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
	@Override	
	public void scrollDownToFooter(WebDriver driver,By locator) {	   
	    WebElement footer = driver.findElement(locator);
	    if (footer != null) {	      
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", footer);
	    } else {
	        System.out.println("Footer element not found on the page.");
	    }
	}
	@Override	
	 public void scrollUpPage(WebDriver driver) {	  
	    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    jsExecutor.executeScript("window.scrollTo(0, 0);");
	}
    @Override	
    public boolean isPageScrolledUp(WebDriver driver) {	        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        long scrollPosition = (Long) jsExecutor.executeScript("return window.pageYOffset;");
        return scrollPosition < 200; 
     }	    
	@Override	
	 public void scrollDown(WebDriver driver,int x ,int y) {	 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollTo(" + x + ", " + y + ");");
	}
	
	@Override
	 public void MouseHoverElement(WebDriver driver,WebElement locator) {
		 Actions actions = new Actions(driver); 
		 actions.moveToElement(locator).build().perform();			
	 }
	@Override
	 public void ClickOnElement(WebDriver driver,WebElement element){
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 wait.until(ExpectedConditions.elementToBeClickable(element));		
		 element.click();		
	 }
	@Override
	public boolean type(WebElement ele, String text) {
		boolean flag = false;
		try {
			flag = ele.isDisplayed();
			ele.clear();
			ele.sendKeys(text);			
			flag = true;
		} catch (Exception e) {
			System.out.println("Location Not found");
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully entered value");
			} else {
				System.out.println("Unable to enter value");
			}

		}
		return flag;
	}
	@Override
	public String getCurrentURL(WebDriver driver) {
		boolean flag = false;

		String text = driver.getCurrentUrl();
		if (flag) {
			System.out.println("Current URL is: \""+text+"\"");
		}
		return text;	
		
	} 	

}
