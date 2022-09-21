import java.util.Set;

import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class DynamicWebView extends TestsigmaCustomFunctions {
	
	 
  	@CustomTestStep
	public TestStepResult Enterkey2( String input) {
  		 TestStepResult result= new TestStepResult();
	  @SuppressWarnings("rawtypes")
	IOSDriver d = (IOSDriver)driver;
	  
	  
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	Set<String> contextNames = ((AppiumDriver) driver).getContextHandles();
	  
		  result.setStatus(ResultConstants.SUCCESS);
			result.setMessage(contextNames.toString() + contextNames.size());
			return result;
			
  	}}
		  
		  


