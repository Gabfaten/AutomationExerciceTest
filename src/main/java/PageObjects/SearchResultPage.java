package PageObjects;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import Base.BaseClass;
import actiondriver.Action;

public class SearchResultPage extends BaseClass{

	public SearchResultPage() throws IOException {
		PageFactory.initElements(driver,this);
	}
	Action action = new Action();	
	
	 @FindBy(id="search_product")
	 private static WebElement search_product;	
	 
	 @FindBy(id="submit_search")
	 private static WebElement submit_search;	
	 
	 @FindBy(css=".features_items>h2")
	 private static WebElement Title;	 
	
	public ProductsPage searchProduct(String ProductName) throws IOException {
		action.type(search_product, ProductName);
		action.implicitWait(driver, 20);
		return new ProductsPage();
		
	}
	public SearchResultPage ClickOnSearchBtn() throws IOException, InterruptedException{
		action.ClickOnElement(driver,submit_search);		
		action.implicitWait(driver, 50);
		return new SearchResultPage();
	}
	 public void verifyTitle(String expectedresult){ 		 
		 action.implicitWait(driver, 20);  
		 boolean actualres= action.GetText(Title, expectedresult);
		 Assert.assertTrue(actualres,"The title is incorrect!");			
	}	 	 
	
	 public boolean SearchResultsVisibility() {		
	    boolean areSearchResultsVisible = action.areElementsVisible(driver,
	    		By.className("product-image-wrapper"));
		return areSearchResultsVisible;
	}

}
