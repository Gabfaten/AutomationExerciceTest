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
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.OrderPage;
import PageObjects.PaymentPage;
import PageObjects.ProductsPage;
import PageObjects.ShippingPage;
import PageObjects.SignUpPage;
import Util.ReadXLSData;
import Util.TestUtils;
import actiondriver.Action;

public class EndToEndTest extends BaseClass{
	
	public EndToEndTest() throws IOException {
		super();	
	}
    Action action = new Action();    
	HomePage homepage;
	LoginPage loginpage;
    ProductsPage productspage;
	OrderPage orderpage;
	ShippingPage  shippingpage;
	SignUpPage signUp_page;
	PaymentPage paymentpage;
		
	@Parameters({"Browser"})
	@BeforeMethod
	public void setUp(String Browser,Method method) throws InterruptedException, IOException {
		Logger = extent.startTest(method.getName());
		initialization(Browser); 		
		this.homepage  = new HomePage();
		this.loginpage  = new LoginPage();
		this.signUp_page = new SignUpPage();
		this.productspage  = new ProductsPage();	
		this.orderpage  = new OrderPage();	
		this.shippingpage  = new ShippingPage();	
		this.paymentpage  = new PaymentPage();	
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
	
	@Test(priority=24,dataProvider = "newAcountDetailsData")
	public void endToEndTest(Method method,String fname,String gender,String pswd,
			String day,String month,String year,String Firstname,
			String lastname,String company,String Adress,String Adress2,
			String country,String State,String City,
			String ZipCode,String Mobilenum) throws Throwable {
		
		 System.out.print("I am insideTest Case 24: Download Invoice after purchase order \n");
		
		 homepage.verifyHomePageTitle();		
		 homepage.verifyHomePageLogo();
		 action.implicitWait(driver, 50); 
		 productspage = homepage.ClickOnProductsLink();		 
		//Hover  first product and click 'Add to cart'
		 productspage = productspage.HoverProduct();	
		 action.implicitWait(driver, 120); 
		 //Click 'Continue Shopping' button
		 productspage = productspage.ClickOnContinueShopping();	
		 Thread.sleep(4000);
		//Hover over second product and click 'Add to cart'
		 productspage = productspage.HoverProductTwo();
		 action.implicitWait(driver, 120); 
		 //Click 'View Cart' button
		 orderpage = productspage.ClickOnViewCart();		
		// Verify that cart page is displayed
	     String url = productspage.getPageUrl();
		if(!url.contains("view_cart")) {
			 System.out.print("Redirection to Cart page failed-----\n");
		}
		 orderpage.Checkbreadcrumbs("Shopping Cart");		
		 shippingpage = shippingpage.ClickOnProceedToCheckout();		 
		 loginpage=orderpage.ClickOnRegisterBtn();				 
		 loginpage = homepage.SignupClick();
		 signUp_page = signUp_page.signup(fname, prop.getProperty("email"));
		 action.implicitWait(driver, 120);    
	     signUp_page = signUp_page.createAccount(
	    		 fname,
	    		 gender, 
	    		 pswd,
	    		 day,
	    		 month,
	    		 year,
	    		 Firstname,
	    		 lastname,
	    		 company,
	    		 Adress,
	 			 Adress2,
	 			 country,
				 State,
				 City,
				 ZipCode,
				 Mobilenum);
	     
		     action.implicitWait(driver, 120);
		     signUp_page = signUp_page.ClickOnCreateAccount(); 		      
		     signUp_page.validateAcountCreatePage("Account created!");	   
		     homepage = signUp_page.ClickContinueBtn();		
		     loginpage.VerifyLoginAsUsername(fname); 
		     action.implicitWait(driver, 20);   
		     //click on cart button in the menu
		     orderpage=orderpage.ClickOnCartLink();
		     //click on proceed to checkout 
		     shippingpage=shippingpage.ClickOnProceedToCheckout();	
		     
	        // Verify that the delivery address is same address filled			
			String expectedresult =gender+"."+" "+Firstname+ " " +lastname;		
			boolean actualName = shippingpage.verifyDeliveryAddress(expectedresult);
			Assert.assertTrue(actualName,"Lastname || First name are not correct!");
			
			boolean actualcompany =shippingpage.verifyCompany(company);
			Assert.assertTrue(actualcompany,"Company name is not correct!");	
			
			boolean actualAdr =shippingpage.verifyAdress1(Adress);
			Assert.assertTrue(actualAdr,"Adress1 is not correct!");
			
			boolean actualAdr2 =shippingpage.verifyAdress2(Adress2);
			Assert.assertTrue(actualAdr2,"Adress2 is not correct!");
			
			 String expected_Address_Postcode = City + " "+ State + " " + ZipCode;			 
			boolean actualPostCode = shippingpage.verifyAddressPostcode(expected_Address_Postcode);
			Assert.assertTrue(actualPostCode,"AddressPostcode is not correct!");
			
			boolean actualCountry = shippingpage.verifyCountry(country);
			Assert.assertTrue(actualCountry,"Country is not correct!");
			boolean actualAddressPhone = shippingpage.verifyAddressPhone(Mobilenum);		
			Assert.assertTrue(actualAddressPhone,"Phone number is not correct!");	
			 //Verify Review Your Order	
			 orderpage.verifyProductOneInCart();
			 orderpage.verifyTotpriceForProductOne();
			 orderpage.verifyProductTwoInCart();
			 orderpage.verifyTotpriceForProductTwo();			
			 //click 'Place Order'
			 paymentpage=shippingpage.ClickOnPlaceOrder();	
			 
			 paymentpage.AddPaymentInfo(prop.getProperty("CardName"),
					 					prop.getProperty("cardNum"),
					 					prop.getProperty("cvc"),
					 					prop.getProperty("expiry_month"),
					                    prop.getProperty("expiry_year"));
			 
			 paymentpage=paymentpage.ClikOnpayBtn();			
			 //boolean result=paymentpage.verifyPaymentDone("Your order has been placed successfully!");
			// Assert.assertTrue(result,"The success message is not displayed!");			 
			  paymentpage.ClickOnDownloadInvoice();			
			 // verify invoice is downloaded successfully	
			 TestUtils.isFileDownload("invoice","txt",5000);
			 TestUtils.takeSnapShot(method.getName());
			// Assert.assertTrue(TestUtils.isFileDownload("invoice","txt",5000));				 
			 paymentpage.ClickOnContinueBtn();			
			 homepage = homepage.ClickOnDeleteAccountLink();
			 action.implicitWait(driver, 20);
	         loginpage.verifyAccountDeletedTitle("ACCOUNT DELETED!");
	         homepage = signUp_page.ClickDeleteAccountBtn();	       
	}		

	@DataProvider
	public Object[][] newAcountDetailsData() throws IOException {
		Object data[][]= ReadXLSData.getDataFromExcel("AccountCreationData");
		return data;		
	}

}
