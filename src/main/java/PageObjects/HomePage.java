package PageObjects;

import org.testng.Assert;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Base.BaseClass;
import actiondriver.Action;

public class HomePage extends BaseClass{
	
	Action action = new Action();
	
	public HomePage() throws IOException {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//img[@src='/static/images/home/logo.png']")
	private static WebElement logo;
	
	@FindBy(xpath="//a[text()=' Signup / Login']")
	private static WebElement SignupBtn;		

	//Delete Account link  in the menu
	 @FindBy(css="ul.navbar-nav>li:nth-child(5)>a")
	 private static WebElement DeleteAccountLink;  
	 
	 @FindBy(css="ul.navbar-nav>li:nth-child(4)>a")
	 private static WebElement LogoutLink; 
	 
	 @FindBy(css="ul.navbar-nav>li:nth-child(8)>a")
	 private static WebElement ContactUsLink;  
	 
	 @FindBy(css="ul.navbar-nav>li:nth-child(2)>a")
	 private static WebElement ProductsLink; 
	 
	 @FindBy(css=".single-widget>h2")
	 private static WebElement SubscriptionTitle; 
	 
	 @FindBy(id="susbscribe_email")
	 private static WebElement Susbscribe_email; 
	 
	 @FindBy(id="subscribe")
	 private static WebElement SusbscribeBtn; 
	 
	 @FindBy(css="#success-subscribe .alert-success")
	 private static WebElement SuccessSubscribe; 
	 
	 @FindBy(css="a[href='/product_details/2']")
	 private static WebElement viewPdtLink;	 
	 
	 @FindBy(id="scrollUp")
	 private static WebElement scrollUpBtn;	 	 
		 
	 @FindBy(tagName="h2")
	 private static WebElement Title;	
	 
	public Boolean LogoIsDisplayed() {
		 Boolean res = logo.isDisplayed();
		 return  res;
	}		
	public LoginPage SignupClick() throws IOException{		 
		action.ClickOnElement(driver,SignupBtn);	
		return new LoginPage();		
	}

	public HomePage ClickOnDeleteAccountLink() throws IOException {	
		action.explicitWait(driver, DeleteAccountLink, Duration.ofSeconds(50));	
		return new HomePage();		
	}	
	public HomePage ClickOnLogOut() throws IOException {			
		action.explicitWait(driver, LogoutLink, Duration.ofSeconds(50));	
		return new HomePage();		
	}	
	
	 public void verifyHomePageTitle() throws IOException {
        String expectedTitle = "Automation Exercise";
        String actualTitle = driver.getTitle();       
        System.out.println("actualTitle:" + actualTitle);
        Assert.assertEquals(expectedTitle, actualTitle, "The title is not correct");
    }
	public void verifyHomePageLogo() throws IOException {     
        Boolean actualresult = logo.isDisplayed();
        Assert.assertTrue(actualresult, "The logo is not displayed");
	 }
	public ContactPage ClickOnContactUsLink() throws IOException {
		action.explicitWait(driver, ContactUsLink, Duration.ofSeconds(50));	
		return new ContactPage();			
	}
	public ProductsPage ClickOnProductsLink() throws IOException {			
		action.explicitWait(driver, ProductsLink, Duration.ofSeconds(50));	
		return new ProductsPage();		
	}
	public void VerifyTextSubscription(String expectedresult) {
		action.implicitWait(driver, 20);  
		boolean actualres = action.GetText(SubscriptionTitle,expectedresult);
		Assert.assertTrue(actualres,"The Title is not displayed!");			
	}
	
	public void Subscription(String email) throws IOException, InterruptedException {	
		action.type(Susbscribe_email,email);
		ClickOnSubscribeBtn();	
	}
	public void ClickOnSubscribeBtn() throws IOException, InterruptedException{	
		action.ClickOnElement(driver,SusbscribeBtn);		
		action.implicitWait(driver, 20);		
	}
	public void VerifySuccessMsg(String expectedresult) {
		action.implicitWait(driver, 20);  
		boolean actualres = action.GetText(SuccessSubscribe,expectedresult);
		Assert.assertTrue(actualres,"Success message is not displayed!");			
	}
	public ProductsPage ClickOnViewProductLink() throws IOException {
		action.explicitWait(driver, viewPdtLink, Duration.ofSeconds(50));	
		return new ProductsPage();			
	}
	public void scrollDownToFooter() {
		  action.implicitWait(driver, 120);  
	      action.scrollDownToFooter(driver,By.id("footer"));  
	}
	// Click on arrow at bottom right side to move upward
	public void ScrollTop() throws IOException, InterruptedException {		
		action.ClickOnElement(driver,scrollUpBtn);		
		Thread.sleep(2000);
		action.scrollUpPage(driver);
		boolean isScrolledUp= action.isPageScrolledUp(driver);
		if (isScrolledUp) {
            System.out.println("Page is scrolled up successfully.");
        } else {
            System.out.println("Page is not scrolled up.");
        }			
	}	     
	public void VerifyText(String expectedresult) {
		action.implicitWait(driver, 70);  
		boolean actualres = action.GetText(Title,expectedresult);
		Assert.assertTrue(actualres,"The title is not displayed!");			
	}
	public void ScrollDown(int x,int y) {
		action.scrollDown(driver,x ,y);
		
	}

	
	
}
