package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import basePkg.BaseTest;
import pageObjects.HomePage;
import utilities.Person;
import utilities.SeleniumActions;

public class Assessment extends BaseTest{

	private WebDriver driver=null;
	@Test
	public void enterDataIntoTheTable()
	{
		driver = getDriverInstance();
		HomePage homePage = new HomePage(driver);
//		clicking table data arrow
		homePage.getTableData().click();
		
//		waiting for input text box to become visible
		SeleniumActions actions = new SeleniumActions(driver);
		actions.waitForElementToBeVisible(homePage.getInputTextBox());
		
//		clearing input text box and entering json data into it
		homePage.getInputTextBox().clear();
		homePage.getInputTextBox().sendKeys(getJsonData());
		
//		clicking refresh table button
		homePage.getRefreshTableButton().click();
		
		List<String> rowsData = homePage.getDynamicTableRows().stream().map(el -> el.getText()).toList();
        
		try
        {
			ObjectMapper objMapper = new ObjectMapper();
			List<Person> persons =objMapper.readValue(new File(System.getProperty("user.dir") + "/src/main/java/pkg/data.json"), new TypeReference<List<Person>>(){});
			
			List<WebElement> rows = homePage.getDynamicTableRows();
            for(int i=1;i<rows.size();i++)
            {
            	String rowData=rowsData.get(i);
            	String actualName = rowData.split(" ")[0];
            	String actualAge = rowData.split(" ")[1];
            	String actualGender = rowData.split(" ")[2];
            	
            	String expectedName = persons.get(i-1).getName();
            	String expectedAge = String.valueOf(persons.get(i-1).getAge());
            	String expectedGender = persons.get(i-1).getGender();
            	
//              verifying data pushed into the dynamic table is same as data present in json file
            	Assert.assertEquals(actualName, expectedName, "Name in json data is different than the name displaying in UI");
            	Assert.assertEquals(actualAge, expectedAge, "Age in json data is different than the age displaying in UI");
            	Assert.assertEquals(actualGender, expectedGender, "Gender in json data is different than the gender displaying in UI");
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public String getJsonData()
	{
//		retrieving json data from json file as a string
		String jsonData="";
		try {
			jsonData= new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/main/java/pkg/data.json")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonData;
	}
}
