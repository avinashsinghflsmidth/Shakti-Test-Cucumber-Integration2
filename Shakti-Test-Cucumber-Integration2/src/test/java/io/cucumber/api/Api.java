package io.cucumber.api;

import java.util.Properties;

import io.cucumber.util.FileUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/* 
 * class Api for calling API (POST,GET,PUT,DELETE)
 */
public class Api {

	static long tokenExpiresTime;
	static String token = null;
	static String connetionUrl;

	static {
		Properties properties = FileUtil.loadProperties("configuration.properties");
		setConnectionUrl(properties);
	}

	public static void setConnectionUrl(Properties properties) {
		connetionUrl = properties.getProperty("connection_api_url");
	}

	/*
	 * * The postAPICallNew() method for calling POST Api..
	 */  
	public static String postAPICallNew(String postApiUrl, String requestData) {
		final String mainURLString  = connetionUrl+postApiUrl;
		System.out.println("mainURLString :"+mainURLString);
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Accept", "application/json");
		httpRequest.header("Content-Type", "application/json; charset=UTF-8");
		httpRequest.header("Authorization", "Bearer " +getToken());
		httpRequest.body(requestData);
		Response response = httpRequest.post(mainURLString);
		int statusCode = response.getStatusCode();
		//Validate Response Code
		System.out.println("Get Response Code in postAPICallNew() ===>" + statusCode);
		if (statusCode == 200 || statusCode == 201) {
			String body = response.getBody().asString() ;
			return body;
		}else {
			System.out.println("error code is " + statusCode);
		}
		return "";
	}

	/*
	 * The getAPICallNew() method for calling GET Api..
	 */
	public static String getAPICallNew(String getApiUrl) {
		final String mainURLString = connetionUrl+getApiUrl;
		System.out.println("mainURLString :"+mainURLString);
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Authorization", "Bearer " +getToken());
		Response response = httpRequest.get(mainURLString);
		int statusCode = response.getStatusCode();
		//Validate Response Code
		System.out.println("Get Response Code in getAPICallNew() ===>" + statusCode);
		if (statusCode == 200 || statusCode == 201) {
			String body = response.getBody().asString() ;
			return body;
		}else {
			System.out.println("error code is " + statusCode);
		}
		return "";
	}


	/*
	 * The putAPICallNew() method for PUT Api.. 
	 */
	public static String putAPICallNew(String updateByIdApiUrl, String requestData) {
		final String mainURLString  = connetionUrl+updateByIdApiUrl;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Accept", "application/json");
		httpRequest.header("Content-Type", "application/json; charset=UTF-8");
		httpRequest.header("Authorization", "Bearer " +getToken());
		httpRequest.body(requestData);
		Response response = httpRequest.put(mainURLString);
		int statusCode = response.getStatusCode();
		//Validate Response Code
		System.out.println("Get Response Code in putAPICallNew() ===>" + statusCode);
		if (statusCode == 200 || statusCode == 201) {
			String body = response.getBody().asString() ;
			return body;
		}else {
			System.out.println("error code is " + statusCode);
		}
		return "";
	}

	/*
	 * This deleteAPICallNew() method for DELETE..
	 */
	public static String deleteAPICallNew(String deleteByIdApiUrl) {
		final String mainURLString = connetionUrl+deleteByIdApiUrl;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Accept", "application/json");
		httpRequest.header("Content-Type", "application/json; charset=UTF-8");
		httpRequest.header("Authorization", "Bearer " +getToken());
		Response response = httpRequest.delete(mainURLString);
		int statusCode = response.getStatusCode();
		// Validate Response Code
		System.out.println("Get Response Code in deleteAPICallNew() ===>" + statusCode);
		if (statusCode == 200 || statusCode == 202 || statusCode == 201 || statusCode == 204) {
			String body = response.getBody().asString() ;
			return body;
		}else {
			throw new AssertionError("error code is " + statusCode);
		}
	}

	/*
	 * * The getToken() method returns token string..
	 */  
	private static String getToken() {   
		final String authUrl = "http://apitokengeneration.azurewebsites.net/Service1.svc/GenerateToken";
		if(token != null && (convertMiliSecondToSeconds(System.currentTimeMillis())) < tokenExpiresTime) {
			System.out.println("time has not expired yet for token::");
			return token;
		}else {
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.get(authUrl);
		    token = response.getBody().asString().replaceAll("\"", "");
			if(!token.isEmpty() || token != null) {
				tokenExpiresTime = convertMiliSecondToSeconds(System.currentTimeMillis())+3600;
				System.out.println("new Token ===>"+token);
				return token;
			}
		}
		return token;
	}

	private static long convertMiliSecondToSeconds(long currentTimeMillis) {
		    long totalSecs = currentTimeMillis/1000;
			return totalSecs;
	}

}