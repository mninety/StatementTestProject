package init;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.TestData;

public class TestInitialization {
	
	public WebDriver driver;
	

	@BeforeTest
	public void setUp() 
	{
		try {
			TestData testdata = new TestData();
			String browserName = testdata.properties.getProperty("browserName");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		else if(browserName.equalsIgnoreCase("headless")) {
			ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--disable-features=VizDisplayCompositor");
			chromeOptions.addArguments("--window-size=1920,1080");
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--always-authorize-plugins");
		    chromeOptions.addArguments("--allow-running-insecure-content");
		    chromeOptions.addArguments("--disable-extensions"); // disabling extensions
		    chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
		    chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		    chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
			driver = new ChromeDriver(chromeOptions);
			
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Here we are quitting the driver
	@AfterTest
	public void afterTest() {
		   
		   try {
			Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   driver.quit();
	}
}
