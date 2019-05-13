package io.cucumber.skeleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.OtherAssetsAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.model.OtherAssetsGetResponse;
import io.cucumber.model.OtherAssetsResponse;

/*
 * class OtherAssets for testing OtherAssets feature BDD..
 */
public class OtherAssets {
	List<Map<String, String>> list;
	HashMap<String, String> dataTableHashMap;
	OtherAssetsResponse getAfterPostResponse;
	OtherAssetsGetResponse getAllAfterPostResponse;
	OtherAssetsResponse putResponse;
	OtherAssetsResponse getAfterPutResponse;
	OtherAssetsResponse getAfterDeleteResponse;
	OtherAssetsGetResponse getAllAfterDeleteResponse;
	OtherAssetsResponse postResponse;

	private int totalRecordAfterPost;
	private int totalRecordAfterDelete;

	/*
	 * This method givenData() used for getting the given data from data-table in the feature..
	 */
	@Given("^I have below Details for Other Assets$")
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
	@When("I post get getAll put get delete get getall Other Assets")
	public void postData() {

		ObjectMapper mapperObj = new ObjectMapper();
		String assetData = "";
		long time = System.currentTimeMillis();
		try {
			dataTableHashMap.put("name", dataTableHashMap.get("name")+time);
			assetData = mapperObj.writeValueAsString(dataTableHashMap);
			System.out.println("assetData :::"+assetData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		OtherAssetsAPI assetApi=new OtherAssetsAPI();

		postResponse = assetApi.postAsset(assetData);
		System.out.println("The OtherAssets ID is :"+postResponse.getId());

		getAfterPostResponse = assetApi.getAsset(postResponse.getId());


		getAllAfterPostResponse = assetApi.getAllAsset();
		totalRecordAfterPost = getAllAfterPostResponse.getTotalCount();

		System.out.println("totalRecordAfterPost count ===>"+totalRecordAfterPost);

		dataTableHashMap.put("description", dataTableHashMap.get("description")+"New");
		String newAssetsData = "";
		try {
			newAssetsData = mapperObj.writeValueAsString(dataTableHashMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		putResponse = assetApi.putAsset(postResponse.getId(),newAssetsData);


		getAfterPutResponse = assetApi.getAsset(postResponse.getId());

		assetApi.deleteAsset(postResponse.getId());

		getAfterDeleteResponse = assetApi.getAsset(postResponse.getId());


		getAllAfterDeleteResponse = assetApi.getAllAsset();

		totalRecordAfterDelete = getAllAfterDeleteResponse.getTotalCount();
		System.out.println("totalRecordAfterDelete count ===>"+totalRecordAfterDelete);

	}


	/*
	 * This method validateResults() is used for validating the responses..
	 */
	@Then("validate the Other Assets results")
	public void validateResults() {
		if(postResponse !=null && postResponse.getId() !=null && !postResponse.getId().isEmpty()) {
			if(getAfterPostResponse !=null && getAfterPostResponse.getId() !=null && !getAfterPostResponse.getId().isEmpty()) {
				if(getAfterPostResponse.getId().equals(postResponse.getId())){
					if(getAllAfterPostResponse!=null && getAllAfterPostResponse.getValues().size()>0) {
						OtherAssetsResponse otherAssetsResponse = getAllAfterPostResponse.getValues().stream().filter(r -> r.getId().equals(postResponse.getId())).findAny().orElse(null);
						if(otherAssetsResponse != null) {
							System.out.println("id present in first page");
						}else {
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


		//Check asset details after  PUT
		if(getAfterPutResponse !=null && getAfterPutResponse.getId()!=null && !getAfterPutResponse.getId().isEmpty()) {
			if(!getAfterPutResponse.getDescription().equals(postResponse.getDescription())) {
					System.out.println("otherassets updated");
			}else {
				throw new AssertionError("OtherAssets details not updated");
			}
		}

		//Check asset details after  DELETE
		OtherAssetsResponse otherAssetsResponse = getAllAfterDeleteResponse.getValues().stream().filter(r -> r.getId().equals(postResponse.getId())).findAny().orElse(null);
		if(otherAssetsResponse != null) {
			throw new AssertionError("OtherAssets details not deleted");
		}else {
			System.out.println("other assets deleted");
		}
		
		
		Assert.assertEquals(totalRecordAfterDelete, totalRecordAfterPost-1);

	}
}
