
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.jayway.jsonpath.JsonPath;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;


public class newapicall extends TestsigmaCustomFunctions{
    
    @CustomTestStep
    public TestStepResult FetchApprovers(String ID,String csrfToken, String sessionID) throws TestEngineException, IOException {
    	TestStepResult result= new TestStepResult();
    	try {
    		
    		String ids=getRuntimeData(ID).toString();
        	String csrft=getTestDataParameterValue(csrfToken).toString();
        	String sid=getTestDataParameterValue(sessionID).toString();
          
          			String cookievalue="csrftoken="+csrft+";sessionid="+sid;
          			String csrftokenvalue=csrft;
          			StringBuffer sb= new StringBuffer();
          			
          			String PRID=String.valueOf(getRuntimeData(ID));
          		
          			String approvers="PR_approvers";
        			OkHttpClient client = new OkHttpClient().newBuilder()
        			  .build();
        			Request request = new Request.Builder()
        			  .url("https://veera.ngrok.io/api/purchase-requests/"+PRID+"/?version=2")
        			  .method("GET", null)
        			  .addHeader("cookie", cookievalue)
        			  .addHeader("X-CSRFToken", csrftokenvalue)
        			  .addHeader("Content-Type", "application/json")
        			  .build();
        			Response response = client.newCall(request).execute();
        			response.code();
        			List<String> approver = JsonPath.read(response.body().string(), "$.decision_steps[*].approver.user_id");
         			String approversdata="";
         			
        			 
        			int sizeofarray=approver.size();
        				for (int i=0;i<sizeofarray;i++) {
                       approversdata=   approver.get(i)+","+approversdata;
      				
        				}
          	setRuntimeData(approvers,approversdata);
          	 result.setStatus(ResultConstants.SUCCESS);
             result.setMessage("Passed");
    		
    	}
    	catch(Exception e){
    		  result.setStatus(ResultConstants.FAILURE);
    		e.printStackTrace();
    		StringBuffer sb=new StringBuffer();
    		sb.append(e.getMessage());
    		 
    		 result.setMessage(sb.toString());
    		
    	}
         
        return result; 
    }   
    
}