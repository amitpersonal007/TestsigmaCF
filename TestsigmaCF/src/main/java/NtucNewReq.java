import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NtucNewReq extends TestsigmaCustomFunctions{
    @CustomTestStep
    public TestStepResult MapApi(String envvariable , String Userdetails ) throws IOException {
        TestStepResult result= new TestStepResult();

        try {
            String user = getGlobalParameterValue(envvariable);
            ArrayList<String> arrayList = createArrayListFromCSV(user);
            appendElements(arrayList);

            for (String item : arrayList) {
                System.out.println(item);
            }

            String csvString = String.join("||",arrayList);

            setGlobalParameterValue(envvariable,csvString);

            result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("Data Updated");

        }
        catch(Exception e) {
            result.setStatus(ResultConstants.FAILURE);
            result.setMessage("Could not create a map"+e.getMessage());
        }
        return result;
    }
    public static ArrayList<String> createArrayListFromCSV(String csvString) {
        // Split the CSV string by commas
        String[] values = csvString.split(",");
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(values));
        return arrayList;
    }
    public static void appendElements(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            Instant timestamp = Instant.now();

            String timestampString = formatTimestamp(timestamp);
            String element = arrayList.get(i) + timestampString + (i + 1);
            arrayList.set(i, element);
        }
}
    public static String formatTimestamp(Instant timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return formatter.format(timestamp);
    }
}

