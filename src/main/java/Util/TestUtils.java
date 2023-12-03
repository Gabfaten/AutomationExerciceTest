package Util;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import Base.BaseClass;


public class TestUtils extends BaseClass{
	public TestUtils() throws IOException {
		super();	
	}
	
	public static void takeSnapShot(String name)throws IOException {		
	  File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);		
	  FileUtils.copyFile(srcFile, new File(".\\TestReport\\"+ name+".png"));		
	}	
	
	public static boolean isFileDownload(String expectedFileName,
			String fileExtension,int timeout) {		
		
		String folderName = System.getProperty("user.dir")+ File.separator + "Downloads";
		File[] ListOfFiles;
		String fileName;
		
		boolean fileDownloaded=false;
		long startTime= Instant.now().toEpochMilli();
		long waitTime = startTime + timeout;
		while (Instant.now().toEpochMilli()< waitTime) {
			//get all the files of the downloaded folder
			ListOfFiles= new File(folderName).listFiles();
			for (File file:ListOfFiles) {			
				fileName=file.getName().toLowerCase();				
				if(fileName.contains(expectedFileName.toLowerCase())&& fileName.contains(fileExtension.toLowerCase())
						&& file.lastModified()> startTime && !fileName.contains("crdownload")) {
					fileDownloaded=true;
					break;					
				}				
			}			
			if (fileDownloaded) {				
				break;
			}	
		}
		return fileDownloaded;
}

}
