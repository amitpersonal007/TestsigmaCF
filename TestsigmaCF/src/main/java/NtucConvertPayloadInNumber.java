

import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import io.appium.java_client.android.AndroidDriver;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;




public class NtucConvertPayloadInNumber extends TestsigmaCustomFunctions{
    
   
  	@CustomTestStep
	public TestStepResult MapApi(String runtimevar) throws IOException {
  		 TestStepResult result= new TestStepResult();
	      AndroidDriver d = (AndroidDriver)driver;
	  
	 try {
		 
		 String var = getRuntimeData(runtimevar);
		 int var1 = Integer.getInteger(var);
		 
		 OkHttpClient client = new OkHttpClient().newBuilder()
				 .build();
				 MediaType mediaType = MediaType.parse("application/json");
				 RequestBody body = RequestBody.create(mediaType, "{\r\n    \"firstName\": \"Muhammad\",\r\n    \"lastName\": \"Ovi\",\r\n    \"age\": "+var1+"\r\n}");
				 Request request = new Request.Builder()
				 .url("https://dummyjson.com/users/add")
				 .method("POST", body)
				 .addHeader("Content-Type", "application/json")
				 .build();
				 Response response = client.newCall(request).execute();
		 
		
				
				System.out.println(response.code());
				
				
				StringBuffer sb = new StringBuffer(response.body().string());
				
	
		result.setStatus(ResultConstants.SUCCESS);
		result.setMessage("Response code is "+response.code()+" Response is "+ sb.toString());
		
		 
	 }
	 catch(Exception e) {
		 
		result.setStatus(ResultConstants.FAILURE);
		result.setMessage("Could not execute"+e.getMessage());
		 
	 }
			return result;
			
  	}}
		  
		 


















