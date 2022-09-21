
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;

import io.appium.java_client.android.AndroidDriver;

public class SwitchToWindows extends TestsigmaCustomFunctions {

		
		@CustomTestStep
		public TestStepResult windowSwitch(int index)  {
			
			AndroidDriver d = (AndroidDriver)driver;	
			Set<String> windowHandles = driver.getWindowHandles();
		    List<String> windowStrings = new ArrayList<String>(windowHandles);
		    String reqWindow = windowStrings.get(index);
		    driver.switchTo().window(reqWindow);
		   
			
			TestStepResult result=new TestStepResult();
	    	result.setStatus(ResultConstants.SUCCESS);
	    	result.setMessage("Total Window handles present is "+windowHandles+"  Switched to windowhandle with name "+reqWindow);
	    	return result;
			
		}

		
		
		
	}