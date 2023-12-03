package PageObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Base.BaseClass;
import actiondriver.Action;

public class TestCasesPage extends BaseClass{

	public TestCasesPage() throws IOException {
		PageFactory.initElements(driver,this);
	}
	Action action = new Action(); 
	 
	 @FindBy(css="ul.navbar-nav>li:nth-child(5)>a")
	 private static WebElement TestCasesLink; 
	 
	 @FindBy(tagName="h2")
	 private static WebElement Title;	
	 
	 public void verifyPageTitle(String expectedresult){   		
		 Boolean res = action.GetText(Title,expectedresult);	  
         Assert.assertTrue(res,"The title is not correct");	     
	 }	
	 
	 public TestCasesPage ClickOnTestCasesLink() throws IOException {	
		action.explicitWait(driver, TestCasesLink, Duration.ofSeconds(50));
		return new TestCasesPage();
	 }
}
