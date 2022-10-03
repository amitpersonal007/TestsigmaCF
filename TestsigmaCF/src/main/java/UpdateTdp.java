
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

import org.apache.commons.lang3.StringUtils;


public class UpdateTdp extends TestsigmaCustomFunctions{
    
   
      @CustomTestStep
    public TestStepResult concat(String parametername, String parametervalue)  {
           TestStepResult result= new TestStepResult();
           
           
           try {
        	   
        	   String getdata = getRuntimeData(parametervalue);
        	   setTestDataParameterValue(parametername, getdata);
        	   
        	 
               result.setStatus(ResultConstants.SUCCESS);
               result.setMessage("Successfully updated the test data parameter with value"+getdata );
        	   
           }catch(Exception e) {
        	   
        	   result.setStatus(ResultConstants.FAILURE);
        	   result.setMessage(e.getMessage()  + e.getCause());
        	   
        	   
        	   
           }
           
        
        
          return result;  
          
        
            
      }
      
}