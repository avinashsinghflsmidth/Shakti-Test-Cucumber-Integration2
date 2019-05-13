package io.cucumber.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.DepartmentResponse;
import io.cucumber.model.EvenServiceResponseAllGet;
import io.cucumber.model.EventServiceResponseGet;

public class EventServiceAPI {

	public EventServiceResponseGet postEvent(String eventData) {
		String postApiUrlString = "/api/v1/events";
		try {
			String ApiResponse = Api.postAPICallNew(postApiUrlString, eventData);
			System.out.println("ApiResponse ::"+ApiResponse);
			EventServiceResponseGet response = null;
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new  ObjectMapper();
				response = mapper.readValue(ApiResponse,  EventServiceResponseGet.class);
				return response; 
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new EventServiceResponseGet(); 
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new EventServiceResponseGet();
	}

	public EvenServiceResponseAllGet getEvenServiceByAssetId(String assetId) {
		String getDeptByIdUrlString = "/api/v1/events?assetId=";
		try {
			String ApiResponse = Api.getAPICallNew(getDeptByIdUrlString+assetId);
			EvenServiceResponseAllGet response = null;
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.readValue(ApiResponse, EvenServiceResponseAllGet.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new EvenServiceResponseAllGet();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new EvenServiceResponseAllGet();
	}

}

