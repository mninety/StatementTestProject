package APIController;

import java.io.IOException;
import java.util.Date;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.TestData;

public class InsertStatement {

	public InsertStatement() {
		
	}
	
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	
	public void InsertStatementAPI(String userId, double amount, Long date) throws IOException {

		testdata = new TestData();
		
		String json="{\r\n" + 
				"    \"statement\": {\r\n" + 
				"        \"account_id\": \""+userId+"\",\r\n" + 
				"        \"amount\": \""+amount+"\",\r\n" + 
				"        \"currency\": \"EUR\",\r\n" + 
				"        \"date\": "+date+"\r\n" + 
				"    }\r\n" + 
				"}";
		RestAssured.baseURI = testdata.properties.getProperty("baseURL");
		RestAssured.basePath = "";
		
		jsonresponse = new JsonPath(RestAssured.given().
				body(json).log().all().when().post().asString());

	}
	
	
}
