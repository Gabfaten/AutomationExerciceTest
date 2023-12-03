package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;
import actiondriver.Action;

public class ShippingPage extends BaseClass{

	public ShippingPage() throws IOException {
		PageFactory.initElements(driver,this);
	}
	Action action = new Action();
	
	 @FindBy(linkText="Proceed To Checkout")
	 private static WebElement checkoutBtn;
	 
	 @FindBy(xpath="//ul[@id='address_delivery']//li[2]")
	 private static WebElement name;
	 
	 @FindBy(xpath="//ul[@id='address_delivery']//li[3]")
	 private static WebElement company;
	 
	 @FindBy(xpath="//ul[@id='address_delivery']//li[4]")
	 private static WebElement adr1;
	 
	 @FindBy(xpath="//ul[@id='address_delivery']//li[5]")
	 private static WebElement adr2;
	 
	 @FindBy(xpath="//ul[@id='address_delivery']//li[6]")
	 private static WebElement address_postcode;
	 
	 @FindBy(xpath="//ul[@id='address_delivery']//li[7]")
	 private static WebElement address_country_name;
	 
	 @FindBy(xpath="//ul[@id='address_delivery']//li[8]")
	 private static WebElement address_phone; 
	 
	 @FindBy(linkText="Place Order")
	 private static WebElement placeOrderBtn;	 
	 
	 
	 public ShippingPage ClickOnProceedToCheckout()throws IOException {
		 action.ClickOnElement(driver,checkoutBtn);
		 return new ShippingPage();
	 }	 
	 public boolean verifyDeliveryAddress(String expectedresult) {				 	
	     boolean actualres= action.GetText(name,expectedresult);
	     return actualres;	     
	 }
	 public boolean verifyCompany(String expectedresult) { 		
		 boolean actualres= action.GetText(company,expectedresult);
		 return actualres;		   
	 }
	 public boolean verifyAdress1(String expectedresult) { 			 
		 boolean actualres= action.GetText(adr1,expectedresult);
		 return actualres;		  
	 }
	 public boolean verifyAdress2(String expectedresult) { 		 
		 boolean actualres= action.GetText(adr2,expectedresult);
		 return actualres;		  
	 }	 
	 public boolean verifyAddressPostcode(String expectedresult) {
		 boolean actualres= action.GetText(address_postcode,expectedresult);
		 return actualres;	   
	 }
	 public boolean verifyCountry(String expectedresult) { 			 
		 boolean actualres= action.GetText(address_country_name,expectedresult);
		 return actualres;	    
	 }
	 public boolean verifyAddressPhone(String expectedresult) { 		 
		 boolean actualres= action.GetText(address_phone,expectedresult);
		 return actualres;	   
	 }	

	public PaymentPage ClickOnPlaceOrder() throws IOException {	 		
		action.ClickOnElement(driver,placeOrderBtn);		
		action.implicitWait(driver, 20);		
		return new PaymentPage();		
	}
	 
	 
	 
	 
}
