
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;

import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import java.util.HashMap;

import org.apache.commons.lang3.exception.ExceptionUtils;


public class MobileScroll extends TestsigmaCustomFunctions {
StringBuffer sb = new StringBuffer();
  TestStepResult result = new TestStepResult();
  
	@CustomTestStep
	public TestStepResult MobileScroll(String Xpath) throws TestEngineException {
		
    try {
    	
    	IOSDriver d = (IOSDriver)driver;	
       	WebElement element =d.findElement(By.xpath(""+Xpath+""));

 		JavascriptExecutor js = (JavascriptExecutor) driver;
    	HashMap<String, String> scrollObject = new HashMap<String, String>();
    	scrollObject.put("direction", "down");
    	scrollObject.put("element", ((RemoteWebElement) element).getId());
    	js.executeScript("mobile: scroll", scrollObject);
 		
 	
    	
    	
    	
    	 result.setStatus(ResultConstants.SUCCESS);
    	 result.setMessage("Mobile Scroll Successful");
    	 
      }
      catch(Exception e){
    	  result.setStatus(ResultConstants.FAILURE);
    	  result.setMessage("Mobile Scroll failed "+e.getMessage());
       
       
      }
		
		return result;
	}
  
}
