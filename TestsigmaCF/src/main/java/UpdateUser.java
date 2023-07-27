import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class UpdateUser extends TestsigmaCustomFunctions{
    @CustomTestStep
    public TestStepResult MapApi(String envvariable , String Userdetails ) throws IOException {
        TestStepResult result= new TestStepResult();

        try {
            String inputString = getGlobalParameterValue(envvariable);
            String substring = Userdetails;

            String outputString = appendTimestampToSubstring(inputString, substring);
            setGlobalParameterValue(envvariable,outputString);
            result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("Output String: " + outputString);

        }
        catch(Exception e) {
            result.setStatus(ResultConstants.FAILURE);
            result.setMessage("Could not create a map"+e.getMessage());
        }
        return result;
    }
    public static String appendTimestampToSubstring(String inputString, String substring) {

        Instant timestamp = Instant.now();
        String timestampString = formatTimestamp(timestamp);
        String replacement = substring + timestampString;
        String outputString = inputString.replace(substring, replacement);
        return outputString;
    }
    public static String formatTimestamp(Instant timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return formatter.format(timestamp);
    }
    }




