

import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import org.apache.commons.lang3.StringUtils;


public class ConCat extends TestsigmaCustomFunctions{
    
   
      @CustomTestStep
    public TestStepResult concat(String completeURL ) {
           TestStepResult result= new TestStepResult();
           StringBuffer sb = new StringBuffer();
           
           try {
               
            String test = StringUtils.substringBetween(completeURL, "|");
            String data = getTestDataParamterValue(test);
            String newURL=  completeURL.replace("@|"+test+"|", data);
            sb.append(newURL);
            driver.get(newURL);
             result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("The new URL is "+newURL);
                
            
        } catch (Exception e) {
            
            result.setStatus(ResultConstants.FAILURE);
            result.setMessage("Unable to perform action. The URL used is "+sb+   "     " +e.getCause()+e.getMessage());
            
            e.printStackTrace();
        }
      
    
          return result;  
          
        
            
      }
      
}