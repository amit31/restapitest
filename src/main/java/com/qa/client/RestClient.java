package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//GET Method
	public CloseableHttpResponse Get(String url) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient =HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse closeableHttprepsonse =httpClient.execute(httpget);// hit get URL
		int statusCode = closeableHttprepsonse.getStatusLine().getStatusCode();
	
		return closeableHttprepsonse;
	}
	
	// POST Method
	
	public CloseableHttpResponse post(String url,String entityString, HashMap<String,String> headerMap) throws ClientProtocolException, IOException
	{
		//1.Initialise HttpClient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//4. Pass URL
		HttpPost httppost = new HttpPost(url);
		//2.Define PayLoad
		httppost.setEntity(new StringEntity(entityString));
		//3. Add Headers 
		for(Map.Entry<String, String>entry:headerMap.entrySet())
		{
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse httpResponse = httpClient.execute(httppost);
		return httpResponse;
	}
	
	
	//PUT Method 
	
	public CloseableHttpResponse Put(String url,String entityString, HashMap<String,String> headerMap) throws ClientProtocolException, IOException
	{
		//1.Initialise HttpClient
				CloseableHttpClient httpClient = HttpClients.createDefault();
				//4. Pass URL
				HttpPut httpput = new HttpPut(url);
				httpput.setEntity(new StringEntity(entityString));
				for(Map.Entry<String, String>entry:headerMap.entrySet())
				{
					httpput.addHeader(entry.getKey(),entry.getValue());
				}
				CloseableHttpResponse httpResponse = httpClient.execute(httpput);
				return httpResponse;
	}
}
