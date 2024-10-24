package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private WebDriver driver=null;
	
	private By tableData = By.cssSelector("#tablehere+details>summary");
	private By inputTextBox = By.id("jsondata");
	private By refreshTableButton = By.id("refreshtable");
	private By dynamicTableRows = By.xpath("//table[@id='dynamictable']/tr");
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebElement getTableData()
	{
		return driver.findElement(tableData);
	}
	
	public WebElement getInputTextBox()
	{
		return driver.findElement(inputTextBox);
	}
	
	public WebElement getRefreshTableButton()
	{
		return driver.findElement(refreshTableButton);
	}
	
	public List<WebElement> getDynamicTableRows()
	{
		return driver.findElements(dynamicTableRows);
	}
}

