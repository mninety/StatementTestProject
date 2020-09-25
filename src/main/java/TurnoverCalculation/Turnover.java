package TurnoverCalculation;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import org.decimal4j.util.DoubleRounder;

import utils.Helper;

public class Turnover {
	
	//public DecimalFormat df2 = new DecimalFormat("#.##");
	
	public double CalculateTurnover(String[][] transactions) throws ParseException
	{
		Helper help = new Helper();
		double sum=0.00;
		//String first=transactions[0][1];
		String last=transactions[transactions.length-1][1];
		//String minDate=help.minDateFinder(transactions);
		String maxDate=help.maxDateFinder(transactions);

		for(int i=0;i<transactions.length;i++)
		{
			transactions[i][0]=transactions[i][0].replaceAll("EUR|\\s", "");
		}
		
		if(Long.parseLong(last)==Long.parseLong(maxDate))
		{
			
			System.out.println("Turnover recalculation is not required");
			String[][] sortingTransactions=help.sortingAsc(transactions);
			for(int i=0;i<(sortingTransactions.length-1);i++)
			{
				sum=sum+Double.parseDouble(sortingTransactions[i][0]);
			}
		}
		else
		{
			System.out.println("Turnover recalculation is required");
			String[][] sortingTransactions=help.sortingAsc(transactions);
			for(int i=0;i<(sortingTransactions.length-1);i++)
			{
				sum=sum+Double.parseDouble(sortingTransactions[i][0]);
			}
		}
		
		
		
		//System.out.println("Turnover: "+sum);
		
		return DoubleRounder.round(sum,2);
		
	}

	public double AfterBalanceCalculation(String[][] transactions) throws ParseException
	{
		Helper help = new Helper();
		double sum=0.00;
		//String first=transactions[0][1];
		String last=transactions[transactions.length-1][1];
		//String minDate=help.minDateFinder(transactions);
		String maxDate=help.maxDateFinder(transactions);

		for(int i=0;i<transactions.length;i++)
		{
			transactions[i][0]=transactions[i][0].replaceAll("EUR|\\s", "");
		}
		
		if(Long.parseLong(last)==Long.parseLong(maxDate))
		{
			
			System.out.println("Turnover recalculation is not required");
			String[][] sortingTransactions=help.sortingAsc(transactions);
			for(int i=0;i<sortingTransactions.length;i++)
			{
				sum=sum+Double.parseDouble(sortingTransactions[i][0]);
			}
		}
		else
		{
			System.out.println("Turnover recalculation is required");
			String[][] sortingTransactions=help.sortingAsc(transactions);
			for(int i=0;i<sortingTransactions.length;i++)
			{
				sum=sum+Double.parseDouble(sortingTransactions[i][0]);
			}
		}
		
		
		
		//System.out.println("Turnover: "+sum);
		
		return DoubleRounder.round(sum,2);
		
	}

	
}
