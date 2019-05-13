package io.cucumber.skeleton;

import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.EventServiceAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.model.EvenServiceResponseAllGet;
import io.cucumber.model.EventServiceResponseGet;

public class EventServices {

	List<Map<String, String>> list;
	EventServiceResponseGet eventServiceResponseGetResponse;
	EvenServiceResponseAllGet evenServiceResponseAllEventsResponse;

	@Given("^I have the event details$")
	public void givenData(DataTable dataTable) {
		System.out.println("Event Services : Given ..");
		list = dataTable.asMaps(String.class, String.class);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list);
		}
	}
	@When("^I post getall get events$")
	public void postEvents() {
		ObjectMapper mapperObj = new ObjectMapper();
		String eventData = "";
		try {
			eventData = mapperObj.writeValueAsString(list);
			System.out.println("departmentData :::"+eventData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		EventServiceAPI eventServiceAPI = new EventServiceAPI();
		eventServiceResponseGetResponse = eventServiceAPI.postEvent(eventData);
		System.out.println("eventServiceResponseGetResponse ===>"+eventServiceResponseGetResponse);

		evenServiceResponseAllEventsResponse = eventServiceAPI.getEvenServiceByAssetId(list.get(0).get("assetId"));
		System.out.println("evenServiceResponseAllEventsResponse ===>"+evenServiceResponseAllEventsResponse);
	}

	@Then("^validate the results$")
	public void validateResults() {

	}
}
