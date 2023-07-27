import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class Ascending extends TestsigmaCustomFunctions {

    @CustomTestStep
    public TestStepResult clickonDownload(String Xpath , String action) {
        TestStepResult result= new TestStepResult();



            List<WebElement> elementList =driver.findElements(By.xpath(""+Xpath+""));
            //driver.findElement(By.xpath("")).sendKeys("");
            List<String> textList = new ArrayList<String>();
            for (WebElement element : elementList) {
                String textValue = element.getText();
                textList.add(textValue);
            }
            System.out.println(textList);

            boolean isAscending = true;
            double previousValue1 = Double.NEGATIVE_INFINITY;

            for (String value : textList) {
                double currentValue = Double.parseDouble(value.replace("K", ""));
                if (currentValue < previousValue1) {
                    isAscending = false;
                    break;
                }
                previousValue1 = currentValue;
            }

            if (isAscending) {
                result.setStatus(ResultConstants.SUCCESS);
                result.setMessage("The List is in ascending order "+textList);
                System.out.println("The list is in ascending order by its value.");
            } else {
                result.setStatus(ResultConstants.FAILURE);
                result.setMessage("The list is not in ascending order");
            }
        return result;


}}

