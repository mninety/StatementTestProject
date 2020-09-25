package PageRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StatementPage {
	WebDriver driver;
	//Constructor
		public StatementPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(name = "account_id")	
		public WebElement accountIDInput;	
		
		@FindBy(name = "from_date")	
		public WebElement fromDate;	
		
		@FindBy(name = "to_date")	
		public WebElement toDate;	
		
		@FindBy(xpath = "//button")	
		public WebElement searchButton;
		
		@FindBy(id = "statement_row_0")	
		public WebElement tableData;
		
		@FindBy(xpath = "//body")	
		public WebElement body;
		
		
		@FindBy(xpath = "//tr")	
		public List<WebElement> table;
		
		@FindBy(id = "money")	
		public List<WebElement> amountList;
		
		@FindBy(id = "date")	
		public List<WebElement> dateList;
		
		@FindBy(xpath = "//body/div/div[@class='container']")	
		public WebElement balanceBefore;
		
		@FindBy(xpath = "//div[@class='col-sm']")	
		public WebElement balanceAfter;
		
}
