package io.cucumber.api;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.LineGetResponse;
import io.cucumber.model.LineResponse;

public class LineAPI {

	public LineResponse postAsset(String payload) {

		String postAssetUrlStringUrl = "/api/v1/lines";
		try {
			Api.loadProperties();
			String ApiResponse = Api.postAPICallNew(postAssetUrlStringUrl, payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				LineResponse response = mapper.readValue(ApiResponse, LineResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new LineResponse();

			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new LineResponse();
	}
	public LineResponse getAsset(String id) {
		String getAssetByIdUrlString = "/api/v1/lines/";
		try {
			String ApiResponse = Api.getAPICallNew(getAssetByIdUrlString+id);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				LineResponse response = mapper.readValue(ApiResponse, LineResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new LineResponse();
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new LineResponse();
	}

	public LineGetResponse getAllAsset() {
		String getAllAssetUrlString = "/api/v1/lines/";
		try{
			String ApiResponse =  Api.getAPICallNew(getAllAssetUrlString);
			if(!ApiResponse.isEmpty()){
				ObjectMapper mapper = new ObjectMapper();
				LineGetResponse response = mapper.readValue(ApiResponse, LineGetResponse.class);
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

	public LineResponse putAsset(String id, String payload) {
		String updateAssetByIdUrlString = "/api/v1/lines/";
		try {
			String ApiResponse = Api.putAPICallNew(updateAssetByIdUrlString+id,payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				LineResponse response = mapper.readValue(ApiResponse, LineResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new LineResponse();

			}} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public String deleteAsset(String id) {
		String deleteAssetByIdUrlString = "/api/v1/lines/"; 
		try {
			String ApiResp = Api.deleteAPICallNew(deleteAssetByIdUrlString+id);
			return ApiResp;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}		
}

