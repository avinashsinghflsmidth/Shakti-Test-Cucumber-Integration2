package io.cucumber.skeleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.DepartmentAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.model.DepartmentResponse;
import io.cucumber.model.DepartmentResponseGet;

/*
 * class Department for testing Department feature BDD..
 */
public class Department {

	List<Map<String, String>> list;
	HashMap<String, String> dataTableHashMap;
	DepartmentResponse departmentPostResponse;
	DepartmentResponse getAfterPostResponse;
	DepartmentResponseGet getAllAfterPostResponse;
	DepartmentResponse departmentPutResponse;
	DepartmentResponse getAfterPutResponse;
	DepartmentResponseGet getAllAfterDeleteResp;
	DepartmentResponse getAfterDeleteResponse;
	DepartmentAPI departmentAPI = new DepartmentAPI();
	DepartmentResponseGet getAllDepartmentByPage;
	private int totalRecordAfterPost;
	private int totalRecordAfterDelete;

	/*
	 * This method givenData() used for getting the given data from data-table in the feature..
	 */
	@Given("^I have below Details for Department$")
	public void givenData(DataTable dataTable) {
		list = dataTable.asMaps(String.class, String.class);
		for(Map<String,String> map : list) {
			dataTableHashMap = new HashMap<String, String>(); 
			dataTableHashMap.putAll(map); 
		}
		System.out.println("dataTableHashMap===>"+dataTableHashMap);
	}


	/*
	 * This method postData() is used for POST, GET, PUT, GET, DELETE, GET operations..
	 */
	@When("I post get getAll put get delete get getall")
	public void postData() {
		ObjectMapper mapperObj = new ObjectMapper();
		String departmentData = "";
		long time = System.currentTimeMillis();
		try {
			dataTableHashMap.put("name", dataTableHashMap.get("name")+time);
			departmentData = mapperObj.writeValueAsString(dataTableHashMap);
			System.out.println("departmentData :::"+departmentData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// calling POST Api...
		departmentPostResponse = departmentAPI.postDepartment(departmentData);
     
		// calling GET department by id..
		getAfterPostResponse = departmentAPI.getDepartment(departmentPostResponse.getId());
        
		// calling GET all department...
		getAllAfterPostResponse = departmentAPI.getAllDepartment();
		System.out.println("getAllAfterPostResponse total page ::::"+getAllAfterPostResponse.getTotalPages());
		
	   // System.out.println("last page data ::"+departmentAPI.getDepartmentByPage(getAllAfterPostResponse.getTotalPages(),getAllAfterPostResponse.getPageSize()));

		totalRecordAfterPost = getAllAfterPostResponse.getTotalCount();
		
		dataTableHashMap.put("description", dataTableHashMap.get("description")+"New");
		String newDepartmentData = "";
		try {
			newDepartmentData = mapperObj.writeValueAsString(dataTableHashMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// calling UPDATE department by id...
		departmentPutResponse = departmentAPI.putDepartment(departmentPostResponse.getId(),newDepartmentData);

		// calling GET department by id..
		getAfterPutResponse = departmentAPI.getDepartment(departmentPostResponse.getId());

		// calling DELETE department by id..
		departmentAPI.deleteDeapartment(departmentPostResponse.getId());

		// calling GET department by id..
		getAfterDeleteResponse = departmentAPI.getDepartment(departmentPostResponse.getId());

		//calling GET all department...
		getAllAfterDeleteResp = departmentAPI.getAllDepartment();
		totalRecordAfterDelete = getAllAfterDeleteResp.getTotalCount();

	}

	/*
	 * This method validateResults() is used for validating the responses..
	 */
	@Then("validate the department results")
	public void validateResults() {
		if(departmentPostResponse!=null && departmentPostResponse.getId()!=null&& !departmentPostResponse.getId().isEmpty()) {
			if(getAfterPostResponse!=null && getAfterPostResponse.getId()!=null && !getAfterPostResponse.getId().isEmpty()) {
				if(getAfterPostResponse.getId().equals(departmentPostResponse.getId())){
					if(getAllAfterPostResponse!=null && getAllAfterPostResponse.getValues().size()>0) {
						DepartmentResponse departmentResponse = getAllAfterPostResponse.getValues().stream().filter(dept -> dept.getId().equals(departmentPostResponse.getId())).findAny().orElse(null);
						if(departmentResponse != null) {
							System.out.println("id is present in first page");
						}else {
							// If id not present then..

							//What if not found in first page, Do we have to retrieve all pages???
							//throw new AssertionError("Id not present in  get all response");

							/*
							 * System.out.println("dept id not present in 1st page"); for(int i =
							 * getAllAfterPostResponse.getPage() ; i <=
							 * getAllAfterPostResponse.getTotalPages() ; i++) { getAllDepartmentByPage =
							 * departmentAPI.getDepartmentByPage(i+1,getAllAfterPostResponse.getPageSize());
							 * for(DepartmentResponse response : getAllDepartmentByPage.getValues()) {
							 * if(response.getId().equals(departmentPostResponse.getId())){ isPresent =
							 * true; break; } } } if(!isPresent) { throw new
							 * AssertionError("Id not present in get all response"); }
							 */
						}
					}else {
						throw new AssertionError("Id not present in  get all response");
					}
				}else {
					throw new AssertionError("Id in get and post responses are not matching");
				}
			}else {
				throw new AssertionError("Id was not found in get response");
			}
		}else {
			throw new AssertionError("Id was not found in posted response");
		}

		//Check department details after PUT..
		if(getAfterPutResponse !=null && getAfterPutResponse.getId() !=null && !getAfterPutResponse.getId().isEmpty()) {
			if(!getAfterPutResponse.getDescription().equals(departmentPostResponse.getDescription())) {
				System.out.println("Department details updated successfully");
			}else {
				throw new AssertionError("Department details not updated");
			}
		}

		//Check department details after DELETE..
		DepartmentResponse departmentResponse = getAllAfterDeleteResp.getValues().stream().filter(dept -> dept.getId().equals(departmentPostResponse.getId())).findFirst().orElse(null);
		if(departmentResponse != null) {
			throw new AssertionError("Id is present in when getting all response after delete by id");
		}else {
			System.out.println("id not present, departmentt deleted successfully");
		}
		Assert.assertEquals(totalRecordAfterDelete, totalRecordAfterPost-1);
	}
}
