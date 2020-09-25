package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Helper {

	
    public String getCurrentTime() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateobject = new SimpleDateFormat("ddMMyyyy-HHmmss");
        return dateobject.format(date);
    }
    public Long getUnixTime()
    {
    	Long curDate=System.currentTimeMillis() / 1000L;
    	return curDate;
    }
    
    public String HRDatetoUnixTimeConversion(String date) throws ParseException {
        long epoch = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime() / 1000;
        //System.out.println("Unix Time: "+epoch);
        return String.valueOf(epoch);
    }
    
    public String unixTimetoHRDateConversion(String date) throws ParseException {
    	
    	SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String unix= dateForm.format(new Date(Long.parseLong(date) * 1000L));
    	//System.out.println("HR Date: "+unix);
    	return unix;
    }
    
    public String maxDateFinder(String[][] date) {
    	
        long maxValue = Long.parseLong(date[0][1]); 
        for(int i=1;i < date.length;i++){ 
          if(Long.parseLong(date[i][1]) > maxValue){ 
             maxValue = Long.parseLong(date[i][1]); 
          } 
        } 
        
        return String.valueOf(maxValue);
    }
    
    public String minDateFinder(String[][] date) {

        long minValue = Long.parseLong(date[0][1]); 
        for(int i=1;i<date.length;i++){ 
          if(Long.parseLong(date[i][1]) < minValue){ 
            minValue = Long.parseLong(date[i][1]); 
          } 
        } 
        return String.valueOf(minValue);
    }
   
    public String[][] sortingAsc(String[][] date) {

    	//int newIndex[]= new int[date.length];
        for (int i = 0; i < date.length; i++) 
        {
            for (int j = i + 1; j < date.length; j++) 
            {
                if (Long.parseLong(date[i][1]) > Long.parseLong(date[j][1])) 
                {
                	
                    String temp = date[i][1];
                    String temp1 = date[i][0];
                    date[i][1] = date[j][1];
                    date[i][0] = date[j][0];
                    date[j][1] = temp;
                    date[j][0]=temp1;
                    
                	
                	
                }
            }
        } 
        return date;
    }
    
}
