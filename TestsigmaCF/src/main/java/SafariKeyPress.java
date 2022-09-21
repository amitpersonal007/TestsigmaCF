
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class SafariKeyPress extends TestsigmaCustomFunctions{
    
    @CustomTestStep
    public TestStepResult sampleCustomStep() throws TestEngineException{
    	TestStepResult result= new TestStepResult();
    	try {
    		Robot robot = new Robot();
    		robot.keyPress(KeyEvent.VK_TAB);
    		
    		robot.keyPress(KeyEvent.VK_TAB);
    		robot.keyPress(KeyEvent.VK_ENTER);
    		
    	
         	
        
          
            result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("The complete URL is ");
    		
    	}catch(Exception e) {
    		
    		e.printStackTrace();
    		result.setStatus(ResultConstants.FAILURE);
    		result.setMessage("Failed to peform operation. Exception is "+e.getCause()+e.getMessage());
	
    	}
    	 
       return result;
    }
}