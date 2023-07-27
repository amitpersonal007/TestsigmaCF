import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;


public class TerminateApp extends TestsigmaCustomFunctions {

    @CustomTestStep
    public TestStepResult clickonDownload() {
        TestStepResult result= new TestStepResult();

        try {

            AndroidDriver d = (AndroidDriver)driver;
            d.terminateApp("com.recette.openeat");
            d.runAppInBackground(Duration.ofSeconds(10));

            result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("Successfully clicked on downloads ");


        } catch (Exception e) {

            result.setStatus(ResultConstants.FAILURE);
            result.setMessage("Unable to perform action. Stack trace is "+e.getMessage());

            e.printStackTrace();
        }

        return result;

    }
}

