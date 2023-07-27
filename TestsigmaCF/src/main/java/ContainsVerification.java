import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

import java.text.ParseException;

public class ContainsVerification extends TestsigmaCustomFunctions {

    @SuppressWarnings("unchecked")
    @CustomTestStep

    public TestStepResult generate_random_kpi(String runtimeVar, String parameter) throws ParseException, TestEngineException {

        TestStepResult result = new TestStepResult();


        String tdp = getTestDataParameterValue(parameter);
        String runtimevar1 = getRuntimeData(runtimeVar);

        if (tdp.contains(runtimevar1)){
            result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("Volume data from TDP matches with the runtime variable "+runtimevar1+"::::"+tdp);
        }
        else{
            result.setStatus(ResultConstants.FAILURE);
            result.setMessage("Volume data from TDP does matches with the runtime variable "+runtimeVar+""+tdp);
        }
        return result;

    }
}
