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
import PageObjects.ProductsPage;
import PageObjects.SearchResultPage;
import Util.ReadXLSData;
import Util.TestUtils;

public class SearchResultPageTest extends BaseClass{

	public SearchResultPageTest() throws IOException {
		super();		
	}
	HomePage homepage;
	ProductsPage productspage;
	SearchResultPage searchresultpage;
	
	@Parameters({"Browser"})
	@BeforeMethod
	public void setUp(String Browser,Method method) throws InterruptedException, IOException {		
		Logger = extent.startTest(method.getName());
		initialization(Browser); 
		this.homepage  = new HomePage();
		this.productspage= new ProductsPage();
		this.searchresultpage = new SearchResultPage();
	    
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
	
	
	@Test(priority=9,dataProvider="searchProduct")	
	public void SearchProduct(Method method,String ProductName) throws IOException, InterruptedException {
		System.out.println("I am inside Test Case 9: Search Product \n");
		homepage.verifyHomePageTitle();
		homepage.verifyHomePageLogo();
		productspage = homepage.ClickOnProductsLink();
		// Verify if the user is on the "ALL PRODUCTS" page
		productspage.verifyAllProductsTitle();
		searchresultpage.searchProduct(ProductName);
		searchresultpage = searchresultpage.ClickOnSearchBtn();		
		//Verify 'SEARCHED PRODUCTS' is visible
		searchresultpage.verifyTitle("Searched Products");
		//Verify all the products related to search are visible
		boolean areSearchResultsVisible = searchresultpage.SearchResultsVisibility();
		if (areSearchResultsVisible) {
            System.out.println("All the products linked to the search  are  displayed");
        } else {
            System.out.println("Some or all products linked to the search are not displayed!");
        }
		 TestUtils.takeSnapShot(method.getName());
	}	
	
   @DataProvider
	public Object[][] searchProduct() throws IOException {
		Object data[][]= ReadXLSData.getDataFromExcel("SearchProduct");
		return data;		
	}
		

}
