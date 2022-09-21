
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

import io.appium.java_client.android.AndroidDriver;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@SuppressWarnings("all")
public class FetchDataMulti extends TestsigmaCustomFunctions {
//	TestStepResult result = new TestStepResult();
//	AndroidDriver localDriver = (AndroidDriver) driver;
	StringBuffer br = new StringBuffer();

	@CustomTestStep
  @SuppressWarnings("unchecked")
	public TestStepResult ScanOrderItemsUsingBarcode(String accessToken, String orderNumber) {
		TestStepResult result = new TestStepResult();
		AndroidDriver localDriver = (AndroidDriver) driver;

		try {
			String runTimeOrder = getRuntimeData(orderNumber);
			String token = getRuntimeData(accessToken);
//			result.setMessage("<br> OrderNumber " + runTimeOrder + "\n");
			br.append("<br> OrderNumber " + runTimeOrder + "<br>");

			String APIurl = "https://api.zs-uat.fairprice.com.sg/order-service/order/" + runTimeOrder;

			OkHttpClient client = new OkHttpClient().newBuilder().build();
			MediaType mediaType = MediaType.parse("text/plain");

			// RequestBody body = RequestBody.create(mediaType, "");
			Request request = new Request.Builder().url(APIurl).method("GET", null)
					.addHeader("Authorization", "Bearer " + token + "").build();

//			result.setMessage("<br> " + APIurl);
			br.append("<br> " + APIurl);
			Response response = client.newCall(request).execute();
			String responseBody = response.body().string();

			Configuration conf = Configuration.defaultConfiguration();
			conf.addOptions(Option.SUPPRESS_EXCEPTIONS);

			List<String> itemNames = JsonPath.using(conf).parse(responseBody).read("$.data.order.items[*].name");
			System.out.println("itemName 0 - " + itemNames.get(0));
			List<String> itemBarcodes = JsonPath.using(conf).parse(responseBody)
					.read("$.data.order.items[*].barcodes[0]");
			System.out.println("itemBarcode 0 - " + itemBarcodes.get(0));

			List<Double> itemCount = JsonPath.using(conf).parse(responseBody)
					.read("$.data.order.items[*].orderDetails.orderedQuantity");

			Map<String, String> myMap = new HashMap<String, String>();
			for (int i = 0; i < itemNames.size(); i++) {
				myMap.put(itemNames.get(i), itemBarcodes.get(i) + "_" + itemCount.get(i));
			}

//			result.setMessage("<br> Order items fetched from API response - " + myMap.toString());
			br.append("<br> Order items fetched from API response - " + myMap.toString());
			// if response is empty, display appropriate message
			if (responseBody.isEmpty()) {
				throw new IOException(
						"Unexpected response!!!\n Code - " + response.code() + "\n Message - " + response.message());
			}

			ScanItemsUsingBarcodeNumber(myMap);

//			result.setMessage("<br> Order items fetched from API response - " + myMap.toString());
			result.setStatus(ResultConstants.SUCCESS);
		}
	 catch(Exception e) {		 
		result.setStatus(ResultConstants.FAILURE);
		br.append(ExceptionUtils.getStackTrace(e));
	}
		result.setMessage(br.toString());
		return result;
	}

	// Scan items present inside the order by entering the respective bar code
  @SuppressWarnings("unchecked")
	private void ScanItemsUsingBarcodeNumber(final Map<String, String> itemCodeMap) throws Exception {

		String itemRowLocator = "//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,'rv_products_list')]//android.widget.TextView[contains(@resource-id,'tv_product_name')][contains(@text,'%s')]";
		By enterBarcodeLabelLocator = By.xpath("//android.widget.Button[contains(@text,'Enter Item Barcode')]");
		By inputBarcodeLabelText = By.xpath("//android.widget.EditText[contains(@resource-id,'etInput')]");
//		By permissionOneTime = By.xpath("//android.widget.Button[contains(@resource-id,'permission_allow_one_time_button')]");
		By permissionDialog = By.xpath("//android.widget.LinearLayout[contains(@resource-id,'grant_dialog')]");
		By permissionOneTime = By.xpath("//*[contains(lower-case(@text),'while using the app'])");
		By barcodeSubmitBtn = By.xpath("//android.widget.Button[contains(@text,'Submit')]");

		TestStepResult result = new TestStepResult();
		AndroidDriver dr = (AndroidDriver) driver;
//		JavascriptExecutor executor = (JavascriptExecutor) dr;
		WebDriverWait wait = new WebDriverWait(dr, 60);
		int itr = 1;

		try {
			// iterate through and scan the item present in the order
			for (String key : itemCodeMap.keySet()) {
				WebElement itemRow = dr.findElement(By.xpath(String.format(itemRowLocator, key)));
				itemRow.click();

//				WebElement loadingIcon = dr.findElement(By.className("android.widget.ProgressBar"));
//				wait.until(ExpectedConditions.invisibilityOf(loadingIcon));

				Thread.sleep(3000);

				String[] splitArr = itemCodeMap.get(key).split("_"); // split the values to get barcode and itemcount
				String barcode = splitArr[0];

//				int itemCount = Integer.valueOf(splitArr[1]);
				int itemNum = (int) Double.parseDouble(splitArr[1]);

//				System.out.println("itemCount -- " + itemCount);
//				result.setMessage("Total Items - " + itemNum);
				br.append("Total Items - " + itemNum);

				// handle camera permission dialog
				List<WebElement> permissionPop = dr.findElements(permissionDialog);
				if (!permissionPop.isEmpty()) {
//				if (permissionPop.isDisplayed()) {
					br.append("Found element -- " + permissionPop);
					WebElement allow = permissionPop.get(0).findElement(permissionOneTime);
					allow.click();
//					executor.executeScript("arguments[0].click();", allow);
				}
//				int itemCount = 3;

				// iterate through the total count of a single product
				while (itr <= itemNum) {
//					Thread.sleep(1000);
//					wait.until(ExpectedConditions.elementToBeClickable(enterBarcodeLabelLocator));
//					dr.findElement(enterBarcodeLabelLocator).click();
//
//					Thread.sleep(1000);
//					wait.until(ExpectedConditions.visibilityOfElementLocated(inputBarcodeLabelText));
//					dr.findElement(inputBarcodeLabelText).sendKeys(barcode);
//
//					Thread.sleep(1000);
//					wait.until(ExpectedConditions.elementToBeClickable(barcodeSubmitBtn));
//					dr.findElement(barcodeSubmitBtn).click();
				}
				itr++;
			}
		} catch (Exception we) {
			result.setMessage("<br> Caught expection --- " + we.getMessage());
			br.append(ExceptionUtils.getStackTrace(we));
			throw we;
		}
	}

}