package io.cucumber.skeleton;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.MeasurementUnitAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.model.MeasurementUnitGetResponse;
import io.cucumber.model.MeasurementUnitPostResponse;
import io.cucumber.model.MeasurementUnitsResponse;

/*
 * class MeasurementUnit for testing MeasurementUnit feature BDD..
 */
public class MeasurementUnit {
	List<Map<String, String>> list;
	HashMap<String, String> dataTableHashMap;
	MeasurementUnitPostResponse measurementUnitPostResponse;
	MeasurementUnitGetResponse measurementUnitGetResponse;

	/*
	 * This method givenData() used for getting the given data from the feature..
	 */
	@Given("^I have below details$")
	public void givenData(DataTable dataTable) {
		list = dataTable.asMaps(String.class, String.class);
		for(Map<String,String> map : list) {
			dataTableHashMap = new HashMap<String, String>(); 
			dataTableHashMap.putAll(map); 
		}
		System.out.println("dataTableHashMap===>"+dataTableHashMap);
	}
	
	/*
	 * This method postSubscription() is used for POST, GET..
	 */
	@When("^I post then get$")
	public void postMeasurementUnit() {
		ObjectMapper mapperObj = new ObjectMapper();
		String measurementData = "";
		 Date date= new Date();
		try {
			dataTableHashMap.put("name", date.getTime()+"_name");
			measurementData = mapperObj.writeValueAsString(dataTableHashMap);
			System.out.println("measurementData :::"+measurementData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		MeasurementUnitAPI measurementUnitAPI = new MeasurementUnitAPI();
		measurementUnitPostResponse = measurementUnitAPI.postMeasurementUnit(measurementData);
		System.out.println("measurementUnitPostResponse ::"+measurementUnitPostResponse);
		
	    measurementUnitGetResponse = measurementUnitAPI.getAllMeasurementUnits();
		System.out.println("measurementUnitGetResponse ::"+measurementUnitGetResponse);
	}
	
	
	/*
	 * This method validateResults() is used for validating the responses..
	 */
	@Then("validate the Measurement results")
	public void validateResults() {
		if(measurementUnitPostResponse != null && measurementUnitPostResponse.getAliases().size()>0) {
			MeasurementUnitsResponse measurementUnitsResponse = measurementUnitGetResponse.getMeasurementUnits().stream().filter(m -> m.getMeasurementUnit().equals(measurementUnitPostResponse.getMeasurementUnit())).findAny().orElse(null);
			if(measurementUnitsResponse != null) {
				if(measurementUnitsResponse.getAliases().contains(dataTableHashMap.get("alias"))) {
					System.out.println("fount the "+dataTableHashMap.get("alias")+" in the first page");
				}else {
					throw new AssertionError("alias not present in  get all response");
				}
				System.out.println("fount the "+measurementUnitPostResponse.getMeasurementUnit()+" in the first page");
			}else {
				//throw new AssertionError("Id not present in  get all response");
			}
		
		}
	}
	
}
