package PageObjects;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Base.BaseClass;
import actiondriver.Action;

public class ContactPage extends BaseClass{
	
	Action action = new Action();
	
	public ContactPage() throws IOException {		
		PageFactory.initElements(driver,this);
	}
	
	 @FindBy(css="#contact-page>div.row>div.col-sm-8>div>h2")
	 private static WebElement ContactUsTitle;	
	 
	 @FindBy(name="name")
	 private static WebElement Contactname;
	 
	 @FindBy(name="email")
	 private static WebElement Contactemail;
	 
	 @FindBy(name="subject")
	 private static WebElement Contactsubject;
	 
	 @FindBy(id="message")
	 private static WebElement Contactmessage;
	 
	 @FindBy(css="#contact-us-form>div:nth-child(7)>input")
	 private static WebElement SubmitBtn;
	 
	 @FindBy(name="upload_file")
	 private static WebElement fileInput;
	 
	 @FindBy(css="div.status.alert.alert-success")
	 private static WebElement SuccessMsg; 
	 
	 @FindBy(css="#form-section > a")
	 private static WebElement BackHomebtn; 	 
	 
	 
	public void verifyTitle(String expectedresult){	
		 action.implicitWait(driver, 20);  
		 boolean res = action.GetText(ContactUsTitle,expectedresult);		
		 Assert.assertTrue(res,"'GET IN TOUCH!' is not displayed!");	
	}
	// Enter name, email, subject and message
	public void SendContact(String name,String email,String subject,String message) {		
		 action.type(Contactname, name);
		 action.type(Contactemail, email);
		 action.type(Contactsubject, subject);
		 action.type(Contactmessage, message);			
		// Provide the path of the file to be uploaded
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Uploads\\Contactfile.txt";       
        fileInput.sendKeys(filePath);
	}		
	
	public ContactPage ClickOnSubmitBtn() throws IOException {		
		action.ClickOnElement(driver,SubmitBtn);
		return new ContactPage();		
	}	
	public void switchToFrame() throws InterruptedException{		
		Alert a = driver.switchTo().alert();
		a.accept();
		Thread.sleep(2000); 
	}	
	public ContactPage verifySucessMessage(String expectedresult) throws IOException {		
		 action.implicitWait(driver, 50);  
		 boolean res = action.GetText(SuccessMsg,expectedresult);		
		 Assert.assertTrue(res,"Sucess message is not displayed!");		
		 return new ContactPage(); 
	}
	public HomePage ClickOnHomebtn() throws IOException {
		action.ClickOnElement(driver,BackHomebtn);
		return new HomePage();			
	} 
	public void getHomePageUrl(String expectedUrl){
        String actualPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualPageUrl,
        		"Redirection to home page failed!");
     }
	

}
