package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Util.WebListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;



public class BaseClass {
	
	 public static WebDriver driver;	
	 public static Properties prop;
	 public static com.relevantcodes.extentreports.ExtentReports extent;	
	 public static ExtentTest Logger;
	 
	public static EventFiringWebDriver e_driver;
	public static WebListener Weblistener ;
	 
    //Load config.properties	 
    protected void loadProperties() throws IOException {
        prop = new Properties();
        FileInputStream file = new FileInputStream(
            System.getProperty("user.dir") + "\\src\\main\\java\\Configuration\\config.properties");
        prop.load(file);
    }	

	  public BaseClass() throws IOException {
	        loadProperties();	      
	  }
	
	 public void initialization(String browser) { 		   
		
		if(browser.equalsIgnoreCase("chrome") ) {				
		    WebDriverManager.chromedriver().setup();
		    
			//change download directory
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("download.default_directory",
					"C:\\Users\\SAMSUNG\\eclipse-workspace\\AutomationExercice\\Downloads");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);			
			 driver = new ChromeDriver(options);
			 
		   }
		   else if (browser.equalsIgnoreCase("firefox") ) {	
			    WebDriverManager.firefoxdriver().setup();
			    
			  //change download directory
			   FirefoxOptions options = new FirefoxOptions();
			   FirefoxProfile profile = new FirefoxProfile(); 
			   
			   profile.setPreference("browser.download.dir",
					   "C:\\Users\\SAMSUNG\\eclipse-workspace\\AutomationExercice\\Downloads"); 
			   
			   //Set Preference to not show file download confirmation dialogue
			   profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
			   //If it is set as ‘2 ’ save file at the location 
			   //specified for the most recent download
			   profile.setPreference("browser.download.folderList",2); 
			   profile.setPreference("browser.download.manager.showWhenStarting",false); 
			   
			   options.setProfile(profile);
			   driver = new FirefoxDriver(options);			 
			   
			}
		    else if (browser.equalsIgnoreCase("Edge") ) {	
		    	 WebDriverManager.edgedriver().setup();
		    	 driver =  new EdgeDriver();
			}		     
		   // Attach WebListener
		    e_driver = new EventFiringWebDriver(driver);
		    Weblistener = new WebListener();
		    e_driver.register(Weblistener);
		    driver = e_driver;	
		    
		    driver.get(prop.getProperty("URL"));		  
		    driver.manage().window().maximize();	    
		    
		    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		    

 }	
	
}





