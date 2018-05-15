package restapiamittest;

import java.io.IOException;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.client.RestClient;
import com.qa.testBase.TestBase;

public class TestRestClient extends TestBase {

	String url;
	String serviceUrl;
	RestClient rs;
	CloseableHttpResponse closeableHttprepsonse;	
	JSONObject root;
	JSONArray dataArray;
	@BeforeTest
	public void setUp() throws ClientProtocolException, IOException
	{
		url=prop.getProperty("URL");
		serviceUrl= prop.getProperty("serviceUrl");
		url = url+serviceUrl;
		rs = new RestClient();
		System.out.println(url);
		closeableHttprepsonse=rs.Get(url);
		String responseString = EntityUtils.toString(closeableHttprepsonse.getEntity(),"UTF-8");
		System.out.println("Response String from API ==>"+responseString);
		//Set the root 
		root = new JSONObject(responseString);
		//Set the Array head using JsonArray
		dataArray = root.getJSONArray("data");
	}

	@Test(priority=1)
	public void getTestStatusCode() throws ClientProtocolException, IOException
	{

		int statusCode = closeableHttprepsonse.getStatusLine().getStatusCode();

		Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200);

	}

	@Test(priority=2)
	public void getTestDataForFirstElement() throws ParseException, IOException
	{

		//Get the first element:
		JSONObject firstElement = dataArray.getJSONObject(0);
		int id = firstElement.getInt("id"); 
		System.out.println("value of id is-->"+ id);
		Assert.assertEquals(id, 1);
		String firstName=firstElement.getString("first_name");
		Assert.assertEquals(firstName, "George");
		String lastName = firstElement.getString("last_name");
		Assert.assertEquals(lastName, "Bluth");


	}

	@Test(priority=3)
	public void getTestDataForSecondElement() throws ParseException, IOException
	{
		//Get the second Element
		JSONObject secondElement = dataArray.getJSONObject(1);
		int id = secondElement.getInt("id"); 
		System.out.println("value of id is-->"+ id);
		Assert.assertEquals(id, 2);
		String firstName=secondElement.getString("first_name");
		Assert.assertEquals(firstName, "Janet");
		String lastName = secondElement.getString("last_name");
		Assert.assertEquals(lastName, "Weaver");


	}


}
