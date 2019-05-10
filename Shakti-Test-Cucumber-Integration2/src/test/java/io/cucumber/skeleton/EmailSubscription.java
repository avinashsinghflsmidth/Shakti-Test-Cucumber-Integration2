package io.cucumber.skeleton;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.EmailSubscriptionAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.model.EmailSubscriptionResponse;

/*
 * class EmailSubscription for testing EmailSubscription feature BDD..
 */
public class EmailSubscription {
	List<Map<String, String>> list;
	EmailSubscriptionResponse postResponse;
	HashMap<String, String> dataTableHashMap;

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
		postResponse = emailApi.postEmail(emailData);
		
		System.out.println("email postResponse ::"+postResponse);
	}	

	@Then("validate the EmailSubscription results")
	public void validateResults() {
		
	}
}





