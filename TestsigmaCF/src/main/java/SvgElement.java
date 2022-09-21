
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;




public class SvgElement extends TestsigmaCustomFunctions{
    
    @CustomTestStep
    public TestStepResult sampleCustomStep(String Xpath) throws TestEngineException {
         
    	 WebElement ele = driver.findElement(By.xpath(""+Xpath+""));
   //  new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(ele)).click();
   
    
    //Actions a = new Actions(driver);
   // a.moveToElement(ele).click().build().perform();
    
   // ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);

        TestStepResult result= new TestStepResult();
        result.setStatus(ResultConstants.SUCCESS);
        result.setMessage("Successfully Executed");
        return result;
    }
}