package testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Base.BaseClass;
import PageObjects.OrderPage;
import PageObjects.HomePage;
import PageObjects.ProductsPage;
import Util.ReadXLSData;
import Util.TestUtils;
import actiondriver.Action;


public class ProductsPageTest extends BaseClass{
	
	public ProductsPageTest() throws IOException {
		super();		
	}
	Action action = new Action();
	
	HomePage homepage;
	ProductsPage productspage;
	OrderPage orderpage;
	
	@Parameters({"Browser"})
	@BeforeMethod
	public void setUp(String Browser,Method method) throws InterruptedException, IOException {		
		Logger = extent.startTest(method.getName());
		initialization(Browser); 
		this.homepage  = new HomePage();
		this.productspage  = new ProductsPage();
		this.orderpage  = new OrderPage();
	    
	}
	@AfterMethod
	public void tearDown(Method method,ITestResult result)throws IOException{	   
	    if(result.getStatus()== ITestResult.SUCCESS) {	    	
	    	Logger.log(LogStatus.PASS, "Test pass");
	    	Logger.log(LogStatus.PASS, "<a href='" + result.getName()+".png"+"'><span class='label info'>Download Snapshot</span></a>" );	
	    	
	    }else if (result.getStatus()== ITestResult.FAILURE) {	    	
	    	Logger.log(LogStatus.FAIL,result.getThrowable());
	    	Logger.log(LogStatus.FAIL, "<a href='" + result.getName()+".png"+"'><span class='label info'>Download Snapshot</span></a>" );	
	    }else{
	    	Logger.log(LogStatus.SKIP, "Test Skipped");
	    }
	           
	    driver.quit();	  
	}		
	
	@Test(priority = 8)
	public void VerifyAllProducts(Method method) throws IOException, InterruptedException {
		System.out.print("I am inside Test Case 8: Verify All Products and product detail page \n");
		homepage.verifyHomePageTitle();		
		homepage.verifyHomePageLogo();
		productspage = homepage.ClickOnProductsLink();
		// Verify if the user is on the "ALL PRODUCTS" page
		productspage.verifyAllProductsTitle();		
		boolean isProductListPresent = productspage.ListElementPresent();	
		 if (isProductListPresent) {
            System.out.println("Product List is visible.");
         } else {
            System.out.println("Product List is not visible!");
         } 
		 //Click on 'View Product' of first product 		
		String url = productspage.ClickOnViewPdtLink();
	    if(!url.contains("product_details/")) {
			 System.out.print("Redirection to product detail  page failed-----\n");
		}
	    // Verify that detail is visible	  
	    Boolean  isPdtNameVisible= productspage.VerifyProductName();
	    if (isPdtNameVisible) {
            System.out.println("The product name is displayed.");
        } else {
            System.out.println("The products name is not displayed!");
        }
	    Boolean isCategoryVisible = productspage.VerifyCategoryPdt();
	    if (isCategoryVisible) {
            System.out.println("The Category of product is displayed.");
        } else {
            System.out.println("The Category of product is not displayed!");
        }
	    boolean price =productspage.VerifyPriceofPdt();
	    boolean condition =productspage.VerifyPdtConditions();
	    boolean brand =productspage.VerifyPdtBrand();
	    boolean availability = productspage.VerifyPdtAvaibility();
	    if(availability&price&condition&brand) {	    	
	       System.out.println("The price, condition & brand of product are displayed.");
	    }else {	    	
	    	 System.out.println("Not all dtails of product is displayed!");
	    }	
	    TestUtils.takeSnapShot(method.getName());
	}
	
	@Test(priority = 12)
	public void AddTocart(Method method) throws IOException, InterruptedException {
		System.out.print("I am inside Test Case 12: Add Products in Cart \n");
		homepage.verifyHomePageTitle();		
		homepage.verifyHomePageLogo();
		productspage = homepage.ClickOnProductsLink();
		//Hover  first product and click 'Add to cart'
		productspage = productspage.HoverProduct();	
		Thread.sleep(2000);
		//Click 'Continue Shopping' button
		productspage = productspage.ClickOnContinueShopping();	
		Thread.sleep(4000);
		//Hover over second product and click 'Add to cart'
		productspage = productspage.HoverProductTwo();
		Thread.sleep(3000);
		//Click 'View Cart' button
		orderpage = productspage.ClickOnViewCart();
		Thread.sleep(4000);	
		//Verify both products are added to Cart	
		orderpage.verifyProductOneInCart();			
		orderpage.verifyProductTwoInCart();
		orderpage.verifyTotpriceForProductOne();
		orderpage.verifyTotpriceForProductTwo();
		TestUtils.takeSnapShot(method.getName());
	}	
	@Test(priority = 18)
	public void VerifyCategoryProducts(Method method) throws IOException {
	System.out.print("I am inside Test Case 18: View Category Products \n");		
	  //scroll down
	   homepage.ScrollDown(825,300);
	    //Verify that categories are visible on left side bar
		boolean result = productspage.categoryProducts();	
		 if (result) {
             System.out.println("Category is visible" );
         } else {
             System.out.println("Category is not visible" );
         }
		//Click on 'Women' category
		 boolean actualtitle= productspage.VerifyCategory("WOMEN - DRESS PRODUCTS");
		 Assert.assertTrue(actualtitle,"The Title is not displayed!");
		 String url =productspage.VerifySubCategory();
		 if(!url.contains("category_products/")) {
			 System.out.print("Redirection to category product page failed!\n");
		}	
		 TestUtils.takeSnapShot(method.getName());
	}	
	@Test(priority = 19)
	public void VerifyBrandProducts(Method method) throws IOException, InterruptedException {
		System.out.print("I am inside  Test Case 19: View & Cart Brand Products	 \n");
		productspage =homepage.ClickOnProductsLink();
		homepage.ScrollDown(920,300);   
		productspage.Brands();
		TestUtils.takeSnapShot(method.getName());
	}
	
	@Test(priority = 21,dataProvider="ReviewData")
	public void AddReviewOnProduct(Method method,String name,String email,String review) throws IOException, InterruptedException {
		System.out.print("I am inside Test Case 21: Add review on product \n");
		productspage = homepage.ClickOnProductsLink();
		homepage.ScrollDown(825,300);   
		// Verify if the user is on the "ALL PRODUCTS" page
		productspage.verifyAllProductsTitle();				
		boolean isProductListPresent = productspage.ListElementPresent();	
		 if (isProductListPresent) {
            System.out.println("Product List is visible.");
         } else {
            System.out.println("Product List is not visible!");
         }
		 //Click on 'View Product' of first product 		
		 productspage.ClickOnViewPdtLink();
		 productspage.verifyReviewTitle("Write Your Review");		 
		 productspage.AddReview(name,email,review);		
		 productspage.verifySuccessMsg("Thank you for your review.");	
		 TestUtils.takeSnapShot(method.getName());
	
	}
  @DataProvider
	public Object[][] ReviewData() throws IOException {
		Object data[][]= ReadXLSData.getDataFromExcel("ReviewDetails");
		return data;		
	}

}
