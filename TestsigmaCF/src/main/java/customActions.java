
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.testsigma.customfunc.common.CustomTestStep;
import com.testsigma.customfunc.common.TestsigmaCustomFunctions;
import com.testsigma.customfunc.result.ResultConstants;
import com.testsigma.customfunc.result.TestStepResult;
import com.testsigma.testengine.exceptions.TestEngineException;

public class customActions extends TestsigmaCustomFunctions {
	TestStepResult result=new TestStepResult();
	
	
	@CustomTestStep
    public TestStepResult getlistfromcheckbox(String runtimename) throws TestEngineException, InterruptedException{
	   try {
		   
		   HashSet<String> s =new HashSet<String>();
			 Thread.sleep(5000);
		List<WebElement> listings = driver.findElements(By.xpath("//tr/td[5]/input[@checked=\"checked\"]//ancestor::tr/td[6]/input[contains(@name,\"ServiceCoreFieldsViewModelList\")]"));
		  for (WebElement checkedItems : listings) {
			   s.add(checkedItems.getAttribute("value")) ;
	      } 
		  TreeSet<String> treeSet = new TreeSet<String>(s);
		   

		  
		setRuntimeData(runtimename, treeSet.toString());
		result.setStatus(ResultConstants.SUCCESS);
		result.setMessage("Count of data is   "+treeSet.size()+"    Texts stored in the runtime variable "+runtimename+"  List is "+treeSet);
		   
	   }
	   catch(Exception e) {
		   
		   result.setStatus(ResultConstants.FAILURE);
		   result.setMessage(e.getMessage()+e.getCause());
		 
	   }
    	return result;
		
	}
	
	@CustomTestStep
	public TestStepResult getlistafterscroll(String runtimename) throws TestEngineException, InterruptedException{
		
		try {
			
			// driver.get("https://app.thelayer.com/SalesOrderCapture/Index/1086?baseCategoryId=147&productTypeId=2&page=1&pageSize=2500");
			 HashSet<String> scrollList =new HashSet<String>();
			 
			  ((JavascriptExecutor) driver).executeScript("document.querySelector(\"#divHandsOnTable_SalesOrderDataCapture > div.ht_master.handsontable.innerBorderLeft > div\").scrollLeft=1000");
			  Thread.sleep(5000);
			  List<WebElement> listings2 = driver.findElements(By.xpath("//tr/th/div/span[@class=\"colHeader columnSorting\"][last()]")); 
			   for (WebElement webElement2 : listings2) {
				   scrollList.add(webElement2.getText());
		        }
			   
			   ((JavascriptExecutor) driver).executeScript("document.querySelector(\"#divHandsOnTable_SalesOrderDataCapture > div.ht_master.handsontable.innerBorderLeft > div\").scrollLeft=2500");  
			   Thread.sleep(5000);
			   List<WebElement> listings3 = driver.findElements(By.xpath("//tr/th/div/span[@class=\"colHeader columnSorting\"][last()]"));
			   for (WebElement webElement3 : listings3) {
				   scrollList.add(webElement3.getText());
		        }
			   
			   ((JavascriptExecutor) driver).executeScript("document.querySelector(\"#divHandsOnTable_SalesOrderDataCapture > div.ht_master.handsontable.innerBorderLeft > div\").scrollLeft=4000");
				  Thread.sleep(5000);
				   List<WebElement> listings4 = driver.findElements(By.xpath("//tr/th/div/span[@class=\"colHeader columnSorting\"][last()]")); 
				  
				   for (WebElement webElement4 : listings4) {
					   scrollList.add(webElement4.getText());
			           
			        }
				   
				   
				   ((JavascriptExecutor) driver).executeScript("document.querySelector(\"#divHandsOnTable_SalesOrderDataCapture > div.ht_master.handsontable.innerBorderLeft > div\").scrollLeft=5500");
					  Thread.sleep(5000);
					   List<WebElement> listings5 = driver.findElements(By.xpath("//tr/th/div/span[@class=\"colHeader columnSorting\"][last()]")); 
					  
					   for (WebElement webElement5 : listings5) {
						   scrollList.add(webElement5.getText());
				           
				        }
					   
					   ((JavascriptExecutor) driver).executeScript("document.querySelector(\"#divHandsOnTable_SalesOrderDataCapture > div.ht_master.handsontable.innerBorderLeft > div\").scrollLeft=7000");
						  Thread.sleep(5000);
						   List<WebElement> listings6 = driver.findElements(By.xpath("//tr/th/div/span[@class=\"colHeader columnSorting\"][last()]")); 
						   Thread.sleep(5000);
						   for (WebElement webElement6 : listings6) {
							   scrollList.add(webElement6.getText());
					           
					        }
						   
						   ((JavascriptExecutor) driver).executeScript("document.querySelector(\"#divHandsOnTable_SalesOrderDataCapture > div.ht_master.handsontable.innerBorderLeft > div\").scrollLeft=7200");
							  Thread.sleep(5000);
							   List<WebElement> listings7 = driver.findElements(By.xpath("//tr/th/div/span[@class=\"colHeader columnSorting\"]")); 
							   Thread.sleep(5000);
							   for (WebElement webElement7 : listings7) {
								   scrollList.add(webElement7.getText());
						           
						        }
							   
							   TreeSet<String> treeSet2 = new TreeSet<String>(scrollList);
							   setRuntimeData(runtimename, treeSet2.toString());
							   result.setStatus(ResultConstants.SUCCESS);
							   result.setMessage("Successfully Scrolled. Count of data stored is "+treeSet2.size()+ "List of data are  "+treeSet2);
			
		}catch(Exception e) {
			result.setStatus(ResultConstants.FAILURE);
			result.setMessage("Failed to scroll or store data"+e.getMessage()+e.getCause());
			
		}
		
						   
						   
	     	return result;
			
		}
	
	@CustomTestStep
    public TestStepResult comparetwodata(String nameofvar1,String nameofvar2) throws TestEngineException, InterruptedException{
	   try {
		   
		  String[] listpostscroll = getRuntimeData(nameofvar1).split(",");
		  
		  String[] listofcheckbox = getRuntimeData(nameofvar2).split(",");
		  
		  List<String> poi1 = Arrays.asList(listpostscroll);  
		  List<String> poi2 = Arrays.asList(listofcheckbox);  
		  
		  TreeSet <String>treeoflistpostscroll = new TreeSet<>(poi1);
		  TreeSet <String>treeofcheckbox= new TreeSet<>(poi2);
		  
		  if(poi1.contains(poi2)) {
			  
			  result.setStatus(ResultConstants.SUCCESS);
			  result.setMessage("Both the data are equal");
		  }
		  
		  else {
			  
			  treeoflistpostscroll.remove(treeoflistpostscroll.first()); 
			  treeoflistpostscroll.removeAll(treeofcheckbox);
			  result.setStatus(ResultConstants.FAILURE);
			  result.setMessage("Data are mismactched. Count of the data with checkbox is "+poi2.size()+"  count of the columns is  "+poi1.size()+"  The mismatch list is "+ treeoflistpostscroll);
			  
		  }
		   
	   }
	   
	   catch(Exception e) {
		   
		   result.setStatus(ResultConstants.FAILURE);
		   result.setMessage(e.getMessage()+e.getCause());
		 
	   }
    	return result;
		
	}
	
}