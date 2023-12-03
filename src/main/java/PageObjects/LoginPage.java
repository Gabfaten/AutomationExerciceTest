package PageObjects;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Base.BaseClass;
import actiondriver.Action;

public class LoginPage extends BaseClass{
	
	Action action = new Action();	
	
	public LoginPage() throws IOException {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".login-form h2")
	private static WebElement LoginToyourAccTitle;	
	
	@FindBy(xpath="/html/body/section/div/div/div[1]/div/form/input[2]")
	private static WebElement LoginTextBox;

	@FindBy(name="password")
	private static WebElement passwordTextBox;	

	@FindBy(css=".login-form form button")
	private static WebElement LoginButton;	
	
	@FindBy(css=".login-form form p")
	private static WebElement ErrorMsg;
	
	@FindBy(xpath="//ul/li[10]/a[contains(text(), 'Logged in as')]")
	private static WebElement LoggedInName;	
	
	 @FindBy(xpath="//*[@id=\"form\"]//h2")
	 private static WebElement DeleteAccountTitle ;

	 public void verifyTitle(String expectedresult){ 		 
		 action.implicitWait(driver, 20);  
		 boolean actualres= action.GetText(LoginToyourAccTitle, expectedresult);
		 Assert.assertTrue(actualres,"The title is incorrect!");			
	 }	
	 
	 public void VerifyLoginAsUsername(String username) {
		 String expectedres = "Logged in as" + " "+ username;
	     String actualres= LoggedInName.getText();	     
	     Assert.assertEquals(expectedres, actualres, "username is not visible !");
	 }
     public HomePage performLogin(String email,String password) throws IOException{	
		action.type(LoginTextBox,email);
		action.type(passwordTextBox,password);		
		action.explicitWait(driver, LoginButton, Duration.ofSeconds(20));		
	
		return new HomePage();
	}
     
    public void ErrorIsDisplayed(String expectedresult){	
		action.implicitWait(driver, 20);  
		boolean actualres = action.GetText(ErrorMsg,expectedresult);
		Assert.assertTrue(actualres,"The error message is not displayed!");	
	}
	public String getLoginPageUrl(){
        String LoginPageUrl = action.getCurrentURL(driver);
        return LoginPageUrl;        
     }
	
     public void verifyAccountDeletedTitle(String expectedresult){	
		 action.implicitWait(driver, 20);  
		 boolean res = action.GetText(DeleteAccountTitle,expectedresult);		
		 Assert.assertTrue(res,"'Account deleted' not displayed!");		
	}

}
