package StatementTest;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.Test;

import APIController.InsertStatement;
import utils.Helper;
import utils.TestData;

public class StatementAPI {
	TestData testdata;
	
	@Test(description = "Insert Statement Data with current date")
	public void StatementAPITest_withCurrentDate() throws IOException
	{
		testdata= new TestData();
		String AccountID=testdata.properties.getProperty("insertedAccountID");
		System.out.println("Account: "+AccountID);
		String Amount=testdata.properties.getProperty("amountwhileInserting");
		InsertStatement insertdata= new InsertStatement();
		Helper help = new Helper();
		insertdata.InsertStatementAPI(AccountID, Double.parseDouble(Amount), help.getUnixTime()); //Please check accountID and Amount before run the api
	}

	@Test(description = "Insert Statement Data with previous date")
	public void StatementAPITest_withPreviousDate() throws IOException, NumberFormatException, ParseException
	{
		testdata= new TestData();
		String AccountID=testdata.properties.getProperty("insertedAccountID");
		System.out.println("Account: "+AccountID);
		String Amount=testdata.properties.getProperty("amountwhileInserting");
		String previousDate=testdata.properties.getProperty("previousDate");
		InsertStatement insertdata= new InsertStatement();
		Helper help = new Helper();
		insertdata.InsertStatementAPI(AccountID, Double.parseDouble(Amount), Long.parseLong(help.HRDatetoUnixTimeConversion(previousDate))); //Please check accountID and Amount before run the api
	}

	
}
