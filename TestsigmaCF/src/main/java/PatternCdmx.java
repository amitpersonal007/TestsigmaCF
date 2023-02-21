
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;



//runtimevarname = User needs to enter any string name for which they want to set runtime variable with replaced string
//testdata = Should be the entire function with runtimevariable and its symbol
//replaceString = runtime variable name where the data needs to be replaced thats present in test data

public class PatternCdmx extends TestsigmaCustomFunctions {

		
		@CustomTestStep
		public TestStepResult matchPattern(String runtimevarname, String testdata , String replaceString)  {
			
			TestStepResult result=new TestStepResult();
			
			
			
			try {
                Pattern pattern = Pattern.compile("\\$\\|(.*?)\\|");
                Matcher matcher = pattern.matcher(testdata);
                List<String> extractedStrings = new ArrayList<>();
                while (matcher.find()) {
                    String extractedString = matcher.group(1);
                    extractedStrings.add(extractedString);
                }

                String needtobeReplaced = extractedStrings.get(0);
                String valueOfString = getRuntimeData(needtobeReplaced);
                String replacedString = testdata.replace(needtobeReplaced,valueOfString);

                String regex = "\\$\\|([^|]+)\\|";
                String finalString = replacedString.replaceAll(regex, "$1");
               
                setRuntimeData(runtimevarname, finalString);

        
                result.setStatus(ResultConstants.SUCCESS);
                result.setMessage("Successfully created new data with runtime var name "+runtimevarname+ "   having value "+finalString);
				
								
			}
		
			catch(Exception e) {

                result.setStatus(ResultConstants.FAILURE);
                result.setMessage("Error with operation. Exception is "+e.getMessage());
				
				
			}
	    	
	    	return result;
			
		}	
	}