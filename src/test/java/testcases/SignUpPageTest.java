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
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.SignUpPage;
import Util.ReadXLSData;
import Util.TestUtils;
import actiondriver.Action;

public class SignUpPageTest extends BaseClass{

	public SignUpPageTest() throws IOException {
		super();	
	}
	
	LoginPage loginpage;
	HomePage homepage;
	SignUpPage signUp_page;
	
	Action action = new Action();
	
	@Parameters({"Browser"})
	@BeforeMethod
	public void setUp(String Browser, Method method) throws IOException {	
		Logger = extent.startTest(method.getName());
		initialization(Browser);
		this.loginpage = new LoginPage();
		this.homepage  = new HomePage();
		this.signUp_page = new SignUpPage();
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
	
	@Test(priority = 1 ,dataProvider = "newAcountDetailsData")
	public void RegisterUser(Method method,String fname,String gender,String pswd,
			String day,String month,String year,String Firstname,
			String lastname,String company,String Adress,String Adress2,
			String country,String State,String City,
			String ZipCode,String Mobilenum	) throws IOException, InterruptedException {
		
		  System.out.println("I am inside Test Case 1: Register User \n");	
		  homepage.verifyHomePageTitle();		
		  homepage.verifyHomePageLogo();
		  loginpage = homepage.SignupClick();
		  signUp_page.verifyTitle("New User Signup!");		
		  Thread.sleep(2000);
		 signUp_page = signUp_page.signup(fname, prop.getProperty("email"));
		 Thread.sleep(2000);
		 signUp_page.getSignUpPageTitle("ENTER ACCOUNT INFORMATION");		
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
	     
	     Thread.sleep(2000);	     
	     signUp_page = signUp_page.ClickOnCreateAccount(); 	
	     //Verify 'Account created' is visible	     
	     signUp_page.validateAcountCreatePage("Account created!");
	     //click Continue button
	     homepage = signUp_page.ClickContinueBtn();		 
	     loginpage.VerifyLoginAsUsername(fname); 
	    // Click 'Delete Account' button
	     homepage = homepage.ClickOnDeleteAccountLink();
	     action.implicitWait(driver, 20); 
	     TestUtils.takeSnapShot(method.getName());
	     signUp_page.verifyAccountDeletedTitle("Account Deleted!");	    
	     homepage = signUp_page.ClickDeleteAccountBtn();		     
	     TestUtils.takeSnapShot(method.getName());
	}

	@Test(priority = 4)
	public void RegisterUserWithExistingEmail(Method method) throws IOException, InterruptedException{	
         System.out.println("I am inside  Test Case 5: Register User with existing email \n");	       
	     homepage.verifyHomePageTitle();		
		 homepage.verifyHomePageLogo(); 
         loginpage = homepage.SignupClick(); 
         signUp_page.verifyTitle("New User Signup!"); 
		 Thread.sleep(1000);		 
         signUp_page = signUp_page.signup(prop.getProperty("correct.username"),
        		 prop.getProperty("correct.email")); 
          signUp_page.ErrorIsDisplayed("Email Address already exist!");  
          TestUtils.takeSnapShot(method.getName());
      }		
   @DataProvider
	public Object[][] newAcountDetailsData() throws IOException {
		Object data[][]= ReadXLSData.getDataFromExcel("AccountCreationData");
		return data;		
	}
	
	
	
	
	
	
	
	
	

}
