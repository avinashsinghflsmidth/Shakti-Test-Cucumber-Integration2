package io.cucumber.skeleton;


import java.util.List;

import org.junit.Assert;

import io.cucumber.api.SignalSubscriptionAPI;
import io.cucumber.model.SignalSubscriptionPostResponse;
import io.cucumber.model.SignalSubscriptionGetResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.sql.Timestamp;

/*
 * class SignalSubscription for testing SignalSubscription feature BDD..
 */
public class SignalSubscription {
	String strCallbackUrl;
	String strParentId;
	SignalSubscriptionPostResponse subscritionPostResponse;
	SignalSubscriptionGetResponse getAfterPostResponse;
	SignalSubscriptionGetResponse getAfterDeleteResponse;
	private int totalRecordAfterPost;
	private int totalRecordAfterDelete;
	/*
	 * This method givenData() used for getting the given data from data-table in the feature..
	 */
	@Given("^I have callbackUrl : (.*) and parentId : (.*)$")
	public void givenData(String callbackUrl, String parentId) {
		System.out.println("Signal Subscription:Given ..");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());		
		String uniqueUrl = "https://" + "automation" + "-" + timestamp.getTime() + ".com";
		strCallbackUrl = uniqueUrl;
		strParentId = parentId;		
	}

	/*
	 * This method postSubscription() is used for POST, GET, DELETE, GET operations..
	 */
	@When("^I post then get then delete then get$")
	public void postSubscription() {
		SignalSubscriptionAPI signalSubscriptionAPI=new SignalSubscriptionAPI();

		String signalSubscriptionData = "{\"callbackUrl\":\"" + strCallbackUrl + "\",";
		signalSubscriptionData = signalSubscriptionData + 	"\"parentId\":\"" + strParentId + "\"}";

		try {
			//POST
			subscritionPostResponse = signalSubscriptionAPI.postSignalSubcription(signalSubscriptionData);

			//GET			
			getAfterPostResponse = signalSubscriptionAPI.getSignalSubcription();	
			totalRecordAfterPost = getAfterPostResponse.getTotalCount();

			//DELETE
			signalSubscriptionAPI.deleteSignalSubscription(subscritionPostResponse.getId());

			//GET
			getAfterDeleteResponse = signalSubscriptionAPI.getSignalSubcription();	

			totalRecordAfterDelete = getAfterDeleteResponse.getTotalCount();
		}catch (Exception e) {
			System.err.println("Error occured while creating url object in post subscription..");
		}

	}

	/*
	 * This method validateGetResponse() is used for validating the responses..
	 */
	@Then("posted subscripion will be in get result deleted subscription will not be in get result$")
	public void validateGetResponse() {
		if (subscritionPostResponse.getId() != null && !subscritionPostResponse.getId().isEmpty()) {
			List<SignalSubscriptionPostResponse> subscriptions = getAfterPostResponse.getSubscriptions();			
			Boolean flag = false;
			if (!(getAfterPostResponse.getSubscriptions().isEmpty()))
			{
				for(SignalSubscriptionPostResponse temp : subscriptions) {
					if (temp.getCallbackUrl().equals(strCallbackUrl) && temp.getParentId().equals(strParentId) ){
						flag = true;
						break;
					}
				}

			}else {throw new AssertionError("Empty subscription list");}
			if (flag == false) {
				throw new AssertionError("Posted Url & Signal Id is not found");
			}
		}else {
			throw new AssertionError("Id was not found in posted response");
		}

		//Validate if deleted subscription is not in get 
		List<SignalSubscriptionPostResponse> subscriptions = getAfterDeleteResponse.getSubscriptions();		
		Boolean flag = false;
		for(SignalSubscriptionPostResponse temp : subscriptions) {
			if (temp.getCallbackUrl().equals(strCallbackUrl) && temp.getParentId().equals(strParentId) ){
				flag = true;
				break;
			}
		}
		if (flag == true) {
			throw new AssertionError("Posted Url & Signal Id is not deleted");
		}
		
		Assert.assertEquals(totalRecordAfterDelete, totalRecordAfterPost-1);
	}

}
