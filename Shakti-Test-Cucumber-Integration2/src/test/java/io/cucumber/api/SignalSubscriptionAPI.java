package io.cucumber.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.SignalSubscriptionGetResponse;
import io.cucumber.model.SignalSubscriptionPostResponse;

/*
 * class SignalSubscriptionAPI having Api url and method details..
 */
public class SignalSubscriptionAPI {


	/*
	 * method postSignalSubcription sending Api With payload data...
	 */
	public SignalSubscriptionPostResponse postSignalSubcription(String payload){	
		final String postSignalSubcriptionUrlString = "/api/v1/signal-subscriptions";
		try {
			String apiResponse = Api.postAPICallNew(postSignalSubcriptionUrlString, payload);
			if (!apiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SignalSubscriptionPostResponse subResp = mapper.readValue(apiResponse, SignalSubscriptionPostResponse.class);
				return subResp;
			} else {
				System.out.println("response is empty ===>"+apiResponse);
				return new SignalSubscriptionPostResponse();
			}
		}  catch(Exception e){
			e.printStackTrace();
		}
		return new SignalSubscriptionPostResponse();
	}

	/*
	 * method getSignalSubcription() getting all dept...
	 */
	public SignalSubscriptionGetResponse getSignalSubcription(){		
		final String getSignalSubcriptionUrlString = "/api/v1/signal-subscriptions/?page=1&pageSize=100";
		try {
			String apiResponse = Api.getAPICallNew(getSignalSubcriptionUrlString);
			if (!apiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SignalSubscriptionGetResponse subResp = mapper.readValue(apiResponse, SignalSubscriptionGetResponse.class);
				return subResp;
			} else {
				System.out.println("response is empty ===>"+apiResponse);
				return new SignalSubscriptionGetResponse();
			}
		} catch(Exception e ){
			e.printStackTrace();
		}
		return new SignalSubscriptionGetResponse();
	}

	/*
	 * method deleteSignalSubscription() deleting subscription by id..
	 */
	public String deleteSignalSubscription(String signalSubscriptionId) {
		final String deleteSignalSubscriptionUrlString = "/api/v1/signal-subscriptions/";
		try {
			String resp = Api.deleteAPICallNew(deleteSignalSubscriptionUrlString+signalSubscriptionId);
			return resp;
		}  catch(Exception e ){
			e.printStackTrace();
			return "";
		}

	}	
}

