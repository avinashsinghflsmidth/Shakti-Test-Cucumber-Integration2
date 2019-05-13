package io.cucumber.skeleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.EmailSubscriptionAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.model.EmailSubscriptionGetResponse;
import io.cucumber.model.EmailSubscriptionResponse;

/*
 * class EmailSubscription for testing EmailSubscription feature BDD..
 */
public class EmailSubscription {
	List<Map<String, String>> list;
	EmailSubscriptionResponse emailPostResponse;
	HashMap<String, String> dataTableHashMap;
	EmailSubscriptionGetResponse getAllAfterPostResponse;
	EmailSubscriptionGetResponse getAllAfterDeleteResp;
	
	private int totalRecordAfterPost;
	private int totalRecordAfterDelete;



	@Given("^I have below Details for EmailSubscription$")
	public void givenData(DataTable dataTable) {
		list = dataTable.asMaps(String.class, String.class);
		for(Map<String,String> map : list) {
			dataTableHashMap = new HashMap<String, String>(); 
			dataTableHashMap.putAll(map); 
		}
		System.out.println("dataTableHashMap===>"+dataTableHashMap);
	}

	@When("I post getAll delete getAll EmailSubscription")
	public void postEmail() {
		ObjectMapper mapperObj = new ObjectMapper();
		String emailData = "";
		try {
			//dataTableHashMap.put("name", "name_"+date.getTime());
			emailData = mapperObj.writeValueAsString(dataTableHashMap);
			System.out.println("emailData :::"+emailData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		EmailSubscriptionAPI emailApi = new EmailSubscriptionAPI();

		emailPostResponse = emailApi.postEmail(emailData);

		getAllAfterPostResponse = emailApi.getAllEmail();
		totalRecordAfterPost = getAllAfterPostResponse.getTotalCount();

		emailApi.deleteEmail(emailPostResponse.getEventEmailSubscriptionId());

		getAllAfterDeleteResp = emailApi.getAllEmail();
		totalRecordAfterDelete = getAllAfterDeleteResp.getTotalCount();
	}	

	@Then("validate the EmailSubscription results")
	public void validateResults() {
		if(emailPostResponse !=null && emailPostResponse.getId() !=null && !emailPostResponse.getId().isEmpty()) {
			if(getAllAfterPostResponse !=null && getAllAfterPostResponse.getSubscriptions().size()>0) {
				EmailSubscriptionResponse emailSubscriptionResponse = getAllAfterPostResponse.getSubscriptions().stream().filter(a -> a.getId().equals(emailPostResponse.getId())).findAny().orElse(null);
				if(emailSubscriptionResponse != null) {
					System.out.println("id found in first page");
				}else {
					//What if not found in first page, Do we have to retrieve all pages???
					//throw new AssertionError("Id not present in  get all response");
				}
			}else {
				throw new AssertionError("Id not present in  get all response");
			}
		}else {
			throw new AssertionError("Id was not found in posted response");
		}

		//Check equipments details after  DELETE
		EmailSubscriptionResponse emailSubscriptionResponse = getAllAfterDeleteResp.getSubscriptions().stream().filter(a -> a.getId().equals(emailPostResponse.getId())).findAny().orElse(null);
		if(emailSubscriptionResponse != null) {
			throw new AssertionError("Id is present in  get all response after delete");
		}else {
			System.out.println("subscription deleted");
		}
		
		Assert.assertEquals(totalRecordAfterPost-1 , totalRecordAfterDelete);
	}
}





