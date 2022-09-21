
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.io.File;
import java.sql.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.html5.Location;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

import io.appium.java_client.android.AndroidDriver;

public class InfoLog extends TestsigmaCustomFunctions {
	
	@CustomTestStep
    public TestStepResult getLog(String inputstring) throws TestEngineException, InterruptedException{
		TestStepResult result=new TestStepResult();

		 LogEntries logEntries = driver.manage().logs().get("browser");
	     //   List<LogEntry> logEntryList = logEntries.getAll().stream().filter(logEntry -> logEntry.getLevel().equals(Level.INFO)).collect(Collectors.toList());
		
	      
	       // LogEntry mess = logEntryList.get(0);
//	        
//	        if (mess.toString().contains("Increased conversion rates")) {
//	        	result.setStatus(ResultConstants.SUCCESS);
//	        	result.setMessage("Condition passed   "+mess+"  contains "+inputstring);
//	        	
//	        }
//	        
//	        else {
//	        	result.setStatus(ResultConstants.FAILURE);
//	        	result.setMessage("Condition failed   "+mess+"  does not contains "+inputstring);
//	        	System.out.println("faoil");
//	        }
	
    	return result;
		
	}
}