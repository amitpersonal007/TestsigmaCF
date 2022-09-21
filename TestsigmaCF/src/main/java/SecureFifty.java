
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

import org.apache.commons.lang3.StringUtils;


public class SecureFifty extends TestsigmaCustomFunctions{
    
   
      @CustomTestStep
    public TestStepResult concat(String lon , String lat )  {
           TestStepResult result= new TestStepResult();
           
           
           try {
        	   
        	   String longi =  String.valueOf(lon);
               
               String lati = String.valueOf(lat);
                 
               setRuntimeData("latitude", lati);
               setRuntimeData("longitude", longi);
               
               result.setStatus(ResultConstants.SUCCESS);
               result.setMessage("Successfully executed runtime data are "+lati+ " "+longi);
        	   
           }catch(Exception e) {
        	   
        	   result.setStatus(ResultConstants.FAILURE);
        	   result.setMessage(e.getMessage()  + e.getCause());
        	   
        	   
        	   
           }
           
        
        
          return result;  
          
        
            
      }
      
}