package io.cucumber.skeleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.EquipmentAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.model.EquipmentGetResponse;
import io.cucumber.model.EquipmentResponse;

/*
 * class Equipment for testing Equipment feature BDD..
 */
public class Equipment {

	List<Map<String, String>> list;
	HashMap<String, String> dataTableHashMap;
	EquipmentResponse postResponse;
	EquipmentResponse getAfterPostResp;
	EquipmentGetResponse getAllAfterPostResp;
	EquipmentResponse putResponse;
	EquipmentResponse getAfterPutResponse;
	EquipmentResponse getAfterDeleteResponse;
	EquipmentGetResponse getAllAfterDeleteResponse;
	private int totalRecordAfterPost;
	private int totalRecordAfterDelete;

	/*
	 * This method givenData() used for getting the given data from data-table in the feature..
	 */
	@Given("^I have below Details for Equipment$")
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
	@When("I post get getAll put get delete get getAll equipment")
	public void postEquipment() {
		EquipmentAPI equipmentAPI = new EquipmentAPI();
		ObjectMapper mapperObj = new ObjectMapper();
		String equipmentData = "";
		try {
			equipmentData = mapperObj.writeValueAsString(dataTableHashMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		postResponse = equipmentAPI.postEquipment(equipmentData);

		getAfterPostResp = equipmentAPI.getEquipment(postResponse.getId());

		getAllAfterPostResp = equipmentAPI.getAllEquipments(postResponse.getParentId());
		totalRecordAfterPost = getAllAfterPostResp.getTotalCount();
		
		System.out.println("getAllAfterPostResp ===>"+getAllAfterPostResp);

		dataTableHashMap.put("description", dataTableHashMap.get("description")+"New");
		String newDepartmentData = "";
		try {
			newDepartmentData = mapperObj.writeValueAsString(dataTableHashMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		putResponse = equipmentAPI.putEquipment(postResponse.getId(),newDepartmentData);

		getAfterPutResponse = equipmentAPI.getEquipment(postResponse.getId());

		equipmentAPI.deleteEquipment(postResponse.getId());

		getAfterDeleteResponse = equipmentAPI.getEquipment(postResponse.getId());

		getAllAfterDeleteResponse = equipmentAPI.getAllEquipments(postResponse.getParentId());

		totalRecordAfterDelete = getAllAfterDeleteResponse.getTotalCount();
	}

	/*
	 * This method validateResults() is used for validating the responses..
	 */
	@Then("validate the equipment results")
	public void validateResults() {
		if(postResponse!=null && postResponse.getId()!=null&& !postResponse.getId().isEmpty()) {
			if(getAfterPostResp!=null && getAfterPostResp.getId()!=null && !getAfterPostResp.getId().isEmpty()) {
				if(getAfterPostResp.getId().equals(postResponse.getId())){
					if(getAllAfterPostResp!=null && getAllAfterPostResp.getValues().size()>0) {
						EquipmentResponse equipmentResponse = getAllAfterPostResp.getValues().stream().filter(equip -> equip.getId().equals(postResponse.getId())).findAny().orElse(null);
						if(equipmentResponse != null) {
							System.out.println("equipment id present in first page ::");
						}else {
							System.out.println("equipment id not present in first page ::");
							//What if not found in first page, Do we have to retrieve all pages???
							//throw new AssertionError("Id not present in  get all response");
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
		
		//Check equipment details after  PUT
		if(getAfterPutResponse!=null && getAfterPutResponse.getId()!=null && !getAfterPutResponse.getId().isEmpty()) {
			if(!getAfterPutResponse.getDescription().equals(postResponse.getDescription())) {
				System.out.println("Equipment details updated");
			}else {
				throw new AssertionError("Equipments details not updated");
			}
		}

		//Check equipments details after  DELETE
		EquipmentResponse equipmentResponse = getAllAfterDeleteResponse.getValues().stream().filter(equip -> equip.getId().equals(postResponse.getId())).findAny().orElse(null);
		if(equipmentResponse != null) {
			throw new AssertionError("Id is present in  get all response after delete");
		}else {
			System.out.println("id not present, Equipment deleted successfully");
		}
		
		Assert.assertEquals(totalRecordAfterDelete, totalRecordAfterPost-1);
	}
	

}
