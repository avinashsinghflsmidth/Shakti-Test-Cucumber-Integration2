package io.cucumber.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.EquipmentGetResponse;
import io.cucumber.model.EquipmentResponse;

/*
 * class EquipmentAPI having Api url and method details..
 */
public class EquipmentAPI {

	/*
	 * method postEquipment sending Api With payload data...
	 */
	public EquipmentResponse postEquipment(String payload) {
		String postEquipmentUrlString = "/api/v1/equipments";
		try {
			String ApiResponse = Api.postAPICallNew(postEquipmentUrlString, payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				EquipmentResponse response = mapper.readValue(ApiResponse, EquipmentResponse.class);
				return response;
			} else {
				System.out.println("response is empty===>"+ApiResponse);
				return new EquipmentResponse();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new EquipmentResponse();
	}


	/*
	 * method getEquipment() getting dept by id...
	 */
	public EquipmentResponse getEquipment(String id) {
		String getEquipByIdUrlString = "/api/v1/equipments/";
		try {
			String ApiResponse = Api.getAPICallNew(getEquipByIdUrlString+id);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				EquipmentResponse response = mapper.readValue(ApiResponse, EquipmentResponse.class);
				return response;
			} else {
				System.out.println("response is empty===>"+ApiResponse);
				return new EquipmentResponse();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new EquipmentResponse();
	}

	/*
	 * method getAllEquipments() getting all equipment...
	 */
	public EquipmentGetResponse getAllEquipments(String parentdId) {
		String getAllEquipUrlString = "/api/v1/equipments?parentId="+parentdId;
		try{
			String ApiResponse =  Api.getAPICallNew(getAllEquipUrlString);
			if(!ApiResponse.isEmpty()){
				ObjectMapper mapper = new ObjectMapper();
				EquipmentGetResponse response = mapper.readValue(ApiResponse, EquipmentGetResponse.class);
				return response;
			}else {
				System.out.println("response is empty===>"+ApiResponse);
				return null;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}

	/*
	 * method putEquipment() updating  equiment by id..
	 */
	public EquipmentResponse putEquipment(String id, String payload) {
		String updateEquipByIdUrlString = "/api/v1/equipments/";
		try {
			String ApiResponse = Api.putAPICallNew(updateEquipByIdUrlString+id,payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				EquipmentResponse response = mapper.readValue(ApiResponse, EquipmentResponse.class);
				return response;
			} else {
				System.out.println("response is empty===>"+ApiResponse);
				return new EquipmentResponse();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * method deleteEquipment() deleting  equipment by id..
	 */
	public String deleteEquipment(String id) {
		try {
			String ApiResp = Api.deleteAPICallNew("/api/v1/equipments/"+id);
			return ApiResp;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}		
}