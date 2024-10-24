package basePkg;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;



public class BaseTest {
	
	private Properties prop =null;
	private FileInputStream fis = null;
	private WebDriver driver = null;
	
	@BeforeTest
	public void openApplication()
	{
		try
		{
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/pkg/gloabal.properties");
			prop = new Properties();
			prop.load(fis);
			
			String browser = prop.getProperty("browser");
			if(browser.equalsIgnoreCase("chrome"))
			{
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("edge"))
			{
				driver = new EdgeDriver();
			}
			else
			{
				throw new Exception("browser not found");
			}
			
			if(driver!=null)
			{
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
				
				driver.get(prop.getProperty("url"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void closeBrowser()
	{
		if(driver!=null)
		{
			driver.close();
		}
	}
	
	public WebDriver getDriverInstance()
	{
		return driver;
	}

}
