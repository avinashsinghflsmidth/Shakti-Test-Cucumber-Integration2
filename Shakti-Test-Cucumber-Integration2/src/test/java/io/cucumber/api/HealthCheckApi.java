package io.cucumber.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.HealthCheckResponseGet;

/*
 * class HealthCheckApi having Api url and method details..
 */
public class HealthCheckApi {
	
	/*
	 * method getDepartment() getting dept by id...
	 */
	public HealthCheckResponseGet getAllHealthCheck() {
		String getStatusUrlString = "/api/v1/status/";
		try {
			String ApiResponse = Api.getAPICallNew(getStatusUrlString);
			System.out.println("ApiResponse ==>"+ApiResponse);
			HealthCheckResponseGet response = null; 
			if (!ApiResponse.isEmpty()) {
			  ObjectMapper mapper = new ObjectMapper();
			  response =  mapper.readValue(ApiResponse, HealthCheckResponseGet.class);
			  return response; 
			 }
			  else { 
				  System.out.println("response is empty ===>"+ApiResponse); 
				  return new HealthCheckResponseGet(); 
			  }
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new HealthCheckResponseGet();
	}

}
