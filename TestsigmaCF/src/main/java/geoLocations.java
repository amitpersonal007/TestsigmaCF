import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.Location;

import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

import io.appium.java_client.android.AndroidDriver;

public class geoLocations extends TestsigmaCustomFunctions {
	
	@CustomTestStep
    public TestStepResult makegeolocation() throws TestEngineException, InterruptedException{
	    AndroidDriver androidDriver = (AndroidDriver) driver;
		Location location = new Location(28., 77., 0);
	    androidDriver.setLocation(location);


		TestStepResult result=new TestStepResult();
    	result.setStatus(ResultConstants.SUCCESS);
    	result.setMessage("Successfully Entered Location");
    	return result;
		
	}
}