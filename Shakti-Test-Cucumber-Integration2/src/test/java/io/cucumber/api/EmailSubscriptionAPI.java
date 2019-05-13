package io.cucumber.api;

import java.net.MalformedURLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.EmailSubscriptionGetResponse;
import io.cucumber.model.EmailSubscriptionResponse;

/*
 * class EmailSubscriptionAPI having Api url and method details..
 */
public class EmailSubscriptionAPI {

	/*
	 * method postEmail sending Api With payload data...
	 */
	public EmailSubscriptionResponse postEmail(String payload) {
		String emailSubscriptionUrlString = "/api/v1/email-subscriptions";
		try {
			String ApiResponse = Api.postAPICallNew(emailSubscriptionUrlString, payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				EmailSubscriptionResponse response = mapper.readValue(ApiResponse, EmailSubscriptionResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new EmailSubscriptionResponse();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new EmailSubscriptionResponse();
	} 

	/*
	 * method getAllEmail() getting all EmailSubscriptions...
	 */
	public EmailSubscriptionGetResponse getAllEmail() {
		final String getAllEmailUrlString = "/api/v1/email-subscriptions";
		try{
			String ApiResponse =  Api.getAPICallNew(getAllEmailUrlString);
			if(!ApiResponse.isEmpty()){
				ObjectMapper mapper = new ObjectMapper();
				EmailSubscriptionGetResponse response =  mapper.readValue(ApiResponse, EmailSubscriptionGetResponse.class);
				return response;
			}else {
				System.out.println("response is empty ===>"+ApiResponse);
				return null;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}


	/*
	 * method deleteEmail() deleting by id..
	 */
	public String deleteEmail(String id) {
		String deleteEmailSubscriptionByIdUrlString = "/api/v1/email-subscriptions/";
		try {
			String ApiResponse = Api.deleteAPICallNew(deleteEmailSubscriptionByIdUrlString+id);
			return ApiResponse;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}		

}

