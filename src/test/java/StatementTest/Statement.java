package StatementTest;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageRepository.StatementPage;
import TurnoverCalculation.Turnover;
import init.TestInitialization;
import utils.Helper;
import utils.TestData;
import utils.Verify;

public class Statement extends TestInitialization {
	TestData testdata;
	
	
	public void StatementPageLoad() throws IOException {
		
		//StatementPage state = new StatementPage(driver);
		testdata = new TestData();
		String url = testdata.properties.getProperty("baseURL");
		driver.get(url);
	}

	@Test(description="Before balance is calculated for a specific user")
	public void BeforeBalanceCalculationforSpecificUser() throws IOException, ParseException {
		
		StatementPage state = new StatementPage(driver);
		Turnover turnover= new Turnover();
		Helper help= new Helper();
		StatementPageLoad();
		
		String AccountID=testdata.properties.getProperty("filteredAccountID"); //Please change before test run as your need
		state.accountIDInput.sendKeys(AccountID);
		state.searchButton.click();
		//Thread.sleep(2000);
		if(state.body.getText().contains("EUR"))
		{
			//System.out.println("Table data found");
			int totalList=state.table.size();
			//System.out.println("Total List: "+totalList);
			String[][] transactions = new String[totalList-1][2];
			//int totalamount=state.amountList.size();
			//System.out.println("Total Amount List: "+state.amountList.get(0).getText());
			for(int i=0;i<(totalList-1);i++)
			{
				transactions[i][0]=state.amountList.get(i).getText();
				transactions[i][1]=help.HRDatetoUnixTimeConversion(state.dateList.get(i).getText());
			}
			double expectedBalanceBefore=turnover.CalculateTurnover(transactions);
			String foundbalanceBefore=state.balanceBefore.getText().replaceAll("\\s", "");
			foundbalanceBefore=foundbalanceBefore.replaceAll("Balancebefore:|EUR", "");
			//System.out.println("Found Balance Before: "+foundbalanceBefore);
			Assert.assertEquals(Double.parseDouble(foundbalanceBefore),expectedBalanceBefore,"Actual balance before is not matched with expected balance before");
		}
		else
		{
			
			Assert.assertTrue(state.body.getText().contains("EUR"),"No Data Found");
		}
		
	}

	@Test(description="Before after is calculated for a specific user")
	public void AfterBalanceCalculationforSpecificUser() throws IOException, ParseException {
		
		StatementPage state = new StatementPage(driver);
		Turnover turnover= new Turnover();
		Helper help= new Helper();
		StatementPageLoad();
		
		String AccountID=testdata.properties.getProperty("filteredAccountID"); //Please change before test run as your need
		state.accountIDInput.sendKeys(AccountID);
		state.searchButton.click();
		//Thread.sleep(2000);
		if(state.body.getText().contains("EUR"))
		{
			//System.out.println("Table data found");
			int totalList=state.table.size();
			//System.out.println("Total List: "+totalList);
			String[][] transactions = new String[totalList-1][2];
			//int totalamount=state.amountList.size();
			//System.out.println("Total Amount List: "+state.amountList.get(0).getText());
			for(int i=0;i<(totalList-1);i++)
			{
				transactions[i][0]=state.amountList.get(i).getText();
				transactions[i][1]=help.HRDatetoUnixTimeConversion(state.dateList.get(i).getText());
			}
			double expectedBalanceAfter=turnover.AfterBalanceCalculation(transactions);
			String foundbalanceAfter=state.balanceAfter.getText().replaceAll("\\s|EUR", "");
			String[] afterArray=foundbalanceAfter.split("Balanceafter");
			Assert.assertEquals(Double.parseDouble(afterArray[1]),expectedBalanceAfter,"Actual balance after is not matched with expected balance after");
		}
		else
		{
			
			Assert.assertTrue(state.body.getText().contains("EUR"),"No Data Found");
		}
		
	}

	@Test(description="Statement filtered with date which exactly match with operation date")
	public void StatementFilteredwithDate_WhichExactlyMatchwithOperationDate() throws IOException, ParseException {
		
		
		StatementAPI api= new StatementAPI();
		api.StatementAPITest_withPreviousDate();
		StatementPage state = new StatementPage(driver);
		Turnover turnover= new Turnover();
		Helper help= new Helper();
		StatementPageLoad();
		
		String previousDate=testdata.properties.getProperty("previousDate");
		
		long fromunix=Long.parseLong(help.HRDatetoUnixTimeConversion(previousDate))-21600;
		String fromunixtoHR=help.unixTimetoHRDateConversion(String.valueOf(fromunix));
		
		state.fromDate.sendKeys(fromunixtoHR);
		state.toDate.sendKeys(fromunixtoHR);
		state.searchButton.click();
		//Thread.sleep(2000);
		
		Assert.assertTrue(state.body.getText().contains("EUR"),"No result found even though we have statement for this date range");

		
	}

	@Test(description="Statement filtered with date where operation date is in between that date range")
	public void StatementFilteredwithDate_WhereOperationDateisInBetweenThatDateRange() throws IOException, ParseException {
		
		
		StatementAPI api= new StatementAPI();
		api.StatementAPITest_withPreviousDate();
		StatementPage state = new StatementPage(driver);
		Turnover turnover= new Turnover();
		Helper help= new Helper();
		StatementPageLoad();
		
		String previousDate=testdata.properties.getProperty("previousDate");
		
		long fromunix=Long.parseLong(help.HRDatetoUnixTimeConversion(previousDate))-21601;
		long tounix=Long.parseLong(help.HRDatetoUnixTimeConversion(previousDate))-21599;
		String fromunixtoHR=help.unixTimetoHRDateConversion(String.valueOf(fromunix));
		String tounixtoHR=help.unixTimetoHRDateConversion(String.valueOf(tounix));
		
		state.fromDate.sendKeys(fromunixtoHR);
		state.toDate.sendKeys(tounixtoHR);
		state.searchButton.click();
		//Thread.sleep(2000);
		
		Assert.assertTrue(state.body.getText().contains("EUR"),"No result found even though we have statement for this date range");
		
	}

	@Test(description="Inputed date is not converted at GMT 00:00 that makes an issue no record found")
	public void StatementFilteredwithDate_ButnoRecordFound() throws IOException, ParseException {
		
		
		StatementAPI api= new StatementAPI();
		api.StatementAPITest_withPreviousDate();
		StatementPage state = new StatementPage(driver);
		Turnover turnover= new Turnover();
		Helper help= new Helper();
		StatementPageLoad();
		
		String previousDate=testdata.properties.getProperty("previousDate");
		
		long fromunix=Long.parseLong(help.HRDatetoUnixTimeConversion(previousDate))-1;
		long tounix=Long.parseLong(help.HRDatetoUnixTimeConversion(previousDate))+1;
		String fromunixtoHR=help.unixTimetoHRDateConversion(String.valueOf(fromunix));
		String tounixtoHR=help.unixTimetoHRDateConversion(String.valueOf(tounix));
		
		state.fromDate.sendKeys(fromunixtoHR);
		state.toDate.sendKeys(tounixtoHR);
		state.searchButton.click();
		//Thread.sleep(2000);
		
		Assert.assertTrue(state.body.getText().contains("EUR"),"No result found even though we have statement in between this date range");
		
	}
	
	
}
