
import org.openqa.selenium.JavascriptExecutor;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestsigmaTestdataNotFoundException;


public class ShadowRoot extends TestsigmaCustomFunctions{

    @CustomTestStep
    public TestStepResult generateOTP(String Path,String filenameforvalidation) throws TestsigmaTestdataNotFoundException {
        TestStepResult result= new TestStepResult();
        
        //document.querySelector("body > downloads-manager").shadowRoot.querySelector("#frb0").shadowRoot.querySelector("#file-link").firstChild.nodeValue
    
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            String select=  (String) jse.executeScript(Path);
            
            if(filenameforvalidation.equals(select)) {
            result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("Successfully compared the file name "+select+" = "+filenameforvalidation);
            }
            
            else {
            	
            	result.setStatus(ResultConstants.FAILURE);
                result.setMessage("Failed to compared the file name "+select+" != "+filenameforvalidation);
            	
            }
        
             
            
       
        return result;
    }}
