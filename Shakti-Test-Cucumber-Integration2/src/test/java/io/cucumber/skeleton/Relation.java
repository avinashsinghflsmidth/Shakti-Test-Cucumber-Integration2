package io.cucumber.skeleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.RelationAPI;
import io.cucumber.model.RelationResponseGet;
import io.cucumber.model.RelationResponseGetRelations;

/*
 * class Relation for testing Relation feature BDD..
 */
public class Relation {

    RelationResponseGet getAllAfterPostResponse;
	List<Map<String, String>> list;
	HashMap<String, String> dataTableHashMap;
	String strParentId;
    
	/*
	 * This method givenData() used for getting the given data from the feature..
	 */
	@Given("^I have Details for Relation (.*)$")
	public void givenData(String parentId) {
		System.out.println("Signal Subscription:Given ..");
		strParentId = parentId;		
		System.out.println("strParentId ::"+strParentId);
	}

	/*
	 * This method getRelations() used for GET operations..
	 */
	@When("I getAll Relation")
	public void getRelations() {
		RelationAPI relationApi = new RelationAPI();
		getAllAfterPostResponse = relationApi.getAllRelations(strParentId);
		System.out.println(getAllAfterPostResponse.getRelations().size());
		System.out.println("getAllAfterPostResponse ===>"+getAllAfterPostResponse);
	}
	

	/*
	 * This method validateResults() is used for validating the responses..
	 */
	@Then("validate the Relation results")
	public void validateResults() {
		if(getAllAfterPostResponse != null && getAllAfterPostResponse.getRelations().size() > 0) {
			List<RelationResponseGetRelations> relationResponseGetRelations = getAllAfterPostResponse.getRelations().stream().filter(p -> p.getParentId().equals(strParentId)).collect(Collectors.toList());  
			Assert.assertEquals(relationResponseGetRelations.size(), relationResponseGetRelations.size());
		}
	}
}
