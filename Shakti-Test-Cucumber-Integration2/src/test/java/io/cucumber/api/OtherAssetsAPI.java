package io.cucumber.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.OtherAssetsGetResponse;
import io.cucumber.model.OtherAssetsResponse;

/*
 * class OtherAssetsAPI having Api url and method details..
 */
public class OtherAssetsAPI {

	/*
	 * method postAsset sending Api With payload data...
	 */
	public OtherAssetsResponse postAsset(String payload) {
		String postOtherAssetsUrlString = "/api/v1/otherAssets";
		try {
			String ApiResponse = Api.postAPICallNew(postOtherAssetsUrlString, payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				OtherAssetsResponse response = mapper.readValue(ApiResponse, OtherAssetsResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new OtherAssetsResponse();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new OtherAssetsResponse();
	}
	
	/*
	 * method getAsset() getting assets by id...
	 */
	public OtherAssetsResponse getAsset(String id) {
		String getOtherAssetByIdUrlString = "/api/v1/otherAssets/";
		try {
			String ApiResponse = Api.getAPICallNew(getOtherAssetByIdUrlString+id);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				OtherAssetsResponse response = mapper.readValue(ApiResponse, OtherAssetsResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new OtherAssetsResponse();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new OtherAssetsResponse();
	}

	/*
	 * method getAllAsset() getting all assets...
	 */
	public OtherAssetsGetResponse getAllAsset() {
		String getAllAssetUrlString = "/api/v1/otherAssets/";
		try{
			String ApiResponse =  Api.getAPICallNew(getAllAssetUrlString);
			if(!ApiResponse.isEmpty()){
				ObjectMapper mapper = new ObjectMapper();
				OtherAssetsGetResponse response = mapper.readValue(ApiResponse, OtherAssetsGetResponse.class);
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
	 * method putAsset() updating  by id..
	 */
	public OtherAssetsResponse putAsset(String id, String payload) {
		String updateOtherAssetUrlString = "/api/v1/otherAssets/";
		try {
			String ApiResponse = Api.putAPICallNew(updateOtherAssetUrlString+id,payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				OtherAssetsResponse response = mapper.readValue(ApiResponse, OtherAssetsResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new OtherAssetsResponse();
			}}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public String deleteAsset(String id) {
		String deleteOtherAssetByIdUrlString = "/api/v1/otherAssets/";
		try {
			String ApiResp = Api.deleteAPICallNew(deleteOtherAssetByIdUrlString+id);
			return ApiResp;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}		
}

