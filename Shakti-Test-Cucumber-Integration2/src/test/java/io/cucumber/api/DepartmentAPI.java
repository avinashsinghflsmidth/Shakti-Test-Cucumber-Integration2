package io.cucumber.api;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.DepartmentResponse;
import io.cucumber.model.DepartmentResponseGet;

/*
 * class DepartmentAPI having Api url and method details..
 */
public class DepartmentAPI {

	/*
	 * method postDepartment sending Api With payload data...
	 */
	public DepartmentResponse postDepartment(String requestData) {
		String postApiUrlString = "/api/v1/departments";
		try {
			Api.loadProperties();
			String ApiResponse = Api.postAPICallNew(postApiUrlString, requestData);
			DepartmentResponse response = null;
			if (!ApiResponse.isEmpty()) {
				// mapping response to DepartmentResponse class..
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.readValue(ApiResponse, DepartmentResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new DepartmentResponse();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new DepartmentResponse();
	}

	/*
	 * method getDepartment() getting dept by id...
	 */
	public DepartmentResponse getDepartment(String id) {
		String getDeptByIdUrlString = "/api/v1/departments/";
		try {
			String ApiResponse = Api.getAPICallNew(getDeptByIdUrlString+id);
			DepartmentResponse response = null;
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.readValue(ApiResponse, DepartmentResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new DepartmentResponse();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new DepartmentResponse();
	}

	/*
	 * method getAllDepartment() getting all dept...
	 */
	public DepartmentResponseGet getAllDepartment() {
		String getAllDeptUrlString = "/api/v1/departments/";
		String ApiResponse =  Api.getAPICallNew(getAllDeptUrlString);
		DepartmentResponseGet response = null;
		if(!ApiResponse.isEmpty()){
			ObjectMapper mapper = new ObjectMapper();
			try {
				response = mapper.readValue(ApiResponse, DepartmentResponseGet.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}else {
			System.out.println("response is empty===>"+ApiResponse);
			return null;
		}
	}

	/*
	 * method putDepartment() updating  dept by id..
	 */
	public DepartmentResponse putDepartment(String id, String newDepartmentData) {
		String updateDepartmentByIdUrlString = "/api/v1/departments/";
		try {
			String ApiResponse = Api.putAPICallNew(updateDepartmentByIdUrlString+id,newDepartmentData);
			DepartmentResponse response = null;
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.readValue(ApiResponse, DepartmentResponse.class);
			} else {
				System.out.println("response is empty===>"+ApiResponse);
				return new DepartmentResponse();
			}
			return response;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * method deleteDeapartment() deleting  dept by id..
	 */
	public String deleteDeapartment(String id) {
		String deleteDepartmentByIdUrlString = "/api/v1/departments/";
		String ApiResp = "";
		try {
			 ApiResp = Api.deleteAPICallNew(deleteDepartmentByIdUrlString+id);
			return ApiResp;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ApiResp;
		}
	}

	/*
	 * method getDepartmentByPage() deleting  dept page by page..
	 */
	public DepartmentResponseGet getDepartmentByPage(int pageNumber, int pageSize) {
		String getAllDeptUrlString = "/api/v1/departments/"+"?page="+pageNumber+"&pageSize="+pageSize;
		String ApiResponse =  Api.getAPICallNew(getAllDeptUrlString);
		DepartmentResponseGet response = null;
		if(!ApiResponse.isEmpty()){
			ObjectMapper mapper = new ObjectMapper();
			try {
				response = mapper.readValue(ApiResponse, DepartmentResponseGet.class);
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

