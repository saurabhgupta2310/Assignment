package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumActions {

	private WebDriver driver = null;
	public SeleniumActions(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void waitForElementToBeVisible(WebElement el)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(el));
	}
}
