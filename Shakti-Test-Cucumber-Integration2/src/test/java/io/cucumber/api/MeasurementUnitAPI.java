package io.cucumber.api;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.DepartmentResponseGet;
import io.cucumber.model.MeasurementUnitGetResponse;
import io.cucumber.model.MeasurementUnitPostResponse;

/*
 * class MeasurementUnitAPI having Api url and method details..
 */
public class MeasurementUnitAPI {

	/*
	 * method postMeasurementUnit sending Api With payload data...
	 */
	public MeasurementUnitPostResponse postMeasurementUnit(String measurementData) {

		String postApiUrlString = "/api/v1/measurement-units";
		try {
			String ApiResponse = Api.postAPICallNew(postApiUrlString, measurementData);
			MeasurementUnitPostResponse response = null;
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.readValue(ApiResponse, MeasurementUnitPostResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new MeasurementUnitPostResponse();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new MeasurementUnitPostResponse();
	
	}

	/*
	 * method getAllMeasurementUnits() getting all dept...
	 */
	public MeasurementUnitGetResponse getAllMeasurementUnits() {
		String getAllDeptUrlString = "/api/v1/measurement-units/";
		String ApiResponse =  Api.getAPICallNew(getAllDeptUrlString);
		MeasurementUnitGetResponse response = null;
		if(!ApiResponse.isEmpty()){
			ObjectMapper mapper = new ObjectMapper();
			try {
				response = mapper.readValue(ApiResponse, MeasurementUnitGetResponse.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}else {
			System.out.println("response is empty===>"+ApiResponse);
			return null;
		}
	}

	
}
