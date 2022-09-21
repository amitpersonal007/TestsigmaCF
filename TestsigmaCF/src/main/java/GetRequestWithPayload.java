
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.commons.lang3.exception.ExceptionUtils;


public class GetRequestWithPayload extends TestsigmaCustomFunctions {
StringBuffer sb = new StringBuffer();
 TestStepResult result = new TestStepResult();
  
	@CustomTestStep
	public TestStepResult loadhomepage1() throws TestEngineException {
      try{
   
    	  OkHttpClient client = new OkHttpClient().newBuilder()
    			  .build();
    			MediaType mediaType = MediaType.parse("application/json");
    			RequestBody body = RequestBody.create(mediaType, "{\n\t  \"query\": {\n\t    \"bool\": {\n\t      \"filter\": [\n\t  \t        {\n\t          \"term\": {\n\t            \"ad_matrix_join\": \"ad_matrix_state\"\n\t          }\n\t        },\n\t        {\n\t          \"term\": {\n\t            \"account_id\": \"6090fead53bc363849fce989\"\n\t          }\n\t        }     \n\t      ]\n\t    }\n\t  }\n}");
    			Request request = new Request.Builder()
    			  .url("http://vcs7pelb.geoedge.be/es/ad_matrix_agg_*/_search")
    			  .method("GET", body)
    			  .addHeader("Content-Type", "application/json")
    			  .addHeader("Authorization", "Basic ZWxhc3RpYzppQ2lxaFZhQ0p5JjlLMiNp")
    			  .build();
    			Response response = client.newCall(request).execute();
    			
    			
    			sb.append(response.body().string());
    			 result.setStatus(ResultConstants.SUCCESS);
    		     result.setMessage(sb.toString());
			
		

      }
      catch(Exception e){
        sb.append("<br>Exception:"+ExceptionUtils.getStackTrace(e));
        result.setStatus(ResultConstants.FAILURE);
        result.setMessage(sb.toString());
      }
		
		return result;
	}
  
}
