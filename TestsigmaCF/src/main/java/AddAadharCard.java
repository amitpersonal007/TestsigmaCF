
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;


public class AddAadharCard extends TestsigmaCustomFunctions{
    
   
      @CustomTestStep
    public TestStepResult concat(String runtimeVarname ) {
           TestStepResult result= new TestStepResult();
           StringBuffer sb = new StringBuffer();
               
           
           try {
        	   
        	String generatedString = RandomStringUtils.randomAlphabetic(5);
       	    Random random = new Random();
       	    int x = random.nextInt(9000) + 1000;
       	    String generatedString2 = RandomStringUtils.randomAlphabetic(1);
        	String AadharCard = generatedString+String.valueOf(x)+generatedString2;
        	
        	setRuntimeData(runtimeVarname, AadharCard);
        	   
         
            result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("The aadharcard Generated is  "+AadharCard+"  and is stored in runtime variable "+runtimeVarname);
                
            
        } catch (Exception e) {
            
            result.setStatus(ResultConstants.FAILURE);
            result.setMessage("Unable to perform action. The URL used is "+sb+   "     " +e.getCause()+e.getMessage());
            
            e.printStackTrace();
        }
      
    
          return result;  
          
        
            
      }
      
      
      
}