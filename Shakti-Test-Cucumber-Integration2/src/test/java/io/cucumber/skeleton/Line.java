package io.cucumber.skeleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.LineAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.model.LineGetResponse;
import io.cucumber.model.LineResponse;

public class Line {
	List<Map<String, String>> list;
	LineResponse getAfterPostResponse;
	HashMap<String, String> dataTableHashMap;
	LineGetResponse getAllAfterPostResponse;
	LineResponse putResponse;
	LineResponse getAfterPutResponse;
	LineResponse getAfterDeleteResponse;
	LineGetResponse getAllAfterDeleteResponse;
	LineResponse postResponse;
	private int totalRecordAfterPost;
	private int totalRecordAfterDelete;

	@Given("^I have below Details for Line$")
	public void givenData(DataTable dataTable) {
		list = dataTable.asMaps(String.class, String.class);
		for(Map<String,String> map : list) {
			dataTableHashMap = new HashMap<String, String>(); 
			dataTableHashMap.putAll(map); 
		}
		System.out.println("dataTableHashMap===>"+dataTableHashMap);
	}


	@When("I post get getAll put get delete get getall Line")
	public void postData() {

		ObjectMapper mapperObj = new ObjectMapper();
		String assetData = "";
		try {
			assetData = mapperObj.writeValueAsString(dataTableHashMap);
			System.out.println("assetData :::"+assetData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		LineAPI assetApi=new LineAPI();

		postResponse = assetApi.postAsset(assetData);

		getAfterPostResponse = assetApi.getAsset(postResponse.getId());

		getAllAfterPostResponse = assetApi.getAllAsset();
		totalRecordAfterPost = getAllAfterPostResponse.getTotalCount();


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

		getAllAfterDeleteResponse  = assetApi.getAllAsset();
		totalRecordAfterDelete = getAllAfterDeleteResponse.getTotalCount();

	}
	@Then("validate the Line results")
	public void validateResults() {
		if(postResponse !=null && postResponse.getId()!=null&& !postResponse.getId().isEmpty()) {
			if(getAfterPostResponse !=null && getAfterPostResponse.getId()!=null && !getAfterPostResponse.getId().isEmpty()) {
				if(getAfterPostResponse.getId().equals(postResponse.getId())){
					if(getAllAfterPostResponse !=null && getAllAfterPostResponse.getValues().size()>0) {
						LineResponse lineResponse = getAllAfterPostResponse.getValues().stream().filter(a -> a.getId().equals(postResponse.getId())).findAny().orElse(null);
						if(lineResponse != null) {
							System.out.println("asset is present in first page");
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
		if(getAfterPutResponse !=null && getAfterPutResponse.getId() !=null && !getAfterPutResponse.getId().isEmpty()) {
			if(!getAfterPutResponse.getDescription().equals(postResponse.getDescription())) {
				System.out.println("Asset updated");
			}else {
				throw new AssertionError("Line details not updated");
			}
		}

		//Check asset details after  DELETE
		LineResponse lineResponse = getAllAfterDeleteResponse.getValues().stream().filter(a -> a.getId().equals(postResponse.getId())).findAny().orElse(null);
		if(lineResponse != null) {
			throw new AssertionError("Id is present in  get all response after delete");
		}else {
			System.out.println("Asset deleted");
		}
		
		Assert.assertEquals(totalRecordAfterDelete, totalRecordAfterPost-1);
	}
}
