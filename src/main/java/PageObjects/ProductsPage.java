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

public class ProductsPage extends BaseClass{
 
	public ProductsPage() throws IOException {
		PageFactory.initElements(driver,this);
	}
	Action action = new Action();
	
	 @FindBy(xpath="/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a")
	 private static WebElement ViewPdtLink; 
	 
	 @FindBy(css="div.container>div>div.col-sm-9.padding-right>div>div:nth-child(3)>.product-image-wrapper")
	 private static WebElement FirstPdt; 	
	 
	 @FindBy(css="div.container>div>div.col-sm-9.padding-right>div>div:nth-child(4)>.product-image-wrapper")
	 private static WebElement SecondPdt; 	 
	 
	 //for the first product
	 @FindBy(xpath="/html/body/section[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/div[2]/div/a")
	 private static WebElement addcartLink; 
	 
	 //for the second product	 
	 @FindBy(xpath="/html/body/section[2]/div[1]/div/div[2]/div/div[3]/div/div[1]/div[2]/div/a")
	 private static WebElement addcart;  
	 
	 @FindBy(css="#cartModal .modal-confirm .modal-content .modal-footer .btn-success")
	 private static WebElement ContinueShoppingBtn; 
		 
	 @FindBy(linkText="View Cart")
	 private static WebElement ViewCartLink; 
	 
	 @FindBy(id="quantity")
	 private static WebElement Quantity; 
	 
	 @FindBy(css=".product-information>span button.cart")
	 private static WebElement addCartBtn; 
	 
	 @FindBy(xpath="//a[normalize-space()='Women']")
	 private static WebElement CategoryLink;
	 
	 @FindBy(xpath="//a[normalize-space()='Dress']")
	 private static WebElement SubCategoryLink;
	 
	 @FindBy(xpath="//a[normalize-space()='Men']")
	 private static WebElement SubCategory;
	 
	 @FindBy(xpath="//div[@id='Men']/div/ul/li[1]/a")
	 private static WebElement SubCategoryLink2;
	 
	 @FindBy(xpath="//div[@class='brands-name']/ul/li[1]/a")
	 private static WebElement brandLink;
	 
	 @FindBy(xpath="//div[@class='brands-name']/ul/li[2]/a")
	 private static WebElement brandLink2;
		 
	 @FindBy(css=".features_items h2")
	 private static WebElement CategoryTitle;	 
	 
	 @FindBy(xpath="//a[normalize-space()='Write Your Review']")
	 private static WebElement ReviewText;	 

	 @FindBy(id="name")
	 private static WebElement boxname;

	 @FindBy(id="email")
	 private static WebElement boxemail;

	 @FindBy(id="review")
	 private static WebElement boxreview;
	 
	 @FindBy(id="button-review")
	 private static WebElement ReviewBtn;
	 
	 @FindBy(xpath="//div[@id='review-section']/div/div/span")
	 private static WebElement SuccessMsg;
	 
