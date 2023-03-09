

import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import org.apache.commons.lang3.StringUtils;


public class ConCat extends TestsigmaCustomFunctions{
    
   
      @CustomTestStep
    public TestStepResult concat(String runtimevarname ) {
           TestStepResult result= new TestStepResult();
           StringBuffer sb = new StringBuffer();
           
           try {
               
           String str = "testsigma";
           setRuntimeData(runtimevarname,str);
             result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("The new runtime variable is"+runtimevarname );
                
            
        } catch (Exception e) {
            
            result.setStatus(ResultConstants.FAILURE);
            result.setMessage("Unable to perform action. The URL used is "+sb+   "     " +e.getCause()+e.getMessage());
            
            e.printStackTrace();
        }
      
    
          return result;  
          
        
            
      }
      
}