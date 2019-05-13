package io.cucumber.skeleton;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.HealthCheckApi;
import io.cucumber.model.HealthCheckResponseGet;

/*
 * class HealthCheck for testing Department feature BDD..
 */
public class HealthCheck {

	HealthCheckResponseGet healthCheckResponseGet;

	/*
	 * This method givenData() used for getting the given data from data-table in the feature..
	 */
	@Given("^I have Details for HealthCheck$")
	public void givenData() {
		System.out.println("given data::");
	}

	/*
	 * This method getAllData() is used for GET operations..
	 */
	@When("I getAll HealthCheck")
	public void getAllData() {
		HealthCheckApi healthCheckApi = new HealthCheckApi();
		healthCheckResponseGet = healthCheckApi.getAllHealthCheck();
		System.out.println("healthCheckResponseGet ===>"+healthCheckResponseGet);

	}

	/*
	 * This method validateResults() is used for validating the responses..
	 */
	@Then("validate the HealthCheck results")
	public void validateResults() {
		if(healthCheckResponseGet != null && healthCheckResponseGet.getStatus().equalsIgnoreCase("OK")) {
			if(healthCheckResponseGet.getComponentsStatus().getAssetHierarchy().equalsIgnoreCase("OK")) {
				if(healthCheckResponseGet.getComponentsStatus().getFieldAgentGateway().equalsIgnoreCase("OK")) {
					if(healthCheckResponseGet.getComponentsStatus().getFileUpload().equalsIgnoreCase("OK")) {
						if(healthCheckResponseGet.getComponentsStatus().getFieldAgentGateway().equalsIgnoreCase("OK")) {
							System.out.println("evrything is ok");
						}else {
							throw new AssertionError("field agent gateway is not ok");
						}
					}else {
						throw new AssertionError("file upload is not ok");
					}
				}else {
					throw new AssertionError("field agent gateway is not ok");
				}
			}else {
				throw new AssertionError("asset hierarchy is no ok");
			}
		}
	}
}
