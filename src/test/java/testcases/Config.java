package testcases;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;

import Base.BaseClass;

public class Config extends BaseClass {

	public Config() throws IOException {
		super();		
	}
	
	@BeforeSuite
	public void start() {		
		extent = new ExtentReports(".\\TestReport\\index.html", true);
		//extent.addSystemInfo("Test Name", "Free Crm Test");
	   // extent.addSystemInfo("Language", "Java");
	}
	@AfterSuite
	public void End() {
		extent.flush();
	}

}
