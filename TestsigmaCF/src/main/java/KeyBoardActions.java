
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventMetaModifier;

public class KeyBoardActions extends TestsigmaCustomFunctions{
    
   
  	@CustomTestStep
	public TestStepResult KeyEventdata( String string) {
	  AndroidDriver d = (AndroidDriver)driver;	
      TestStepResult result= new TestStepResult();
      try{
    	  char[] arr = string.toCharArray();
    	  
       
        for(char c:arr){
        	
           if(Character.isLowerCase(c)){
        	   d.pressKey(new KeyEvent(Enum.valueOf(AndroidKey.class, String.valueOf(c).toUpperCase())));
             }
           else if(Character.isUpperCase(c)){
        	   d.pressKey(new KeyEvent(Enum.valueOf(AndroidKey.class, String.valueOf(c))).withMetaModifier(KeyEventMetaModifier.SHIFT_ON));
        	 
           }
           else if(String.valueOf(c).equals("@")){
        	   d.pressKey(new KeyEvent(AndroidKey.AT)); 
           }
           else if(String.valueOf(c).equals("=")){
        	   d.pressKey(new KeyEvent(AndroidKey.EQUALS));
           }
           
           else if(String.valueOf(c).equals("#")){
        	   d.pressKey(new KeyEvent(AndroidKey.POUND)); 
           }
           
           else if(String.valueOf(c).equals("/")){
        	   d.pressKey(new KeyEvent(AndroidKey.SLASH)); 
           }
           else if(String.valueOf(c).equals("*")){
        	   d.pressKey(new KeyEvent(AndroidKey.STAR)); 
           }
           else if(String.valueOf(c).equals("+")){
        	   d.pressKey(new KeyEvent(AndroidKey.PLUS)); 
           }
           else if(String.valueOf(c).equals("-")){
        	   d.pressKey(new KeyEvent(AndroidKey.MINUS)); 
           }
           else if(String.valueOf(c).equals("[")){
        	   d.pressKey(new KeyEvent(AndroidKey.LEFT_BRACKET)); 
           }
           else if(String.valueOf(c).equals("]")){
        	   d.pressKey(new KeyEvent(AndroidKey.RIGHT_BRACKET)); 
           }
           else if(String.valueOf(c).equals(".")){
        	   d.pressKey(new KeyEvent(AndroidKey.NUMPAD_DOT)); 
           }
           else if(String.valueOf(c).equals(";")){
        	   d.pressKey(new KeyEvent(AndroidKey.SEMICOLON)); 
           }
           
           else if(String.valueOf(c).equals(" ")) {
            	   d.pressKey(new KeyEvent(AndroidKey.SPACE)); 
               }

           else if(String.valueOf(c).equals("1")) {
            	   d.pressKey(new KeyEvent(AndroidKey.DIGIT_1)); 
            	 	   
               }

           else if(String.valueOf(c).equals("0")) {
            	   d.pressKey(new KeyEvent(AndroidKey.DIGIT_0)); 
               }

           else if(String.valueOf(c).equals("2")) {
            	   d.pressKey(new KeyEvent(AndroidKey.DIGIT_2)); 
               }

           else if(String.valueOf(c).equals("3")) {
            	   d.pressKey(new KeyEvent(AndroidKey.DIGIT_3)); 
               }

           else if(String.valueOf(c).equals("4")) {
            	   d.pressKey(new KeyEvent(AndroidKey.DIGIT_4)); 
               }

           else if(String.valueOf(c).equals("5")) {
            	   d.pressKey(new KeyEvent(AndroidKey.DIGIT_5)); 
               }

           else if(String.valueOf(c).equals("6")) {
            	   d.pressKey(new KeyEvent(AndroidKey.DIGIT_6)); 
               }
           else if(String.valueOf(c).equals("7")) {
        	   d.pressKey(new KeyEvent(AndroidKey.DIGIT_7)); 
           }
           else if(String.valueOf(c).equals("8")) {
        	   d.pressKey(new KeyEvent(AndroidKey.DIGIT_8)); 
           }
           else if(String.valueOf(c).equals("9")) {
        	   d.pressKey(new KeyEvent(AndroidKey.DIGIT_9)); 
           } 
           else {
        	   
        	  
        	   d.pressKey(new KeyEvent(AndroidKey.NUMPAD_1));
           }
      
      }
 
		result.setStatus(ResultConstants.SUCCESS);
		result.setMessage("Key entered");
		return result;      
      }
      catch (Exception e) {
            e.printStackTrace();
            StringBuffer sb=new StringBuffer();
            sb.append(e.getCause()+e.getMessage());
            result.setStatus(ResultConstants.FAILURE);

            result.setMessage("Failed "+sb);
            
            return result;}
	}}
