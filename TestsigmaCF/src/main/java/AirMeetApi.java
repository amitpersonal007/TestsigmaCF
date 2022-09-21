
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.jayway.jsonpath.JsonPath;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

import io.appium.java_client.android.AndroidDriver;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class AirMeetApi extends TestsigmaCustomFunctions {

  TestStepResult result = new TestStepResult();
  
  AndroidDriver d = (AndroidDriver)driver;
  
	@CustomTestStep
	public TestStepResult validateeventstatus(String EventURLforAPI , String XpathofElement) throws TestEngineException {
		 	
      try {
    	 
    	  OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				//RequestBody body = RequestBody.create(mediaType, "");
				Request request = new Request.Builder()
				  .url(EventURLforAPI)
				  .method("GET", null)
				  .addHeader("Cookie", "__cf_bm=ZbVcZPu5EGlPoTiv8S7COt2r9pwqtJeliiuMA6EHrSw-1655113754-0-AcGem2SRct4g+MKkaS42dVIHfu+Y2qNly05omKC/eCxEvPvtrGprPGkwuDjq8zGLV0FIZZv0uFwAaHXXdrW0LLU=; _cfuvid=II4gnYbhnG4OfqASe74Xqt1ECwQBcqFMS_Gnq_GGlj4-1655113754383-0-604800000")
				  .build();
				Response response = client.newCall(request).execute();
				
				 result.setMessage("Response code is  "+response.code());
			String responsebody=response.body().string();
			
			WebElement wb = d.findElement(By.xpath(""+XpathofElement+""));
			String value = wb.getText();
            String status1 = JsonPath.parse(responsebody).read("$.status");
            result.setMessage("Response code is  "+value+" json value "+status1);
			switch(status1) {
			
			case "ONGOING" :
				if (value.equals("Started")) {
					
					 result.setStatus(ResultConstants.SUCCESS);
					 result.setMessage("API status is ONGOING and UI status is Started");
					
				}else {
					
					 result.setStatus(ResultConstants.FAILURE);
					 result.setMessage("API and UI status doesnot match:::Value from the UI is "+value);
					
				}
				
				break;
				
				
			case "FINISHED" :
				
				if (value.equals("Ended")) {
					
					 result.setStatus(ResultConstants.SUCCESS);
					 result.setMessage("API status is FINISHED and UI status is Ended");
					
				}else {
					
					 result.setStatus(ResultConstants.FAILURE);
					 result.setMessage("API and UI status doesnot match:::Value from the UI is "+value);
					
				}
				
				break;
								
			case "PAUSED" :
				if (value.equals("Paused")) {
					
					 result.setStatus(ResultConstants.SUCCESS);
					 result.setMessage("API status is PAUSED and UI status is Paused");
				}else {
					
					 result.setStatus(ResultConstants.FAILURE);
					 result.setMessage("API and UI status doesnot match:::Value from the UI is "+value);
					
				}
				
				
				break;
				
			case "CREATED" :
				
				if (value.equals("Not Started")) {
					
					 result.setStatus(ResultConstants.SUCCESS);
					 result.setMessage("API status is CREATED and UI status is Not Started");
					
				}else {
					
					 result.setStatus(ResultConstants.FAILURE);
					 result.setMessage("API and UI status doesnot match:::Value from the UI is "+value);
					
				}
				
				break;
			
			}
			
			
    	  
      }catch(Exception e) {
    	  result.setStatus(ResultConstants.FAILURE);
    	  result.setMessage(e.getMessage());
      }
       	
		return result;
	}
		@CustomTestStep
	public TestStepResult validatespeakerlist(String EventURLforAPI, String XpathofSpeakerName) {
			
			 try {
		    	 
		    	  OkHttpClient client = new OkHttpClient().newBuilder()
						  .build();
						MediaType mediaType = MediaType.parse("text/plain");
						//RequestBody body = RequestBody.create(mediaType, "");
						Request request = new Request.Builder()
						  .url(EventURLforAPI)
						  .method("GET", null)
						  .addHeader("Cookie", "__cf_bm=ZbVcZPu5EGlPoTiv8S7COt2r9pwqtJeliiuMA6EHrSw-1655113754-0-AcGem2SRct4g+MKkaS42dVIHfu+Y2qNly05omKC/eCxEvPvtrGprPGkwuDjq8zGLV0FIZZv0uFwAaHXXdrW0LLU=; _cfuvid=II4gnYbhnG4OfqASe74Xqt1ECwQBcqFMS_Gnq_GGlj4-1655113754383-0-604800000")
						  .build();
						Response response = client.newCall(request).execute();
						
						
					String responsebody=response.body().string();
					
					WebElement wb = d.findElement(By.xpath(""+XpathofSpeakerName+""));
					String value = wb.getText();
					
					 ArrayList<String> speakerarr=	JsonPath.parse(responsebody).read("$.sessions[*].speakerList[*].name");
					 
					 if(speakerarr.contains(value)) {
						 result.setStatus(ResultConstants.SUCCESS);
						 result.setMessage("Speaker with name "+value+" is found in the API response");
					 }else {
						 result.setStatus(ResultConstants.FAILURE);
						 result.setMessage("Speaker with name "+value+" is not found in the API response");
					 }
					 
			 }
					 
					 catch(Exception e) {
						 result.setStatus(ResultConstants.FAILURE);
				    	  result.setMessage(e.getMessage());
				    	 
						 
					 }
				
		return result;
	
}
		
		@CustomTestStep
		public TestStepResult validateseventdetails(String EventURLforAPI, String XpathofEventname) {
			
			   try {
			    	 
			    	  OkHttpClient client = new OkHttpClient().newBuilder()
							  .build();
							MediaType mediaType = MediaType.parse("text/plain");
							//RequestBody body = RequestBody.create(mediaType, "");
							Request request = new Request.Builder()
							  .url(EventURLforAPI)
							  .method("GET", null)
							  .addHeader("Cookie", "__cf_bm=ZbVcZPu5EGlPoTiv8S7COt2r9pwqtJeliiuMA6EHrSw-1655113754-0-AcGem2SRct4g+MKkaS42dVIHfu+Y2qNly05omKC/eCxEvPvtrGprPGkwuDjq8zGLV0FIZZv0uFwAaHXXdrW0LLU=; _cfuvid=II4gnYbhnG4OfqASe74Xqt1ECwQBcqFMS_Gnq_GGlj4-1655113754383-0-604800000")
							  .build();
							Response response = client.newCall(request).execute();
							
							
						String responsebody=response.body().string();
						
						WebElement wb = d.findElement(By.xpath(""+XpathofEventname+""));
						String value = wb.getText();
						 ArrayList<String> arr=	JsonPath.parse(responsebody).read("$.sessions[*].name");
						 
						 if(arr.contains(value)) {
							 result.setStatus(ResultConstants.SUCCESS);
							 result.setMessage("Event with name "+value+" is found in the API response");
						 }else {
							 result.setStatus(ResultConstants.FAILURE);
							 result.setMessage("Event with name "+value+" is not found in the API response");
						 }
			            
			   }
			   catch(Exception e) {
				   
				   result.setStatus(ResultConstants.FAILURE);
					 result.setMessage(e.getMessage() + e.getCause());
				   
			   }
			
			return result;
			
		}
		
		@CustomTestStep
		public TestStepResult geteventtime(String EventURLforAPI, String XpathofEventname) {
			return result;
			
		}
		
  
}
