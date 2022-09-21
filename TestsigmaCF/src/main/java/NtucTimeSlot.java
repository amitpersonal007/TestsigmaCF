
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;

import io.appium.java_client.ios.IOSDriver;

public class NtucTimeSlot extends TestsigmaCustomFunctions {

		
		@CustomTestStep
		public TestStepResult allocatetimeslot()  {
			
			TestStepResult result=new TestStepResult();
			
			IOSDriver iosdriver = (IOSDriver)driver;
			
			try {
				
				@SuppressWarnings("unchecked")
				List<WebElement> dayslot=iosdriver.findElements(By.xpath("//XCUIElementTypeApplication[@name=\"FairPrice\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeCollectionView/XCUIElementTypeCell"));
			int count =	dayslot.size();
			
			for(int i=0;i<=count;i++) {
				
				dayslot.get(i).click();
				
				if(dayslot.get(i).isDisplayed() && driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Available_Slots\"]")).isEnabled()) {
					
					break;
				}
				
				driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Available_Slots\"]")).click();
				
			}
			
			result.setStatus(ResultConstants.SUCCESS);
	    	result.setMessage("Successfully selected the time slot");
				
			}
			catch(Exception e) {
				e.printStackTrace();
				result.setStatus(ResultConstants.FAILURE);
		    	result.setMessage("Failed to perform operation. Exception is "+e.getMessage());
				
				
			}
			
		
			
	    	
	    	return result;
			
		}	
	}