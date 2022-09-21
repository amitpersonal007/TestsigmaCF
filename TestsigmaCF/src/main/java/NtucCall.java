
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.jayway.jsonpath.JsonPath;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import io.appium.java_client.android.AndroidDriver;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;




public class NtucCall extends TestsigmaCustomFunctions{
    
   
  	@CustomTestStep
	public TestStepResult MapApi(String accesstoken, String Xpath) throws IOException {
  		 TestStepResult result= new TestStepResult();
	  AndroidDriver d = (AndroidDriver)driver;
	  
	 try {
		 
		  OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				//RequestBody body = RequestBody.create(mediaType, "");
				Request request = new Request.Builder()
				  .url("https://api.zs-uat.fairprice.com.sg/order-service/order/70190259?include[0]=cancelledItems&rawData=true&includeSubstitution=true")
				  .method("GET", null)
				  .addHeader("Authorization", "Bearer "+accesstoken+"")
				  .build();
				Response response = client.newCall(request).execute();
				
				System.out.println(response.code());
				
				
				String responsebody=response.body().string();
				
		List<String> arr=	JsonPath.parse(responsebody).read("$.data.order.items[*].name");
		System.out.println(arr.get(0));
				 
				 
				
				 
		List<String> arr1=JsonPath.parse(responsebody).read("$.data.order.items[*].barcodes[0]");
		System.out.println(arr1.get(0));
			 
			 Map<String, String> myMap = new HashMap<String, String>();
			 for (int i = 0; i < arr.size(); i++) {
			      myMap.put(arr.get(i), arr1.get(i));
			 }
			 
		String clx=	 driver.findElement(By.xpath(""+Xpath+"")).getText();

		result.setStatus(ResultConstants.SUCCESS);
		result.setMessage("Created a map "+myMap+" Desired barcode of the product is "+myMap.get(clx));
		 
	 }
	 catch(Exception e) {
		 
		result.setStatus(ResultConstants.FAILURE);
		result.setMessage("Could not create a map"+e.getMessage());
		 
	 }
			return result;
			
  	}}
		  
		 