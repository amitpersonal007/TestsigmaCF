
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.lang3.StringUtils;




public class KeyCode extends TestsigmaCustomFunctions{
    
   
  	@CustomTestStep
	public TestStepResult Enterkey2( String input) {
  		 TestStepResult result= new TestStepResult();
	  AndroidDriver d = (AndroidDriver)driver;
	  
	 
	  //String specialChars = "!@#$&*?";
	 
		  for(int i = 0; i < input.length(); i++) {
			  char ch = input.charAt(i);
			  
			  if(Character.isUpperCase(ch)) {
				  
				  d.pressKey(new KeyEvent(Enum.valueOf(AndroidKey.class, String.valueOf(ch))));
				  
			  }
			  
			  else if(Character.isLowerCase(ch)) {
				  
				  d.pressKey(new KeyEvent(Enum.valueOf(AndroidKey.class, String.valueOf(ch))));
				  
					  
			  }
			  
		  }
		  result.setStatus(ResultConstants.SUCCESS);
			result.setMessage("Key entered");
			return result;
			
  	}}
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		