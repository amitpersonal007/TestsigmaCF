


import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;

import io.appium.java_client.AppiumDriver;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;




public class NtucApiStore extends TestsigmaCustomFunctions{
    
   
  	@CustomTestStep
	public TestStepResult MapApi(String desiredruntimevarname , String id , String BearerToken ) throws IOException {
  		 TestStepResult result= new TestStepResult();
	    
	      
	      AppiumDriver d =  (AppiumDriver)driver;
	  
	 try {
		 
		 String id1 = getRuntimeData(id);
		 String accesstoken = getRuntimeData(BearerToken);
		 OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = RequestBody.create(mediaType, "");
				Request request = new Request.Builder()
				  .url("https://storetech-uat.fairprice.com.sg/store/v0.2/customers/unblock/"+id1+"")
				  .method("PUT", body)
				  .addHeader("Authorization", "Bearer "+accesstoken+"")
				  .build();
				Response response = client.newCall(request).execute();
				
				String status_code = String.valueOf(response.code());
				
	    setRuntimeData(desiredruntimevarname,status_code );
		result.setStatus(ResultConstants.SUCCESS);
		result.setMessage("Response code is "+status_code+ " and is stored inside a runtime variable with name "+desiredruntimevarname);
		
		 
	 }
	 catch(Exception e) {
		 
		result.setStatus(ResultConstants.FAILURE);
		result.setMessage("Could not execute"+e.getMessage());
		 
	 }
			return result;
			
  	}}
		  
		 


















