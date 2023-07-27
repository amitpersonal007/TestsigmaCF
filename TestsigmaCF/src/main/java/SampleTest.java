import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;

import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;





// this function is used to generate category



public class SampleTest extends TestsigmaCustomFunctions {

    @SuppressWarnings("unchecked")
    @CustomTestStep

    public TestStepResult
    generate_random_kpi(String generated_data1,String generated_data2) throws ParseException {

        TestStepResult result = new TestStepResult();

        try{

            List<Map<String, String>> listOfMaps = new ArrayList<>();
            Map<String, String> map1 = new HashMap<>();
            map1.put("Clicks", "clicks");
            listOfMaps.add(map1);
            Map<String, String> map2 = new HashMap<>();
            map2.put("Installs", "installs");

            listOfMaps.add(map2);
            Map<String, String> map3 = new HashMap<>();

            map2.put("Registrations", "registration");

            listOfMaps.add(map2);



            Map<String, String> map4 = new HashMap<>();

            map2.put("Conversions", "acquisition");

            listOfMaps.add(map2);



            Random rand = new Random();

            Map<String, String> randomMap = listOfMaps.get(rand.nextInt(listOfMaps.size()));



            // Get the random key-value pair as separate variables

            Set<Entry<String, String>> entrySet = randomMap.entrySet();

            Entry<String, String>[] entryArray = entrySet.toArray(new Entry[0]);

            Entry<String, String> randomEntry = entryArray[rand.nextInt(entryArray.length)];

            String randomKey = randomEntry.getKey();

            String randomValue = randomEntry.getValue();

            System.out.println("Random Ui_kpi: " + randomKey);

            System.out.println("Random Api_kpi: " + randomValue);





            // this function is used to store data in test data profile

            setTestDataParameterValue(generated_data1,randomKey);

            setTestDataParameterValue(generated_data2,randomValue);

            result.setStatus(ResultConstants.SUCCESS);

            result.setMessage("Generated kpi "+randomKey+" & "+randomKey+" has been generated successfully");



        }

        catch(Exception e)

        {

            result.setStatus(ResultConstants.FAILURE);

        }

        return result;

    }

}