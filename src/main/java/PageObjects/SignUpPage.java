package PageObjects;
import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Base.BaseClass;
import actiondriver.Action;

public class SignUpPage extends BaseClass{
	Action action = new Action();
	
	public SignUpPage() throws IOException {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div[3]/div/form/button")
	private static WebElement SignupBtn;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div[3]/div/form/input[2]")
	private static WebElement NameNewAccount;	
	
	@FindBy(id="name")
	private static WebElement NameForNewAccount;

	@FindBy(xpath="//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")
	private static WebElement emailForNewAccount;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div[3]/div/form/p")
	private static WebElement ErrorMsg;	
	
	@FindBy(id="email")
	private static WebElement EmailForNewAccount;

	@FindBy(id="password")
	private WebElement customerpassword;
	 
	 @FindBy(name="days")
	 private WebElement days;
	
	 @FindBy(name="months")
	 private WebElement months;
	 
	 @FindBy(name="years")
	 private WebElement years;	 
	
	@FindBy(id = "first_name")
	private WebElement customerFirstName;

	@FindBy(id = "last_name")
	private WebElement customerLastName;

	@FindBy(id = "company")
	private WebElement companyName;

	@FindBy(id = "address1")
	private WebElement address;
	
	@FindBy(id = "address2")
	private WebElement address2;
	
	@FindBy(id = "country")
	private WebElement country;
	
	@FindBy(id = "state")
	private WebElement state;

	@FindBy(id = "city")
	private WebElement city;	

	@FindBy(id = "zipcode")
	private WebElement zipcode;

	@FindBy(id = "mobile_number")
	private WebElement mobile;
	
	@FindBy(id = "uniform-id_gender1")
	private WebElement mr;
	
	@FindBy(id = "uniform-id_gender2")
	private WebElement mrs;
	
	//select input of checkbox
	@FindBy(id = "newsletter")
	private WebElement newsletterCheckbox;	
	
	@FindBy(id = "optin")
	private WebElement offerCheckbox;	
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div/div[1]/form/button")
	private static WebElement CreateAccountBtn;	
	
	@FindBy(xpath = "//a[text()='Continue']")
	private WebElement ContinueBtn;
	
	@FindBy(css = "#form>div>div>div>div>a")
	private WebElement ContinueDeleteAccBtn;	
	
	 @FindBy(css="#form>div>div>div>h2")
	 private static WebElement DeleteAccountTitle ; 
	 
	 @FindBy(css="#form>div>div>div>h2")
	 private static WebElement AccountcreateTitle;
	 
	 
	 @FindBy(xpath="//div[@class='signup-form']/h2")
	 private static WebElement newUserSignupTitle;
	 
	 @FindBy(css="#form>div>div>div>div.login-form>h2")
	 private static WebElement SignupTitle;	 
	 
    
	public void getSignUpPageTitle(String expectedresult) {	
		boolean actualres= action.GetText(SignupTitle, expectedresult);
		Assert.assertTrue(actualres,"The title is incorrect!");	 		
	}	
	public void verifyTitle(String expectedresult){	
		 action.implicitWait(driver, 20);  
		 boolean res = action.GetText(newUserSignupTitle,expectedresult);		
		 Assert.assertTrue(res,"'New User Signup!' is not displayed!");	
	}	
	public void validateAcountCreatePage (String expectedresult) {
		 action.implicitWait(driver, 20);  
		 boolean res = action.GetText(AccountcreateTitle,expectedresult);		
		 Assert.assertTrue(res,"'Account created is not displayed!");
	}
	public HomePage ClickContinueBtn() throws IOException {				
		action.ClickOnElement(driver,ContinueBtn);		
		return new HomePage();		
	}	
	
	public SignUpPage signup(String name,String email) throws IOException {
		//enter name and  email 
		action.implicitWait(driver, 20);		
		action.type(NameNewAccount, name);		
		action.type(emailForNewAccount, email);		
		action.ClickOnElement(driver,SignupBtn);		
		action.implicitWait(driver, 20);
		
		return new SignUpPage();		
	}	
	public void ErrorIsDisplayed(String expectedresult) {
		action.implicitWait(driver, 20);  
		boolean actualres = action.GetText(ErrorMsg,expectedresult);
		Assert.assertTrue(actualres,"The error message is not displayed!");	
	}		
	//Fill adress information
	public SignUpPage createAccount(
			String name,			
			String gender,
			String pswd,			
			String Customerday, 
			String Customermonth, 
			String Customeryear,		
			String Firstname,
			String lastname,
			String company,
			String Adress,
			String Adress2,
			String Customercountry,
			String State,
			String City,
			String ZipCode,
			String Mobilenum) throws IOException {	
		
		 if(gender.equalsIgnoreCase("Mr")) {
			 action.ClickOnElement(driver,mr);
		  } else {			 
			  action.ClickOnElement(driver,mrs);
		  }	
		 action.type(NameForNewAccount, name);
		 action.type(customerpassword, pswd);		 
		 action.selectByValue(days,Customerday);				
		 action.selectByValue(months,Customermonth); 				 
		 action.selectByValue(years,Customeryear);
		 action.ClickOnElement(driver,newsletterCheckbox);		 
		 action.ClickOnElement(driver,offerCheckbox);
		 action.type(customerFirstName, Firstname);
		 action.type(customerLastName, lastname); 
		 action.type(companyName, company);		 		
		 action.type(address, Adress);
		 action.type(address2, Adress2);
		 action.selectByValue(country,Customercountry);		
		 action.type(state, State);
		 action.type(city, City);
		 action.type(zipcode, ZipCode);
		 action.type(mobile, Mobilenum);
		 action.implicitWait(driver, 70);			 
		return new SignUpPage();
	}	
	public SignUpPage ClickOnCreateAccount() throws IOException{
		action.ClickOnElement(driver,CreateAccountBtn);			
		return new SignUpPage();
	}	
	
    public void verifyAccountDeletedTitle(String expectedresult){	
		 action.implicitWait(driver, 50);  
		 boolean res = action.GetText(DeleteAccountTitle,expectedresult);		
		 Assert.assertTrue(res,"'ACCOUNT DELETED!' is not displayed!");		
	}    
	
	public HomePage ClickDeleteAccountBtn() throws IOException {
		action.ClickOnElement(driver,ContinueDeleteAccBtn);			
		return new HomePage();		
	}	

}
