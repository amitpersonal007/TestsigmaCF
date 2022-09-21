
import java.io.IOException;

import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestCase extends TestsigmaCustomFunctions{
	@CustomTestStep
	public TestStepResult teampayapicall(String csrftokenvariable,String sessionidvariable, String email, String password) throws IOException, TestEngineException {
		
      OkHttpClient client = new OkHttpClient().newBuilder()
 	 .build();
MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(mediaType, "{\n    \"email\" :\""+email+"\",\n    \"password\":\""+password+"\"\n\n}");
//RequestBody body = RequestBody.create(mediaType, "{\"email\":"+email+",\"password\":"+password+"}");
Request request = new Request.Builder()
  .url("https://staging.teampayhq.com/api/login/")
  .method("POST", body)
  .addHeader("cookie", "csrftoken="+csrftokenvariable)
  .addHeader("x-csrftoken", sessionidvariable)
  .addHeader("content-type", "application/json")
  .build();
Response response = client.newCall(request).execute();
		
				String sb=response.header("set-cookie");
	
				Headers reposneheader=response.headers();
				
				String totalresponseheader=reposneheader.toString();
				System.out.println(totalresponseheader);
				String csrf=totalresponseheader.substring(totalresponseheader.indexOf("csrftoken")+10, totalresponseheader.indexOf("expires")-2);
				String sessionid1=sb.substring(sb.indexOf("sessionid")+10, sb.indexOf("expires")-2);
				setRuntimeData(csrftokenvariable, csrf.trim());
				setRuntimeData(sessionidvariable, sessionid1.trim());
				
				
				System.out.println("CSRF is "+csrf.trim());
				System.out.println("Session ID  is "+sessionid1.trim());
		
		 TestStepResult result = new TestStepResult();
		 result.setMessage("CSRF token stored in variable with name   "+csrftokenvariable+"  with value  "+csrf.trim()+"  And SessionID is stored in a variable with name  "+sessionidvariable+"  with value "+sessionid1.trim()+"   The actual response header is::::::::"+totalresponseheader+ "  RESPONSE STATUS IS >>> "+response.code());
		result.setStatus(ResultConstants.SUCCESS);
		 return result;
		
		
	}

}
