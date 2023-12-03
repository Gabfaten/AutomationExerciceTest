package testcases;

import java.io.IOException;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import Base.BaseClass;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import Util.TestUtils;
import actiondriver.Action;

public class LoginPageTest extends BaseClass{		

	public LoginPageTest() throws IOException {	
		super();	
	}	
	
	LoginPage loginpage;
	HomePage homepage;	
	
	Action action = new Action();	
	
	@Parameters({"Browser"})
	@BeforeMethod
	public void setUp(String Browser, Method method) throws IOException {	 
	 Logger = extent.startTest(method.getName());
	 initialization(Browser);
	 this.loginpage = new LoginPage();
	 this.homepage  = new HomePage();
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
	
	
	//should be executed after testLoginWithIncorrectCredentials 
	//RegisterUserWithExistingEmail, Logout because it delete the account in the final
	@Test(priority = 5)
	public void testLoginWithCorrectCredentials(Method method) throws IOException, InterruptedException{	
	 
	System.out.println("I am inside Test Case 2: Login User with correct email and password\n");		
	 
	homepage.verifyHomePageTitle();		
	 homepage.verifyHomePageLogo();	 
	 loginpage = homepage.SignupClick();	
     loginpage.verifyTitle("Login to your account");    
	 homepage = loginpage.performLogin(prop.getProperty("correct.email"),
			                           prop.getProperty("correct.password"));	
	 
	 loginpage.VerifyLoginAsUsername(prop.getProperty("correct.username"));
	 //Click 'Delete Account' button
     homepage = homepage.ClickOnDeleteAccountLink();
     action.implicitWait(driver, 20); 
     loginpage.verifyAccountDeletedTitle("ACCOUNT DELETED!");    
	 
	 TestUtils.takeSnapShot(method.getName());
	}	
 
	@Test(priority = 2)
	public void testLoginWithIncorrectCredentials(Method method) throws IOException, InterruptedException{
		
	 System.out.println("I am inside Test Case 3: Login User with incorrect email and password \n");	
	 
	 homepage.verifyHomePageTitle();		
	 homepage.verifyHomePageLogo();	
	 loginpage = homepage.SignupClick(); 
	 loginpage.verifyTitle("Login to your account");	 
	 homepage = loginpage.performLogin(prop.getProperty("incorrect.email"),
             prop.getProperty("incorrect.password"));	 
	
     loginpage.ErrorIsDisplayed("Your email or password is incorrect!");       
     Thread.sleep(1000);	
     TestUtils.takeSnapShot(method.getName());
	}
	
	@Test(priority = 3)
	public void Logout(Method method) throws IOException{
		System.out.println("I am inside Test Case 4: Logout User \n");	
	 homepage.verifyHomePageTitle();		
	 homepage.verifyHomePageLogo();	    
	 loginpage = homepage.SignupClick();	 
	 loginpage.verifyTitle("Login to your account");		 
	 homepage = loginpage.performLogin(prop.getProperty("correct.email"),
             prop.getProperty("correct.password"));	
	 
	 loginpage.VerifyLoginAsUsername(prop.getProperty("correct.username"));
     //Click 'Logout' button
     homepage = homepage.ClickOnLogOut();	 
	 //Verify that user is navigated to login page
     String url= loginpage.getLoginPageUrl();
     if(!url.contains("login")) {
		 System.out.print("Redirection to login page failed!\n");
	 }
     TestUtils.takeSnapShot(method.getName());
     
	}	
	
}

