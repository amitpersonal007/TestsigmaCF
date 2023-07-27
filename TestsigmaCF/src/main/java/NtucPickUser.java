
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import okhttp3.*;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NtucPickUser extends TestsigmaCustomFunctions{
    @CustomTestStep
    public TestStepResult MapApi(String envvariable, String runtimevar) throws IOException {
        TestStepResult result= new TestStepResult();
        try {
            String data = getGlobalParameterValue(envvariable);
            Map.Entry<Instant, String> oldestDateInfo = findOldestDate(data);
            String result1 = pickStringsWithoutTimestamp(data);
            System.out.println("Result: " + result1);

            // System.out.println("Strings without Timestamp: " + result1);
            if(result1.isEmpty()){
                setRuntimeData(runtimevar,oldestDateInfo.getValue().toString());
                result.setStatus(ResultConstants.SUCCESS);
                result.setMessage("Picking up user with oldest timestamp,since fresh user list is empty::::"+oldestDateInfo.getValue());

            }else{

                String randomEmail = pickRandomEmail(result1);
                Instant timestamp = Instant.now();
                String timestampString = formatTimestamp(timestamp);
                String new1 = randomEmail+timestampString;
                String outputString = data.replace(randomEmail, new1);
                updateenv(outputString);
                result.setStatus(ResultConstants.SUCCESS);
                result.setMessage("Picking a random user from fresh list,Since fresh users are available "+randomEmail+"  And updating the user with time stamp"+new1+"  Updated env variable is "+outputString);
            }

        }
        catch(Exception e) {
            result.setStatus(ResultConstants.FAILURE);
            result.setMessage("Exception Message is :::: "+e.getMessage());
        }
        return result;
    }

    public static Map.Entry<Instant, String> findOldestDate(String data) {
        Pattern pattern = Pattern.compile("([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[A-Z|a-z]{2,})(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{6}Z)");
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
    public static String pickStringsWithoutTimestamp(String inputString) {
        Pattern pattern = Pattern.compile("\\b[^,\\d]+\\b");
        Matcher matcher = pattern.matcher(inputString);

        StringBuilder resultBuilder = new StringBuilder();
        while (matcher.find()) {
            String value = matcher.group();
            resultBuilder.append(value).append(",");
        }

        // Remove the trailing comma if any
        if (resultBuilder.length() > 0) {
            resultBuilder.setLength(resultBuilder.length() - 1);
        }

        return resultBuilder.toString();
    }
    public static String toCSV(String[] values) {
        StringBuilder csvBuilder = new StringBuilder();
        for (String value : values) {
            csvBuilder.append(value).append(",");
        }
        // Remove the trailing comma if any
        if (csvBuilder.length() > 0) {
            csvBuilder.setLength(csvBuilder.length() - 1);
        }
        return csvBuilder.toString();
    }
    public static String pickRandomEmail(String emailsString) {
        if (emailsString == null || emailsString.isEmpty()) {
            throw new IllegalArgumentException("Email string cannot be empty or null.");
        }

        String[] emails = emailsString.split(",");
        Random random = new Random();
        int randomIndex = random.nextInt(emails.length);

        return emails[randomIndex].trim();
    }

    public static String formatTimestamp(Instant timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return formatter.format(timestamp);
    }

    public static void updateenv(String payload) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "     \"projectId\":\"567\",\n" +
                "    \"parameters\": {\n" +
                "         \"email\": \""+payload+"\"\n" +
                "}\n" +
                "}");
        Request request = new Request.Builder()
                .url("https://app.testsigma.com/api/v1/environments/274")
                .method("PUT", body)
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlOWVhYmQzYi05YjdhLTQ3NWYtOTI4MS1jYjUyODc5ZDU2NGUiLCJkb21haW4iOiJ0ZXN0c2lnbWF0ZWNoLmNvbSIsInRlbmFudElkIjoyODE3fQ.XBr9AhnM_kL4UPyv-yWxRc24yV4KhYJ41g0QlUVzClFi2Xo34Nsm2cFMwKY_4Md25f9OraapUPBGJ23KYnonVA")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

    }
}


