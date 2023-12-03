package testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Base.BaseClass;
import PageObjects.HomePage;
import PageObjects.TestCasesPage;
import Util.TestUtils;


public class TestCasesPageTest extends BaseClass{

	public TestCasesPageTest() throws IOException {
		super();		
	}	
	HomePage homepage;
	TestCasesPage  TestCases;
	
	@Parameters({"Browser"})
	@BeforeMethod
	public void setUp(String Browser, Method method) throws IOException {
		Logger = extent.startTest(method.getName());
		initialization(Browser); 
		this.homepage  = new HomePage();
		this.TestCases = new TestCasesPage();	    
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
	
	@Test(priority = 7)
	public void ClickOnTestCases(Method method) throws IOException, InterruptedException {	
		System.out.print("I am inside Test Case 7: Verify Test Cases Page\n");
		homepage.verifyHomePageTitle();		
		homepage.verifyHomePageLogo();
		TestCases = TestCases.ClickOnTestCasesLink();
		TestCases.verifyPageTitle("Test Cases");
		
		TestUtils.takeSnapShot(method.getName());
	   
	}
	

}
