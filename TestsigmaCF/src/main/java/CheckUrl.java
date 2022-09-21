
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;
import org.apache.commons.lang3.exception.ExceptionUtils;


public class CheckUrl extends TestsigmaCustomFunctions {
StringBuffer sb = new StringBuffer();
  TestStepResult result = new TestStepResult();
  
	@CustomTestStep
	public TestStepResult loadhomepage1(String globalparametername) throws TestEngineException {
      try{
        String env_url = getGlobalParameterValue(globalparametername);
        String url = driver.getCurrentUrl();
        
        if (!url.contains("https://www.hoyer.de/")) {// if loading website first time
			//driver.navigate().to("https://www.hoyer.de/");
			driver.get("https://www.hoyer.de/");
			Thread.sleep(3000);
			// click on details
			if (driver.findElements(By.xpath("//button[@data-gtag='consent:show-details']")).size() > 0) {
				driver.findElement(By.xpath("//button[@data-gtag='consent:show-details']")).click(); // xpath for Details
				driver.findElement(
						By.xpath("//button[@data-gtag='consent:save-selection']"))
						.click(); // xpath for Speichern

			}

			
           
			result.setStatus(ResultConstants.SUCCESS);
		} else { // if the home page already loaded
			
		     sb.append("URL="+url);
	          sb.append("URL is Empty and ...");
	          sb.append("the output for env_url"+env_url);
				result.setMessage(sb.toString());
			if (driver.findElements(By.xpath("/html/body/div[1]/nav/div/div/a/div")).size() > 0) {
				driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/a/div")).click();
				result.setStatus(ResultConstants.SUCCESS);
				result.setMessage("succesfully navigate to homepage with click on hoyer logo");
			} else {
				driver.navigate().to("https://www.hoyer.de/");
				result.setStatus(ResultConstants.SUCCESS);
				result.setMessage("succesfully navigate to homepage with navigate function");
			}

			
		}

      }
      catch(Exception e){
        sb.append("<br>Exception:"+ExceptionUtils.getStackTrace(e));
        result.setStatus(ResultConstants.FAILURE);
        result.setMessage(sb.toString());
      }
		
		return result;
	}
  
}
