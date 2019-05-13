package io.cucumber.skeleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.SiteAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.model.SiteResponse;
import io.cucumber.model.SiteResponseGet;

public class Site {
	List<Map<String, String>> list;
	HashMap<String, String> dataTableHashMap;

	SiteResponse getAfterPostResp;
	SiteResponseGet getAllAfterPostResp;
	SiteResponse putResp;
	SiteResponse getAfterPutResp;
	SiteResponse getAfterDeleteResp;
	SiteResponseGet getAllAfterDeleteResp;
	SiteResponse postResp;

	private int totalRecordAfterPost;
	private int totalRecordAfterDelete;

	@Given("^I have below Details for Site$")
	public void givenData(DataTable dataTable) {
		list = dataTable.asMaps(String.class, String.class);
		for(Map<String,String> map : list) {
			dataTableHashMap = new HashMap<String, String>(); 
			dataTableHashMap.putAll(map); 
		}
		System.out.println("dataTableHashMap===>"+dataTableHashMap);
	}

	@When("I post get getAll put get delete get getall site")
	public void postData() {
		ObjectMapper mapperObj = new ObjectMapper();
		String siteData = "";
		long time = System.currentTimeMillis();
		try {
			dataTableHashMap.put("name", dataTableHashMap.get("name")+time);
			siteData = mapperObj.writeValueAsString(dataTableHashMap);
			System.out.println("siteData :::"+siteData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		SiteAPI siteApi = new SiteAPI();
		postResp = siteApi.postSite(siteData);
		
		getAfterPostResp = siteApi.getSite(postResp.getId());
		
		getAllAfterPostResp = siteApi.getAllSite();
		totalRecordAfterPost = getAllAfterPostResp.getTotalCount();
		
		dataTableHashMap.put("description", dataTableHashMap.get("description")+"New");
		String newSiteData = "";
		try {
			newSiteData = mapperObj.writeValueAsString(dataTableHashMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		putResp = siteApi.putSite(postResp.getId(), newSiteData);

		getAfterPutResp = siteApi.getSite(postResp.getId());
		
		siteApi.deleteSite(postResp.getId());
		
		getAfterDeleteResp = siteApi.getSite(postResp.getId());
		
		getAllAfterDeleteResp = siteApi.getAllSite();
		totalRecordAfterDelete = getAllAfterDeleteResp.getTotalCount();
		
	}

	@Then("validate the Site results")
	public void validateResults() {
		if (postResp != null && postResp.getId() != null && !postResp.getId().isEmpty()) {
			if (getAfterPostResp != null && getAfterPostResp.getId() != null && !getAfterPostResp.getId().isEmpty()) {
				if (getAfterPostResp.getId().equals(postResp.getId())) {
					if (getAllAfterPostResp != null && getAllAfterPostResp.getValues().size() > 0) {
						SiteResponse siteResponse =	getAllAfterPostResp.getValues().stream().filter(i -> i.getId().equals(postResp.getId())).findAny().orElse(null);
						if(siteResponse != null) {
							System.out.println("id is present in first page");
						}else {
							// What if not found in first page, Do we have to retrieve all pages???
							// throw new AssertionError("Id not present in get all response");
						}
					} else {
						throw new AssertionError("Id not present in  get all response");
					}
				} else {
					throw new AssertionError("Id in get and post responses are not matching");
				}
			} else {
				throw new AssertionError("Id was not found in get response");
			}
		} else {
			throw new AssertionError("Id was not found in posted response");
		}

		// Check site details after PUT
		if (getAfterPutResp != null && getAfterPutResp.getId() != null && !getAfterPutResp.getId().isEmpty()) {
			if (!getAfterPutResp.getDescription().equals(postResp.getDescription())) {
					System.out.println("sites updated!");
			} else {
				throw new AssertionError("Sites details not updated");
			}
		}

		// Check site details after DELETE
		SiteResponse siteResponse = getAllAfterDeleteResp.getValues().stream().filter(s -> s.getId().equals(postResp.getId())).findAny().orElse(null);
		if(siteResponse != null) {
			throw new AssertionError("Id is present in  get all response after delete");
		}else {
			System.out.println("Sites deleted!");
		}
		
		Assert.assertEquals(totalRecordAfterDelete, totalRecordAfterPost-1);

	}
}
