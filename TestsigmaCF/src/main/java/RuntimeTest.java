
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;




public class RuntimeTest extends TestsigmaCustomFunctions{
    
    @CustomTestStep
    public TestStepResult sampleCustomStep(String runtimedata) throws TestEngineException {
         
         //your custom code starts here;
    	
    	String runtimevalue=getRuntimeData(runtimedata);
    	
    	
    	
    	
    	
    	//setTestDataParameterValue(globalparametername,  runtimevalue);
      	
         //your custom code ends here

        TestStepResult result= new TestStepResult();
        result.setStatus(ResultConstants.SUCCESS);
        result.setMessage("Successfully Executed"+runtimevalue);
        return result;
    }
}