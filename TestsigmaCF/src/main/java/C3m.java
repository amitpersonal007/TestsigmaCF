
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class C3m extends TestsigmaCustomFunctions{
    
    @CustomTestStep
    public TestStepResult sampleCustomStep(String apiURL,String runtimevarname) throws TestEngineException{
    	TestStepResult result= new TestStepResult();
    	try {
    		
    		String URL = getTestDataParamterValue(apiURL);
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY, 24);
            Date date2=calendar.getTime();
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
            dateformat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
            String presentDate=dateformat.format(date).toString();
            String endDate=dateformat.format(date2).toString();
         	String complete24HURL= URL+"&startDate="+presentDate+"&endDate="+endDate;
         	
        
            setRuntimeData(runtimevarname,complete24HURL);
            result.setStatus(ResultConstants.SUCCESS);
            result.setMessage("The complete URL is "+complete24HURL+" and its stored in runtime variable name "+runtimevarname);
    		
    	}catch(Exception e) {
    		
    		e.printStackTrace();
    		result.setStatus(ResultConstants.FAILURE);
    		result.setMessage("Failed to peform operation. Exception is "+e.getCause()+e.getMessage());
	
    	}
    	 
       return result;
    }
}