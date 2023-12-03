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
import PageObjects.ContactPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.SignUpPage;
import Util.ReadXLSData;
import Util.TestUtils;

public class ContactPageTest  extends BaseClass{
	

	public ContactPageTest() throws IOException {
		super();		
	}
	LoginPage loginpage;
	HomePage homepage;
	SignUpPage signUp_page;
	ContactPage contactpage;

	@Parameters({"Browser"})
	@BeforeMethod
	public void setUp(String Browser, Method method) throws IOException {			
		Logger = extent.startTest(method.getName());
		initialization(Browser);
		
		this.homepage  = new HomePage();		
		this.contactpage = new ContactPage();
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
	
	@Test(priority = 6, dataProvider="ContactData")	
	public void ContactUs(Method method,String name,String email,String subject,String msg) throws IOException, InterruptedException {
	 System.out.println("I am inside Test Case 6: Contact Us Form \n");	       
     homepage.verifyHomePageTitle();		 
	 homepage.verifyHomePageLogo();
     contactpage = homepage.ClickOnContactUsLink();	
     contactpage.verifyTitle("Get In Touch");
     contactpage.SendContact(name,email,subject,msg);
	 contactpage.ClickOnSubmitBtn();
	 contactpage.switchToFrame();	
	 contactpage.verifySucessMessage("Success! Your details have been submitted successfully.");
	 TestUtils.takeSnapShot(method.getName());
	 contactpage.ClickOnHomebtn();	
	 contactpage.getHomePageUrl(prop.getProperty("URL"));	 	
	}
	
   @DataProvider
	public Object[][] ContactData() throws IOException {
		Object data[][]= ReadXLSData.getDataFromExcel("ContactForm");
		return data;		
	}
	

}
