import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import org.openqa.selenium.JavascriptExecutor;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;


public class ClickOnDownload extends TestsigmaCustomFunctions {

    @CustomTestStep
    public TestStepResult clickonDownload() {
        TestStepResult result= new TestStepResult();

        try {

            String path = "document.querySelector(\"body > downloads-manager\").shadowRoot.querySelector(\"#frb0\").shadowRoot.querySelector(\"#url\").click();";
            JavascriptExecutor jse=(JavascriptExecutor)driver;
             jse.executeScript(path);

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
