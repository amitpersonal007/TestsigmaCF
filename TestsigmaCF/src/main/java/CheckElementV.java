
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


public class CheckElementV extends TestsigmaCustomFunctions{

    @CustomTestStep
    public TestStepResult sampleCustomStep(String ele1,String ele2,String ele3 , String WhileCount) throws TestEngineException, InterruptedException {
        TestStepResult result= new TestStepResult();
        StringBuffer sb = new StringBuffer();

        int count = 0;
        while (count < Integer.valueOf(WhileCount)) {
            boolean isElement1Displayed = false;
            boolean isElement2Displayed = false;
            boolean isElement3Displayed = false;

            try {
                WebElement element1 = driver.findElement(By.xpath(ele1));
                isElement1Displayed = element1.isDisplayed();
                if (isElement1Displayed) {
                    sb.append("Element 1 is displayed.");
                    System.out.println("Element 1 is displayed.");
                    break;
                }
            } catch (NoSuchElementException e) {
                // Element not found, do nothing
            }

            try {
                WebElement element2 = driver.findElement(By.xpath(ele2));
                isElement2Displayed = element2.isDisplayed();
                if (isElement2Displayed) {
                    sb.append("Element 2 is displayed.");
                    System.out.println("Element 2 is displayed.");
                    break;
                }
            } catch (NoSuchElementException e) {
                // Element not found, do nothing
            }

            try {
                WebElement element3 = driver.findElement(By.xpath(ele3));
                isElement3Displayed = element3.isDisplayed();
                if (isElement3Displayed) {
                    sb.append("Element 3 is displayed.");
                    System.out.println("Element 3 is displayed.");
                    break;
                }
            } catch (NoSuchElementException e) {
                // Element not found, do nothing
            }

            count++;
            Thread.sleep(1000); // Wait for 1 second before checking again

        }

        if (count == Integer.valueOf(WhileCount)) {
            sb.append("None of the 3 elements were displayed within 20 iterations.");
            result.setStatus(ResultConstants.FAILURE);
            result.setMessage("No Elements are displayed");
            System.out.println("None of the 3 elements were displayed within 20 iterations.");
        }

        result.setStatus(ResultConstants.SUCCESS);
        result.setMessage("Successfully Executed"+sb);
        return result;
    }
}