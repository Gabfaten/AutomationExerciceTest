package testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseClass;
import PageObjects.HomePage;
import Util.ReadXLSData;
import Util.TestUtils;
import com.relevantcodes.extentreports.LogStatus;


public class HomePageTest extends BaseClass{
	
	public HomePageTest() throws IOException {	
		super();		
	}
	HomePage homepage;
	
	@Parameters({"Browser"})
	@BeforeMethod
	public void setUp(String Browser,Method method) throws InterruptedException, IOException {	
		Logger = extent.startTest(method.getName());			
		initialization(Browser); 		
		this.homepage  = new HomePage();	  
	}
	@AfterMethod

	public void tearDown(Method method,ITestResult result)throws IOException{		
		
		  if(result.getStatus()== ITestResult.SUCCESS) {	    	
		    	Logger.log(LogStatus.PASS, "Test pass");
		    	Logger.log(LogStatus.PASS, "<a href='" + result.getName()+".png"+"'><span class='label info'>Download Snapshot</span></a>" );	
		    	
		    }else if (result.getStatus()== ITestResult.FAILURE) {	    	
		    	Logger.log(LogStatus.FAIL, result.getThrowable());
		    	Logger.log(LogStatus.FAIL, "<a href='" + result.getName()+".png"+"'><span class='label info'>Download Snapshot</span></a>" );	
		   
		    }else{
		    	Logger.log(LogStatus.SKIP, "Test Skipped");
		    }
	           
	    driver.quit();	  
	}	

	@Test(priority=10,dataProvider="SubscriptionData")
	public void Subscription(String email,Method method) throws IOException, InterruptedException {
		System.out.println("I am inside Test Case 10: Subscription in home page \n");
		 homepage.verifyHomePageTitle();		
		 homepage.verifyHomePageLogo();	
		 homepage.VerifyTextSubscription("Subscription");
		 //scroll down to footer
		 homepage.scrollDownToFooter();
		 homepage.Subscription(email);
		 homepage.VerifySuccessMsg("You have been successfully subscribed!");			 
		 TestUtils.takeSnapShot(method.getName());
	}	

	@Test(priority=25)
	public void VerifyScrollUp(Method method) throws IOException, InterruptedException {
		 System.out.println("I am inside Test Case 25: verify Scroll Up in Home page\n");
		 homepage.verifyHomePageTitle();		
		 homepage.verifyHomePageLogo();	
		 homepage.scrollDownToFooter();
		 Thread.sleep(1000);		
		 homepage.VerifyTextSubscription("Subscription");
		 homepage.ScrollTop();	
		 homepage.VerifyText("Full-Fledged practice website for Automation Engineers");			 
		 TestUtils.takeSnapShot(method.getName());
	}
		
   @DataProvider
	public Object[][] SubscriptionData() throws IOException {
		Object data[][]= ReadXLSData.getDataFromExcel("Subscription");
		return data;		
	}
	
}
