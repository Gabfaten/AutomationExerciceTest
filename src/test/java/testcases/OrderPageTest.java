package testcases;

import java.io.IOException;
import java.lang.reflect.Method;

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

public class OrderPageTest extends BaseClass{

	public OrderPageTest() throws IOException {
		super();		
	}
	
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
	
	@Test(priority=11,dataProvider="SubscriptionData")
	public void VerifySubscription(Method method,String email) throws IOException, InterruptedException {		
		System.out.println("I am insideTest Case 11: Verify Subscription in order page\n");
		orderpage =orderpage.ClickOnCartLink();
		homepage.Subscription(email);
		TestUtils.takeSnapShot(method.getName());
	}
	
	@Test(priority=13,dataProvider="ProductData")
	public void VerifyProductQte(Method method,String quantity) throws Throwable {	
		System.out.print("I am inside Test Case 13: Verify Product quantity in Cart \n");
		homepage.verifyHomePageTitle();		
		homepage.verifyHomePageLogo();	
		productspage = homepage.ClickOnViewProductLink();	
		String url = productspage.getPageUrl();
		if(!url.contains("product_details/")) {
			 System.out.print("Redirection to product detail  page failed!\n");
		}		
		productspage.enterQuantity(quantity);
		productspage.ClickOnAddCartBtn();
		// Click 'View Cart' button
		productspage.ClickOnViewCart();		
		orderpage.VerifyQuantity(quantity);
		TestUtils.takeSnapShot(method.getName());
	}	
	@Test(priority=17)
	public void RemoveProduct(Method method) throws IOException, InterruptedException {
		System.out.print("I am inside Test Case 17: Remove Products From Cart\n");
		homepage.verifyHomePageTitle();		
		homepage.verifyHomePageLogo();
		Thread.sleep(3000);
		//scroll down
		homepage.ScrollDown(825,655);
		//Hover second product and click 'Add to cart'
		productspage.HoverProductTwo();		
		orderpage = productspage.ClickOnViewCart();
		// Verify that cart page is displayed
	     String url = productspage.getPageUrl();
		if(!url.contains("view_cart")) {
			 System.out.print("Redirection to Cart page failed!\n");
		}
		orderpage.Checkbreadcrumbs("Shopping Cart");	
		orderpage.ClickOnRemoveProduct();	
		Thread.sleep(1000);
		TestUtils.takeSnapShot(method.getName());
	}
	
	
  @DataProvider
	public Object[][] SubscriptionData() throws IOException {
		Object data[][]= ReadXLSData.getDataFromExcel("Subscription");
		return data;		
	}
  @DataProvider
	public Object[][] ProductData() throws IOException {
		Object data[][]= ReadXLSData.getDataFromExcel("ProductDetails");
		return data;		
	}

}