	 public void verifyAllProductsTitle() throws IOException {
		action.implicitWait(driver, 20);  		 
        String expectedTitle = "Automation Exercise - All Products";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle,
        		"Navigation to ALL PRODUCTS page failed");	 
	 }
	 public boolean ListElementPresent() {
		 boolean res= action.areElementsVisible(driver,By.className("product-image-wrapper"));
		 return res;
	 } 	 
	 public String getPageUrl(){
        String Url = action.getCurrentURL(driver);
        return Url;
     }		 
	 public String ClickOnViewPdtLink() throws IOException {			
		action.explicitWait(driver, ViewPdtLink, Duration.ofSeconds(50));	
		String url = getPageUrl();		
		return url;				
	}	 
	 public boolean  VerifyProductName(){			
		action.implicitWait(driver, 50);  
        Boolean PdtName= action.isElementVisible(driver,
        		By.cssSelector(".product-information>h2")); 
        return PdtName;
	 }
	 public boolean  VerifyCategoryPdt(){		
	    Boolean res= action.isElementVisible(driver,
	    		By.cssSelector("div.product-details>div.col-sm-7>div>p:nth-child(3)")); 	        
	    return res;
	}	
	 public boolean  VerifyPriceofPdt(){		
	    Boolean res= action.isElementVisible(driver,
	    		By.cssSelector(".product-information>span span")); 	        
	    return res;
	}
	 public boolean  VerifyPdtAvaibility(){		
	    Boolean available= action.isElementVisible(driver,
	    		By.cssSelector(" div.product-details>div.col-sm-7>div>p:nth-child(6)")); 	        
	    return available;
	} 

	 public boolean  VerifyPdtBrand(){		
	    Boolean res= action.isElementVisible(driver,
	    		By.cssSelector("div.product-details>div.col-sm-7>div>p:nth-child(8)")); 	        
	    return res;
	} 
	 public boolean VerifyPdtConditions(){		
	    Boolean res= action.isElementVisible(driver,
	    		By.cssSelector("div.product-details>div.col-sm-7>div>p:nth-child(7)")); 	        
	    return res;
	}	 
	 public ProductsPage HoverProduct() throws IOException, InterruptedException {		 
		 action.MouseHoverElement(driver,FirstPdt);	
		 Thread.sleep(2000);		
		 action.ClickOnElement(driver, addcartLink);		 
		 return new ProductsPage();		 
	 }
	 
	 public ProductsPage HoverProductTwo() throws IOException, InterruptedException {	 
		 try {
			   action.MouseHoverElement(driver, SecondPdt);
			   action.ClickOnElement(driver, addcart);				 
			} catch (Exception e) {
			    e.printStackTrace();
			}	
		 
		 return new ProductsPage();			 
	 }	 
	 public ProductsPage ClickOnContinueShopping() throws IOException {			
		 action.ClickOnElement(driver,ContinueShoppingBtn);
		 return new ProductsPage();	
	 }
	
	 public OrderPage ClickOnViewCart()throws IOException {
		 action.ClickOnElement(driver,ViewCartLink);
		 return new OrderPage();
	 }
	 public void enterQuantity(String qte) throws Throwable {	
		 action.type(Quantity, qte);
	}
	 public ProductsPage ClickOnAddCartBtn() throws IOException {		 
		 action.ClickOnElement(driver,addCartBtn);
		 return new ProductsPage();
	 }	 
	 
	 public boolean categoryProducts() {
		 boolean res= action.areElementsVisible(driver,By.className("category-products"));
		 return res;
	 } 
	 public boolean VerifyCategory(String expectedresult) {		 
		 action.ClickOnElement(driver, CategoryLink);
		 action.ClickOnElement(driver, SubCategoryLink);	
		 boolean res = action.GetText(CategoryTitle,expectedresult);
		 return res;		 
	 }
	 public String VerifySubCategory() {
	  	action.explicitWait(driver, SubCategory, Duration.ofSeconds(10));
		action.explicitWait(driver, SubCategoryLink2, Duration.ofSeconds(10));
		String url = getPageUrl();		
		return url;		      
	 }
	 
	 public void verifyBrandProducts() {		
		 boolean isvisible= action.areElementsVisible(driver,By.className("product-image-wrapper"));		
		 if (isvisible) {
             System.out.println("brand products are displayed");
         } else {
             System.out.println("brand products are not displayed!");
         }			 
	 }	
	
	 public void verifyNavigateTobrand(WebElement brandLink,String brandName) {
		action.explicitWait(driver, brandLink, Duration.ofSeconds(10)); 		
		String url = getPageUrl();
		if(!url.contains("brand_products/" + brandName)) {
			 System.out.print("Redirection to brand products page failed-----\n");
		} 		
	 }	 
	 public boolean Brands() throws InterruptedException {
		 boolean res= action.areElementsVisible(driver,
				 By.xpath("//div[@class='brands_products']"));		
		 if (res){			 
			 verifyNavigateTobrand(brandLink,"Polo");			
			 verifyBrandProducts();	
			 verifyNavigateTobrand(brandLink2,"H&M");			
			 verifyBrandProducts();					
			   
		 }else {			 
			 System.out.println("Brands not found!");
			 res =false;
		 }		 
		return res;
		 
	 }	 	
     public void verifyReviewTitle(String expectedresult){	
		 action.implicitWait(driver, 50);  
		 boolean res = action.GetText(ReviewText,expectedresult);		
		 Assert.assertTrue(res,"Review Title is not displayed!");		
	 } 	    
	 public void AddReview(String name,String email,String review) {		 
		 action.type(boxname, name);
		 action.type(boxemail, email);
		 action.type(boxreview, review);	
		 action.explicitWait(driver,ReviewBtn,Duration.ofSeconds(10));			 
	 }
     public void verifySuccessMsg(String expectedresult){	
		 action.implicitWait(driver, 20);  
		 boolean res = action.GetText(SuccessMsg,expectedresult);		
		 Assert.assertTrue(res,"'Thank you for your review !' is not displayed!");		
	}

}
