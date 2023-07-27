import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchLastDateUser extends TestsigmaCustomFunctions{
    @CustomTestStep
    public TestStepResult MapApi(String envvariable ) throws IOException {
        TestStepResult result= new TestStepResult();

        try {
            String dateuser = getGlobalParameterValue(envvariable);
            Map.Entry<Instant, String> oldestDateInfo = findOldestDate(dateuser);
            System.out.println("Oldest Date: " + oldestDateInfo.getKey());
           // System.out.println("User Data: " + oldestDateInfo.getValue());
            result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("User Data: " + oldestDateInfo.getValue());
        }
        catch(Exception e) {
            result.setStatus(ResultConstants.FAILURE);
            result.setMessage("Could not create a map"+e.getMessage());
        }
        return result;
    }
    public static Map.Entry<Instant, String> findOldestDate(String data) {
        Pattern pattern = Pattern.compile("([a-zA-Z]+)(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{6}Z)");
        Matcher matcher = pattern.matcher(data);

        Instant oldestDate = null;
        String oldestUserData = null;

        while (matcher.find()) {
            String userData = matcher.group(1);
            String timestampString = matcher.group(2);
            Instant currentTimestamp = Instant.parse(timestampString);

            if (oldestDate == null || currentTimestamp.isBefore(oldestDate)) {
                oldestDate = currentTimestamp;
                oldestUserData = userData;
            }
        }
        return new HashMap.SimpleEntry<>(oldestDate, oldestUserData);
    }
}


