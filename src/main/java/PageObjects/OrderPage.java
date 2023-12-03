package PageObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Base.BaseClass;
import actiondriver.Action;

public class OrderPage extends BaseClass{
    
	public OrderPage() throws IOException {
		PageFactory.initElements(driver,this);
	}	
	 Action action = new Action();
	 
	 @FindBy(css="ul.navbar-nav>li:nth-child(3)>a")
	 private static WebElement cartLink; 
	 
	 @FindBy(id="product-1")
	 private static WebElement product1; 
	 
	 @FindBy(id="product-2")
	 private static WebElement product2; 
	 
	 @FindBy(xpath="//tr[@id='product-1']//h4")
	 private static WebElement productName1; 
	 
	 @FindBy(xpath="//tr[@id='product-2']//h4")
	 private static WebElement productName2; 
	 
	 
	 @FindBy(xpath="//*[@id=\"product-1\"]/td[3]")
	 private static WebElement UP; 
	 
	 @FindBy(xpath="//*[@id=\"product-1\"]/td[4]/button")
	 private static WebElement Qt;   	 	
	 
	 @FindBy(xpath="//*[@id=\"product-1\"]/td[5]/p")
	 private static WebElement TotPrice;  
	 
	 
	 @FindBy(xpath="//*[@id=\"product-2\"]/td[3]")
	 private static WebElement UnitPrice; 
	 
	 @FindBy(xpath="//*[@id=\"product-2\"]/td[4]/button")
	 private static WebElement Qte;   	 	
	 
	 @FindBy(xpath="//*[@id=\"product-2\"]/td[5]/p")
	 private static WebElement TotalPrice;	 
	 
	 @FindBy(css="ol.breadcrumb li.active")
	 private static WebElement breadcrumbsText;	 	
	 
	@FindBy(xpath="//*[@id=\"product-2\"]/td[6]/a")
	 private static WebElement RemoveBtn;
	
	@FindBy(linkText="Register / Login")
	 private static WebElement RegisterBtn;	
	 
	 
	public OrderPage ClickOnCartLink() throws IOException {	   	
		action.explicitWait(driver, cartLink, Duration.ofSeconds(70));	
		return new OrderPage();		
	}
	
	 public boolean ListElementPresent() {
		 boolean res= action.areElementsVisible(driver,
				 By.className("product-image-wrapper"));
		 return res;
	 }	
		
	public int getUnitPrice(WebElement UnitPrice) {	       
        String priceText = UnitPrice.getText();
        String price = priceText.replaceAll("\\D+", "");
        int Unitprice = Integer.parseInt(price); 
        return Unitprice;
	}	
	public int getTotalPrice(WebElement TotalPrice) {		
        String totalpriceText = TotalPrice.getText();
        String totprice = totalpriceText.replaceAll("\\D+", "");
        int totalprice = Integer.parseInt(totprice);      
        return totalprice;
	}	
	public void VerifyTotalprice(WebElement price,WebElement Quantity,
			WebElement TotalPrice) throws IOException {		
		int unitprice = getUnitPrice(price);		
		int qte = Integer.parseInt(Quantity.getText());
		int Totalprice = getTotalPrice(TotalPrice);
		int totalExpectedPrice =(unitprice*qte);
		System.out.println("Expected TotalPrice: "+totalExpectedPrice);
		Assert.assertEquals(Totalprice, totalExpectedPrice,"The Total price is not correct!");
	}	
	 public void verifyProductOneInCart() {		 
		boolean visible = action.isElementVisible(driver, By.id("product-1"));
		Assert.assertTrue(visible, productName1 + " is not added to the cart.");
	 }
	 public void verifyProductTwoInCart() {			 
		boolean isdisplayed =action.isElementVisible(driver, By.id("product-2"));
		Assert.assertTrue(isdisplayed, productName2 + " is not added to the cart.");
	} 
	 //verify total price for product1
	 public void verifyTotpriceForProductOne() throws IOException {		
		 VerifyTotalprice(UP,Qt,TotPrice);
	 }	
	 //verify total price for product2
	 public void verifyTotpriceForProductTwo() throws IOException {		
		 VerifyTotalprice(UnitPrice,Qte,TotalPrice);
	 }		 
	 public void VerifyQuantity(String qte) throws Throwable {	
		 Boolean res = action.GetText(Qte,qte);	  
         Assert.assertTrue(res,"The quantity is not exact!");	
	} 
    public void Checkbreadcrumbs(String expectedresult) {
    	 action.implicitWait(driver, 20);  
		 boolean res = action.GetText(breadcrumbsText,expectedresult);		
		 Assert.assertTrue(res," Cart page is not displayed!");	    	
    }
   
    public void ClickOnRemoveProduct() throws IOException, InterruptedException {
    	action.explicitWait(driver,RemoveBtn , Duration.ofSeconds(50));	
    	Thread.sleep(2000);
    	try {
            // Try to find the removed product element         
    		 action.isElementVisible(driver,By.id("product-2")); 
          } catch (org.openqa.selenium.NoSuchElementException e) {
            // If the element is not found, it means the product is removed
            System.out.println("Product is successfully removed from the cart");
        }    	
    	
    } 
    public LoginPage ClickOnRegisterBtn() throws IOException {	   	
		action.explicitWait(driver, RegisterBtn, Duration.ofSeconds(50));	
		return new LoginPage();		
	}
  
}
