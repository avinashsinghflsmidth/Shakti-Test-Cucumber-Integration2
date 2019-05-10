package io.cucumber.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.SignalSubscriptionGetResponse;
import io.cucumber.model.SignalSubscriptionPostResponse;

public class SignalSubscriptionAPI {

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
		}  catch(Exception e )
		{
			e.printStackTrace();
		}

		return new SignalSubscriptionPostResponse();


	}

	public SignalSubscriptionGetResponse getSignalSubcription(){		
		final String getSignalSubcriptionUrlString = "/api/v1/signal-subscriptions/?";
		try {
			String resp = Api.getAPICallNew(getSignalSubcriptionUrlString);
			if (!resp.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SignalSubscriptionGetResponse subResp = mapper.readValue(resp, SignalSubscriptionGetResponse.class);
				return subResp;
			} else {
				System.out.println("response is empty");
				return new SignalSubscriptionGetResponse();
			}
		} catch(Exception e ){
			e.printStackTrace();
		}
		return new SignalSubscriptionGetResponse();
	}

	public String deleteSignalSubscription(String signalSubscriptionId) {
		final String deleteSignalSubscriptionUrlString = "/api/v1/signal-subscriptions/";
		try {
			String resp = Api.deleteAPICallNew(deleteSignalSubscriptionUrlString+signalSubscriptionId);
			System.out.println(resp);
			return resp;
		}  catch(Exception e )
		{
			e.printStackTrace();
			return "";
		}

	}	
}

