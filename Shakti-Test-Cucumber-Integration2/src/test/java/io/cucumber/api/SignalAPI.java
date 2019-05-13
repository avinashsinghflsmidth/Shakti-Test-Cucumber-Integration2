package io.cucumber.api;

import java.net.MalformedURLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.SignalResponse;
import io.cucumber.model.SignalResponseGet;

/*
 * class SignalAPI having Api url and method details..
 */
public class SignalAPI {

	/*
	 * method postSignal sending Api With payload data...
	 */
	public SignalResponse postSignal(String payload) {
		String postSignalUrlString = "/api/v1/signals";
		try {
			String ApiResponse = Api.postAPICallNew(postSignalUrlString, payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SignalResponse response = mapper.readValue(ApiResponse, SignalResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new SignalResponse();
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return new SignalResponse();
	}

	/*
	 * method getSignal() getting by id...
	 */
	public SignalResponse getSignal(String id) {
		String getSignalByIdUrlString = "/api/v1/signals/";
		try {
			String ApiResponse = Api.getAPICallNew(getSignalByIdUrlString + id);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SignalResponse response = mapper.readValue(ApiResponse, SignalResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new SignalResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SignalResponse();
	}


	/*
	 * method getAllSignal() getting all signals...
	 */
	public SignalResponseGet getAllSignal() {
		String getAllSignalsUrlString = "/api/v1/signals/";
		try {
			String ApiResponse = Api.getAPICallNew(getAllSignalsUrlString);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SignalResponseGet response = mapper.readValue(ApiResponse, SignalResponseGet.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return null;
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/*
	 * method putSignal() for updating by id...
	 */
	public SignalResponse putSignal(String id, String payload) {
		String updateSignalByIdUrlString = "/api/v1/signals/";
		try {
			String ApiResponse = Api.putAPICallNew(updateSignalByIdUrlString + id, payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SignalResponse response = mapper.readValue(ApiResponse, SignalResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new SignalResponse();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	/*
	 * method deleteSignal() deleting  dept by id..
	 */
	public String deleteSignal(String id) {
		String deleteByIdUrlString = "/api/v1/signals/";
		try {
			String ApiResponse = Api.deleteAPICallNew(deleteByIdUrlString + id);
			return ApiResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * method postSignalBatch() for POST with payload data..
	 */
	public static SignalResponse postSignalBatch(String payload) {
		String postSignalBatchUrlString = "/api/v1/signals";
		try {
			String ApiResponse = Api.postAPICallNew(postSignalBatchUrlString, payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SignalResponse response = mapper.readValue(ApiResponse, SignalResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new SignalResponse();
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return new SignalResponse();
	}
}
