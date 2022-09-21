
import org.jboss.aerogear.security.otp.Totp;

import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestsigmaTestdataNotFoundException;


public class TOTPGenerator extends TestsigmaCustomFunctions{

    @CustomTestStep
    public TestStepResult generateOTP(String secretkey , String runtime_variable_name) throws TestsigmaTestdataNotFoundException {
        TestStepResult result= new TestStepResult();
        try{
            Totp otpgenerator = new Totp(secretkey);
            String newotp = otpgenerator.now();
            //storing to runtime variable given as argument in Custom Step
            setRuntimeData(runtime_variable_name, newotp);

            result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("OTP generated and stored in runtime variable named "+runtime_variable_name);
        }
        catch(Exception e){
            result.setStatus(ResultConstants.FAILURE);
            result.setMessage("otp not generated");
        }
        return result;
    }
}