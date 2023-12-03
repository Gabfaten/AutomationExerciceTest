package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;
import actiondriver.Action;

public class PaymentPage extends BaseClass{

	public PaymentPage() throws IOException {
		PageFactory.initElements(driver,this);
	}
	
	Action action = new Action();
	
	@FindBy(name="name_on_card")
	private static WebElement CardName;
	
	@FindBy(name="card_number")
	private static WebElement CardNumber;
	
	@FindBy(name="cvc")
	private static WebElement cvc;
	
	@FindBy(name="expiry_month")
	private static WebElement expiry_month;
	
	@FindBy(name="expiry_year")
	private static WebElement expiry_year;
	
	@FindBy(id="submit")
	private static WebElement SubmitBtn;
	
	@FindBy(css="#success_message .alert-success")
	private static WebElement successMsg;	
	
	@FindBy(linkText="Download Invoice")
	private static WebElement DownloadBtn;
	
	@FindBy(linkText="Continue")
	private static WebElement ContinueBtn;
	
	
	public void AddPaymentInfo(String cardName,String cardNum,String CardCvc,
							String month,String Year) {
		 action.type(CardName, cardName);
		 action.type(CardNumber, cardNum);
		 action.type(cvc, CardCvc);
		 action.type(expiry_month, month);	
		 action.type(expiry_year, Year);			
	}
	//Click 'Pay and Confirm Order' button
	public PaymentPage ClikOnpayBtn() throws IOException {
		action.ClickOnElement(driver,SubmitBtn);
		return new PaymentPage();		
		
	}
	public boolean verifyPaymentDone(String expectedresult) {
     action.implicitWait(driver, 20);  	
	 boolean res = action.GetText(successMsg,expectedresult);
	 return res;		
		
	}
	 public void ClickOnDownloadInvoice() {
	     action.ClickOnElement(driver,DownloadBtn);	
	     action.implicitWait(driver, 70);  
	}
	 public void ClickOnContinueBtn() {
	     action.ClickOnElement(driver,ContinueBtn);		    
	}
	 
	
}
