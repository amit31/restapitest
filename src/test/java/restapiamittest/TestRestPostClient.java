package restapiamittest;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.testBase.TestBase;

public class TestRestPostClient extends TestBase {

	String url;
	String putUrl;
	String serviceUrl;
	String serviceUrlPUT;
	RestClient rs;
	CloseableHttpResponse closeableHttprepsonse;	
	JSONObject root;
	JSONArray dataArray;
	@BeforeTest
	public void setUp() throws ClientProtocolException, IOException
	{
		url=prop.getProperty("URL");
		serviceUrl= prop.getProperty("serviceUrl");
		serviceUrlPUT=prop.getProperty("serviceUrlPut");
		url = url+serviceUrl;
		putUrl=url+serviceUrlPUT;
		rs = new RestClient();
		System.out.println(url);
		
	}

	@Test(priority=1)
	public void postAPITest() throws ClientProtocolException, IOException
	{
       
		HashMap <String,String> headerMap = new HashMap <String,String>();
		headerMap.put("Content-Type","application/json");
		
		// Marshalling --> Jackson API 
		
		ObjectMapper ob = new ObjectMapper();
		Users users = new Users("Sumit", "Leader");
		
		//This step is just to write the data
		ob.writeValue(new File ("/Users/amit_sharma1/eclipse-workspace/restapiamittest/src/main/java/com/qa/data/Users.json"), users);
		// convert user java object to JSON--> Marshalling
		//Java object to JSON String 
		
		String usersJsonString = ob.writeValueAsString(users);
		
		System.out.println(usersJsonString);
		
		closeableHttprepsonse  = rs.post(url, usersJsonString, headerMap);
		
		int statusCode = closeableHttprepsonse.getStatusLine().getStatusCode();
        
		Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_201);
      
		
		// Json to String 
		
		String responseString =EntityUtils.toString(closeableHttprepsonse.getEntity(), "UTF-8");
		
		
		
		//Json to Java --->Unmarshalling
		Users userobj=ob.readValue(responseString,Users.class);
		System.out.println(userobj);
		
		Assert.assertTrue((userobj.getName()).equals(users.getName()));
		Assert.assertTrue((userobj.getJob()).equals(users.getJob()));
		
		
	}

	@Test(priority=2)
	public void putAPITest() throws ClientProtocolException, IOException
	{
       
		HashMap <String,String> headerMap = new HashMap <String,String>();
		headerMap.put("Content-Type","application/json");
		
		// Marshalling --> Jackson API 
		
		ObjectMapper ob = new ObjectMapper();
		Users users = new Users("Sumit", "Manager");
		
		//This step is just to write the data
		ob.writeValue(new File ("/Users/amit_sharma1/eclipse-workspace/restapiamittest/src/main/java/com/qa/data/Users.json"), users);
		// convert user java object to JSON--> Marshalling
		//Java object to JSON String 
		
		String usersJsonString = ob.writeValueAsString(users);
		
		System.out.println(usersJsonString);
		
		closeableHttprepsonse  = rs.Put(putUrl, usersJsonString, headerMap);
		
		int statusCode = closeableHttprepsonse.getStatusLine().getStatusCode();
        
		Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200);
      
		
		// Json to String 
		
		String responseString =EntityUtils.toString(closeableHttprepsonse.getEntity(), "UTF-8");
		
		
		
		//Json to Java --->Unmarshalling
		Users userobj=ob.readValue(responseString,Users.class);
		System.out.println(userobj);
		
		Assert.assertTrue((userobj.getName()).equals(users.getName()));
		Assert.assertTrue((userobj.getJob()).equals(users.getJob()));
		
		
	}
	
	

	


}
