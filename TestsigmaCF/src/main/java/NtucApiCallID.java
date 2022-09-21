
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;




public class NtucApiCallID extends TestsigmaCustomFunctions{
    
    @CustomTestStep
    public TestStepResult sampleCustomStep(String runtimevarnameofresponse, String indexofarray,String runtimevar) throws TestEngineException {
         
        
    	String runtimevalue=getRuntimeData(runtimevarnameofresponse);
    	
    	String str1= runtimevalue.replaceAll("\\[", "").replaceAll("\\]","");
    	String [] str2=str1.split(",");
    	String str3 =str2[Integer.parseInt(indexofarray)];
    	
    	setRuntimeData(runtimevar, str3);
    	
        TestStepResult result= new TestStepResult();
        result.setStatus(ResultConstants.SUCCESS);
        result.setMessage("Splitted the response, the data stored is "+str3+"  inside the runtime variable name "+runtimevar);
        return result;
    }
}